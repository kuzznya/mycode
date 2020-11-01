package ru.teamnull.mycode.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import ru.teamnull.mycode.entity.CheckResult;
import ru.teamnull.mycode.entity.Submission;
import ru.teamnull.mycode.entity.User;
import ru.teamnull.mycode.model.Language;
import ru.teamnull.mycode.model.Role;
import ru.teamnull.mycode.model.SubmissionRequest;
import ru.teamnull.mycode.repository.UserRepository;
import ru.teamnull.mycode.security.SecurityContextUserReceiver;
import ru.teamnull.mycode.service.SubmissionService;

import java.time.Duration;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/tasks/{taskId}/submissions")
@AllArgsConstructor
public class SubmissionController {

    private final SubmissionService submissionService;

    private final UserRepository userRepository;

    private String resultToJson(CheckResult result) {
        int testNumber;
        if (result.getTest() != null)
            testNumber = result.getTest().getNumber();
        else
            testNumber = result.getValidationNumber();

        return "{\"test\": " + testNumber + "\", \"result\": \"" + result.getStatus().name() + "\"}";
    }

    @GetMapping
    public List<Submission> getSubmissions(UserDetails userDetails) {
        if (userDetails.getAuthorities().contains(new SimpleGrantedAuthority("STUDENT"))) {
            userRepository
                    .findByUsername(userDetails.getUsername())
                    .ifPresentOrElse(
                    user -> submissionService.getSubmissions(),
                    submissionService::getSubmissions);
        }
        return submissionService.getSubmissions();
    }

//    @PostMapping
//    public Submission submit(@PathVariable UUID taskId,
//                       @RequestBody SubmissionRequest request) {
//        submissionService.submit(taskId, request.getLanguage(), request.getCode())
//                .subscribe();
//    }

    @PostMapping(produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<String> submit(@PathVariable UUID taskId,
                               @RequestBody SubmissionRequest request) {
        return submissionService
                .submit(taskId, request.getLanguage(), request.getCode())
                .map(this::resultToJson)
                .delayElements(Duration.ofMillis(500));
    }
}
