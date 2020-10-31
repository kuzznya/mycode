package ru.teamnull.mycode.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import reactor.core.publisher.Flux;
import ru.teamnull.mycode.entity.CheckResult;
import ru.teamnull.mycode.model.Language;
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

//    @PostMapping
//    public Flux<String> submit(@PathVariable UUID taskId,
//                               @RequestParam Language language,
//                               @RequestParam MultipartFile file) {
//        return submissionService.submit(taskId, language, file).map(this::resultToJson);
//    }

    @PostMapping(produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<String> submit(@PathVariable UUID taskId, @RequestParam Language language, @RequestBody String code) {
        return submissionService.submit(taskId, language, code).map(this::resultToJson);
    }
}
