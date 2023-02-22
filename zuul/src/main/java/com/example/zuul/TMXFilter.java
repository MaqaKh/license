package com.example.zuul;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Component
public class TMXFilter implements GlobalFilter {
    private final Logger log = LoggerFactory.getLogger(TMXFilter.class);
    private static final String TMX_ID_HEADER_NAME = "TMX-ID";

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        log.info("Incoming request: {}", exchange.getRequest().getURI());
        ServerHttpRequest mutatedRequest = exchange.getRequest().mutate()
                .header(TMX_ID_HEADER_NAME, generateTmxId())
                .build();
        ServerWebExchange mutatedExchange = exchange.mutate().request(mutatedRequest).build();
        return chain.filter(mutatedExchange);
    }

    private String generateTmxId() {
        return UUID.randomUUID().toString();
    }

}
