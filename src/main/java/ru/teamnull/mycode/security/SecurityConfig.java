package ru.teamnull.mycode.security;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.userdetails.MapReactiveUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.server.SecurityWebFilterChain;
import ru.teamnull.mycode.service.UserService;

@EnableWebFluxSecurity
@EnableReactiveMethodSecurity
@AllArgsConstructor
public class SecurityConfig {

//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
        return http
                .cors()
                .and()
                .csrf().disable()
                .authorizeExchange()
//                .pathMatchers(HttpMethod.POST, "/sign-up").permitAll()
//                .pathMatchers(HttpMethod.POST, "/sign-in").permitAll()
                .anyExchange().permitAll()
                .and()
                .build();
    }

    @Bean
    @Primary
    public MapReactiveUserDetailsService userDetailsService() {
        System.out.println("Here");
        UserDetails user = User
                .withUsername("user")
                .password("password")
                .roles("STUDENT")
                .build();
        return new MapReactiveUserDetailsService(user);
    }
}
