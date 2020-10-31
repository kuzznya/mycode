package ru.teamnull.mycode.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.teamnull.mycode.entity.User;
import ru.teamnull.mycode.model.SignInRequest;
import ru.teamnull.mycode.service.AuthService;

import java.util.Base64;

@RestController
@AllArgsConstructor
public class UserController {

    private final AuthService authService;

    @CrossOrigin(methods = RequestMethod.POST)
    @PostMapping("/sign-up")
    public ResponseEntity<User> register(@RequestBody User user) {
        User registeredUser = authService.register(user);

        String plainCredentials = user.getUsername() + ":" + user.getPassword();
        String base64Credentials = Base64.getEncoder().encodeToString(plainCredentials.getBytes());

        return ResponseEntity.ok()
                .header("Authorization", "Basic " + base64Credentials)
                .body(registeredUser);
    }

    @RequestMapping(method = RequestMethod.OPTIONS, path = "/sign-in")
    public ResponseEntity<?> signIn() {
        return ResponseEntity.ok().build();
    }

    @CrossOrigin(methods = RequestMethod.POST)
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
