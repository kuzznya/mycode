package ru.teamnull.mycode.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import ru.teamnull.mycode.entity.CheckResult;
import ru.teamnull.mycode.model.Language;
import ru.teamnull.mycode.model.SubmissionRequest;
import ru.teamnull.mycode.service.SubmissionService;

import java.util.UUID;

@RestController
@RequestMapping("/tasks/{taskId}/submissions")
@AllArgsConstructor
public class SubmissionController {

    private final SubmissionService submissionService;

    private String resultToJson(CheckResult result) {
        int testNumber;
        if (result.getTest() != null)
            testNumber = result.getTest().getNumber();
        else
            testNumber = result.getValidationNumber();

        return "{\"test\": " + testNumber + "\", \"result\": \"" + result.getStatus().name() + "\"}";
    }

    @PostMapping(produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<String> submit(@PathVariable UUID taskId,
                               @RequestBody SubmissionRequest request) {
        return submissionService
                .submit(taskId, request.getLanguage(), request.getCode())
                .map(this::resultToJson);
    }
}
