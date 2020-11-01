package ru.teamnull.mycode.service;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.teamnull.mycode.entity.*;
import ru.teamnull.mycode.model.Language;
import ru.teamnull.mycode.model.SubmissionStatus;
import ru.teamnull.mycode.repository.SubmissionRepository;
import ru.teamnull.mycode.repository.TaskRepository;
import ru.teamnull.mycode.repository.CheckResultRepository;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Date;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;

@Service
@AllArgsConstructor
public class SubmissionService {

    private final TaskRepository taskRepository;
    private final SubmissionRepository submissionRepository;
    private final CheckResultRepository checkResultRepository;

    private Submission createSubmission(Task task, String code, Language language) {
        return Submission
                .builder()
                .task(task)
                .code(code)
                .language(language)
                .status(SubmissionStatus.IN_PROCESS)
                .build();
    }

    private void updateSubmissionStatus(UUID submissionId) {
        Submission submission = submissionRepository.getOne(submissionId);
        SubmissionStatus status = SubmissionStatus.IN_PROCESS;

        for (CheckResult result : submission.getCheckResults()) {
            if (result.getStatus().ordinal() < status.ordinal())
                status = result.getStatus();
        }

        submission.setStatus(status);
        submissionRepository.save(submission);
    }

    public Flux<CheckResult> submit(UUID taskId, Language language, String code) {
        AtomicReference<Submission> processedSubmission = new AtomicReference<>();
        return Mono.just(taskRepository
                .findById(taskId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Task with id " + taskId + " not found")
                ))
                .doOnNext(task -> {
                    if (task.getDeadline().after(new Date()))
                        throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "Submission past deadline");
                })
                .map(task -> createSubmission(task, code, language))
                .map(submissionRepository::save)
                .doOnNext(processedSubmission::set)
                .map(Checker::createChecker)
                .map(Checker::check)
                .flatMapMany(resultFlux -> resultFlux)
                .doOnNext(checkResultRepository::save)
                .doFinally(signalType -> updateSubmissionStatus(processedSubmission.get().getId()));
    }
}
