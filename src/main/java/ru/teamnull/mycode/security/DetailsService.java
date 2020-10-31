package ru.teamnull.mycode.security;

import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import reactor.core.publisher.Mono;

public interface DetailsService extends ReactiveUserDetailsService {
    Mono<UserDetails> findByUsername(String username);
}
