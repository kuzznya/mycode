package ru.teamnull.mycode.security;

import lombok.AllArgsConstructor;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;
import ru.teamnull.mycode.entity.User;
import ru.teamnull.mycode.service.UserService;

@Component
@AllArgsConstructor
public class SecurityContextUserReceiver {

    private final UserService userService;

    public Mono<User> getUser() {
        return ReactiveSecurityContextHolder
                .getContext()
                .map(context -> (org.springframework.security.core.userdetails.User)
                        context.getAuthentication().getPrincipal())
                .map(org.springframework.security.core.userdetails.User::getUsername)
                .map(userService::findUser);
    }
}
