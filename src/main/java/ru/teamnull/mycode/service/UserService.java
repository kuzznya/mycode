package ru.teamnull.mycode.service;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;
import ru.teamnull.mycode.entity.User;
import ru.teamnull.mycode.model.SignInRequest;
import ru.teamnull.mycode.repository.UserRepository;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService implements ReactiveUserDetailsService {

    private final UserRepository userRepository;
//    private final PasswordEncoder passwordEncoder;

    @Override
    public Mono<UserDetails> findByUsername(String username) {
        return Mono.justOrEmpty(userRepository.findByUsername(username));
    }

    public User register(@RequestBody User user) {
        Optional<User> userFromDb = userRepository.findByUsername(user.getUsername());
        if(userFromDb.isPresent())
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        return userRepository.save(user);
    }

    public User validateRequest(SignInRequest request) {
        return userRepository
                .findByUsername(request.getUsername())
                .filter(user -> user.getPassword().matches("(\\{noop})?" + request.getPassword()))
//                .filter(user -> passwordEncoder.matches(request.getPassword(), user.getPassword()))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.FORBIDDEN));
    }
}
