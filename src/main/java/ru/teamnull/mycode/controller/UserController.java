package ru.teamnull.mycode.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;
import ru.teamnull.mycode.entity.User;
import ru.teamnull.mycode.model.Role;
import ru.teamnull.mycode.model.SignInRequest;
import ru.teamnull.mycode.repository.UserRepository;
import ru.teamnull.mycode.service.UserService;

import javax.swing.text.html.Option;
import java.util.Base64;
import java.util.Optional;

@RestController
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/sign-up")
    public ResponseEntity<User> register(@RequestBody User user) {
        User registeredUser = userService.register(user);

        String plainCredentials = user.getUsername() + ":" + user.getPassword();
        String base64Credentials = Base64.getEncoder().encodeToString(plainCredentials.getBytes());

        return ResponseEntity.ok()
                .header("Authorization", "Basic " + base64Credentials)
                .body(registeredUser);
    }

    @PostMapping("/sign-in")
    public ResponseEntity<User> signIn(@RequestBody SignInRequest request) {
        User user = userService.validateRequest(request);

        String plainCredentials = user.getUsername() + ":" + user.getPassword();
        String base64Credentials = Base64.getEncoder().encodeToString(plainCredentials.getBytes());

        return ResponseEntity.ok()
                .header("Authorization", "Basic " + base64Credentials)
                .body(user);
    }
}
