package org.zhaoxuan.gateway.common.filter;

import cn.hutool.core.text.AntPathMatcher;
import com.alibaba.fastjson.JSON;
import jakarta.annotation.Resource;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.*;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.*;
import org.springframework.http.server.reactive.*;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.server.ServerWebExchange;
import org.zhaoxuan.common.Beans.HeaderBean;
import org.zhaoxuan.common.constants.CommonResponseCode;
import org.zhaoxuan.common.constants.HeaderConstants;
import org.zhaoxuan.common.exception.ExceptionDecider;
import org.zhaoxuan.common.utils.*;
import org.zhaoxuan.gateway.common.configs.SysParameterConfig;
import org.zhaoxuan.pojo.response.BaseResponse;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.function.Consumer;

@Slf4j
@Component
@RequiredArgsConstructor(onConstructor = @__({@Autowired}))
public class AuthenticationFilter implements GlobalFilter, Ordered {

    @Resource
    private RedisAccessUtils redisUtil;
    @Resource
    private MessageUtils messageUtils;

    private final SysParameterConfig sysConfig;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        String requestUrl = exchange.getRequest().getPath().value();

        LinkedHashMap<String, String> headerMap = new LinkedHashMap<>();

        String ip = IpUtil.getIpAddress(exchange.getRequest());
        log.info(">> request from : {}, path : {}.", ip, requestUrl);
        if (!ObjectUtils.isEmpty(ip)) {
            headerMap.put(HeaderConstants.IP, ip);
        }

        if (checkUrls(sysConfig.getIgnoreUrls(), requestUrl)) {
            return resetHeader(exchange, chain, headerMap);
        }

        try {
            HeaderBean headerBean = HeaderUtils.mapHeaderParam(exchange);
            ExceptionDecider.ifNull(headerBean.getToken(),
                    String.valueOf(CommonResponseCode.UNAUTHORIZED));
            ExceptionDecider.ifTrue(checkTokenExpire(headerBean.getToken()),
                    String.valueOf(CommonResponseCode.UNAUTHORIZED));

            headerMap.put(HeaderConstants.TOKEN, headerBean.getToken());
            log.info(">> token：{}", headerBean.getToken());
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

        log.info("request header size : {}.", headerMap.keySet().size());

        ServerHttpRequest tokenRequest = exchange.getRequest().mutate().headers(httpHeaders).build();
        ServerWebExchange build = exchange.mutate().request(tokenRequest).build();
        return chain.filter(build);
    }

    private boolean checkTokenExpire(String token) {
        String key = HeaderConstants.BEARER + token;
        long expire = redisUtil.getExpire(key);
        if (expire == -2) {
            return true;
        }
//        authFeignService.tokenProlong(token);
        if (!ObjectUtils.isEmpty(token)) {
            key = HeaderConstants.BEARER + token;
        }
        Object object = redisUtil.get(key);
        return ObjectUtils.isEmpty(object);
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