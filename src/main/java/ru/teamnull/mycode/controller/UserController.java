package ru.teamnull.mycode.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.teamnull.mycode.entity.User;
import ru.teamnull.mycode.model.SignInRequest;
import ru.teamnull.mycode.service.AuthService;

import java.util.Base64;

@RestController
@AllArgsConstructor
public class UserController {

    private final AuthService authService;

    @PostMapping("/sign-up")
    public ResponseEntity<User> register(@RequestBody User user) {
        User registeredUser = authService.register(user);

        String plainCredentials = user.getUsername() + ":" + user.getPassword();
        String base64Credentials = Base64.getEncoder().encodeToString(plainCredentials.getBytes());

        return ResponseEntity.ok()
                .header("Authorization", "Basic " + base64Credentials)
                .body(registeredUser);
    }

    @PostMapping("/sign-in")
    public ResponseEntity<User> signIn(@RequestBody SignInRequest request) {
        User user = authService.validateRequest(request);

        String plainCredentials = user.getUsername() + ":" + user.getPassword();
        String base64Credentials = Base64.getEncoder().encodeToString(plainCredentials.getBytes());

        return ResponseEntity.ok()
                .header("Authorization", "Basic " + base64Credentials)
                .body(user);
    }
}
