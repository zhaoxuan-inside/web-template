package org.zhaoxuan.gateway.common.filter;

import cn.hutool.core.lang.Snowflake;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jboss.logging.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.util.ObjectUtils;
import org.springframework.web.server.ServerWebExchange;
import org.zhaoxuan.common.constants.HeaderConstants;
import reactor.core.publisher.Mono;

@Slf4j
@ComponentScan
@SuppressWarnings("unused")
@RequiredArgsConstructor(onConstructor = @__({@Autowired}))
public class GatewayFilter implements GlobalFilter, Ordered {

    private final Snowflake snowflake;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String traceId = exchange.getRequest().getHeaders().getFirst(HeaderConstants.TID);
        traceId = ObjectUtils.isEmpty(traceId) ? snowflake.nextIdStr() : traceId;

        MDC.put(HeaderConstants.TID, traceId);
        ServerHttpRequest serverHttpRequest = exchange.getRequest().mutate()
                .header(HeaderConstants.TID, traceId)
                .build();

        exchange.getResponse().getHeaders().set(HeaderConstants.TID, traceId);

        ServerWebExchange build = exchange.mutate().request(serverHttpRequest).build();
        return chain.filter(build);
    }

    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE;
    }

}
