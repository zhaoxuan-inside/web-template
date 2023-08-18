package org.zhaoxuan.gateway.common.filter;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.text.AntPathMatcher;
import com.alibaba.fastjson.JSON;
import jakarta.annotation.Resource;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.server.ServerWebExchange;
import org.zhaoxuan.common.constants.*;
import org.zhaoxuan.common.exception.ExceptionDecider;
import org.zhaoxuan.common.exception.ResponseCodeEnum;
import org.zhaoxuan.common.utils.*;
import org.zhaoxuan.gateway.common.configs.SysParameterConfig;
import org.zhaoxuan.pojo.bean.HeaderBean;
import org.zhaoxuan.pojo.response.BaseResponse;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.function.Consumer;

@Slf4j
@Component
@SuppressWarnings({"unused", "UnusedReturnValue"})
@RequiredArgsConstructor(onConstructor = @__({@Autowired}))
public class AuthenticationFilter implements GlobalFilter, Ordered {

    @Resource
    private RedisAccessUtils redisUtil;
    @Resource
    private MessageUtils messageUtils;
    @Resource
    private Snowflake snowflake;
    private final SysParameterConfig sysConfig;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        String requestUrl = exchange.getRequest().getPath().value();

        String tid = snowflake.nextIdStr();
        HeaderBean headerBean = new HeaderBean();
        try {
            headerBean = HeaderUtils.mapHeaderParam(exchange);
            headerBean.setTid(tid);
            MDC.put(HeaderConstants.TID, tid);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        LinkedHashMap<String, String> headerMap = new LinkedHashMap<>();

        String ip = IpUtil.getIpAddress(exchange.getRequest());
        log.info(HeaderLogPatternConstants.REQUEST_HEADER_PARAM_IP, requestUrl, ip);
        if (!ObjectUtils.isEmpty(ip)) {
            headerMap.put(HeaderConstants.IP, ip);
        }

        if (checkUrls(sysConfig.getIgnoreUrls(), requestUrl)) {
            return resetHeader(exchange, chain, headerMap);
        }

        try {

            ExceptionDecider.ifNull(headerBean.getToken(), ResponseCodeEnum.TOKEN_NOT_FOUND);
            ExceptionDecider.ifTrue(checkTokenExpire(headerBean.getFrom(),
                            headerBean.getToken()),
                    ResponseCodeEnum.TOKEN_INVALID);

            headerMap.put(HeaderConstants.TOKEN, headerBean.getToken());
            headerMap.put(HeaderConstants.TID, tid);

            log.info(HeaderLogPatternConstants.REQUEST_HEADER_BEAN, headerBean);

            prolongTokenExpire(headerBean.getFrom(), headerBean.getToken());
            return resetHeader(exchange, chain, headerMap);
        } catch (Exception e) {
            return invalidTokenMono(exchange);
        }
    }

    private Mono<Void> resetHeader(ServerWebExchange exchange,
                                   GatewayFilterChain chain,
                                   LinkedHashMap<String, String> headerMap) {
        Consumer<HttpHeaders> httpHeaders = httpHeader -> {
            for (Map.Entry<String, String> entry : headerMap.entrySet()) {
                httpHeader.set(entry.getKey(), entry.getValue());
            }
        };

        ServerHttpRequest tokenRequest = exchange.getRequest().mutate().headers(httpHeaders).build();
        ServerWebExchange build = exchange.mutate().request(tokenRequest).build();
        return chain.filter(build);
    }

    private boolean checkTokenExpire(String from, String token) {
        Object object = redisUtil.get(RedisKeyPrefixConstants.UID_IDX_TOKEN + from + token);
        return ObjectUtils.isEmpty(object);
    }

    private boolean prolongTokenExpire(String from, String token) {
        return redisUtil.prolong(RedisKeyPrefixConstants.UID_IDX_TOKEN + from + token, TimeConstants.ONE_HOUR);
    }

    @Override
    public int getOrder() {
        return 0;
    }

    private boolean checkUrls(List<String> urls, String path) {
        AntPathMatcher pathMatcher = new AntPathMatcher();
        for (String url : urls) {
            if (pathMatcher.match(url, path)) {
                return true;
            }
        }
        return false;
    }

    private Mono<Void> invalidTokenMono(ServerWebExchange exchange) {
        String code = String.valueOf(CommonResponseCode.UNAUTHORIZED);
        String msg = messageUtils.get(code);
        return buildReturnMono(BaseResponse.builder()
                        .code(code)
                        .msg(msg)
                        .build(),
                exchange);
    }

    private Mono<Void> buildReturnMono(BaseResponse resultMsg, ServerWebExchange exchange) {
        ServerHttpResponse response = exchange.getResponse();
        byte[] bits = JSON.toJSONString(resultMsg).getBytes(StandardCharsets.UTF_8);
        DataBuffer buffer = response.bufferFactory().wrap(bits);
        response.setStatusCode(HttpStatus.UNAUTHORIZED);
        response.getHeaders().add(HeaderConstants.CONTENT_TYPE, HeaderConstants.JSON_CONTENT);
        return response.writeWith(Mono.just(buffer));
    }

}