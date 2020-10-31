package ru.teamnull.mycode.security;

import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

@Component
public class CorsHeadersAdderFilter implements WebFilter {


    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        exchange.getResponse().getHeaders().add("Access-Control-Allow-Origin", "*");
        exchange.getResponse().getHeaders().add("Access-Control-Request-Methods", "POST, GET, OPTIONS, DELETE");
        exchange.getResponse().getHeaders().add("Access-Control-Max-Age", "3600");

        return chain.filter(exchange);
    }
}
