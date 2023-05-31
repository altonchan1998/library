package com.altonchan.gateway;

import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.web.server.ServerWebExchange;


@Component
public class FilterUtil {

    public static final String CORRELATION_ID = "library-correlation-id";

    public String getCorrelationId(HttpHeaders requestHeaders) {
        return !CollectionUtils.isEmpty(requestHeaders.get(CORRELATION_ID)) ? requestHeaders.getFirst(CORRELATION_ID) : null;
    }

    public ServerWebExchange setRequestHeader(ServerWebExchange exchange, String name, String value) {
        return exchange.mutate().request(exchange.getRequest().mutate().header(name, value).build()).build();
    }

    public ServerWebExchange setCorrelationId(ServerWebExchange exchange, String correlationId) {
        return this.setRequestHeader(exchange, CORRELATION_ID, correlationId);
    }

}