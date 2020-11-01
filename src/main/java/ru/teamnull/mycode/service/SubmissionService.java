package ru.teamnull.mycode.service;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.teamnull.mycode.entity.CheckResult;
import ru.teamnull.mycode.entity.Submission;
import ru.teamnull.mycode.entity.Task;
import ru.teamnull.mycode.entity.User;
import ru.teamnull.mycode.model.Language;
import ru.teamnull.mycode.model.SubmissionStatus;
import ru.teamnull.mycode.repository.CheckResultRepository;
import ru.teamnull.mycode.repository.SubmissionRepository;
import ru.teamnull.mycode.repository.TaskRepository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class SubmissionService {

    private final TaskRepository taskRepository;
    private final SubmissionRepository submissionRepository;
    private final CheckResultRepository checkResultRepository;

    private final List<Submission> finishedSubmissions = new ArrayList<>();

    public List<Submission> getSubmissions(User user) {
        finishedSubmissions.forEach(this::updateSubmissionStatus);
        finishedSubmissions.clear();
        return submissionRepository
                .findAll()
                .stream()
                .filter(submission -> submission.getUser().equals(user))
                .collect(Collectors.toList());
    }

    public List<Submission> getSubmissions() {
        finishedSubmissions.forEach(this::updateSubmissionStatus);
        finishedSubmissions.clear();
        return submissionRepository.findAll();
    }

    private Submission createSubmission(Task task, String code, Language language) {
        return Submission
                .builder()
                .task(task)
                .code(code)
                .language(language)
                .status(SubmissionStatus.IN_PROCESS)
                .build();
    }

    private void updateSubmissionStatus(Submission submission) {
        SubmissionStatus status = SubmissionStatus.OK;

        for (CheckResult result : submission.getCheckResults()) {
            if (result.getStatus().ordinal() > status.ordinal() || result.getStatus() == SubmissionStatus.IN_PROCESS)
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
                .doOnNext(processedSubmission::set)
                .map(Checker::createChecker)
                .map(Checker::check)
                .flatMapMany(resultFlux -> resultFlux)
                .doOnNext(result -> {
                    if (processedSubmission.get().getCheckResults() != null)
                        processedSubmission.get().getCheckResults().add(result);
                    else {
                        List<CheckResult> results = new ArrayList<>();
                        processedSubmission.get().setCheckResults(results);
                    }
                })
                .doFinally(result -> finishedSubmissions.add(processedSubmission.get()));
    }
}
