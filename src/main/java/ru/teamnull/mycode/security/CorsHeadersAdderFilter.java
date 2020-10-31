package ru.teamnull.mycode.security;

import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

import java.util.List;

@Component
public class CorsHeadersAdderFilter implements WebFilter {


    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        exchange.getResponse().getHeaders().setAccessControlAllowOrigin("*");
        exchange.getResponse().getHeaders().setAccessControlRequestHeaders(List.of("*"));
        exchange.getResponse().getHeaders().setAccessControlExposeHeaders(List.of("Authorization"));
        exchange.getResponse().getHeaders().setAccessControlMaxAge(3600);

        return chain.filter(exchange);
    }
}
