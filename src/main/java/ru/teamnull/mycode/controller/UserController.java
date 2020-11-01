package ru.teamnull.mycode.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;
import ru.teamnull.mycode.entity.User;
import ru.teamnull.mycode.model.SignInRequest;
import ru.teamnull.mycode.repository.UserRepository;
import ru.teamnull.mycode.security.SecurityContextUserReceiver;
import ru.teamnull.mycode.service.AuthService;

import java.util.Base64;

@RestController
@AllArgsConstructor
public class UserController {

    private final AuthService authService;
    private final SecurityContextUserReceiver receiver;
    private final UserRepository userRepository;

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

    @GetMapping("/check-token")
    public Mono<User> checkToken() {
        return receiver.getUser();
    }

    @GetMapping("/users/{username}")
    public User getByUsername(@PathVariable String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }
}
