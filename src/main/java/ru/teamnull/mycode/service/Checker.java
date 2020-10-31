package ru.teamnull.mycode.service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.teamnull.mycode.entity.Submission;
import ru.teamnull.mycode.entity.Task;
import ru.teamnull.mycode.entity.CheckResult;
import ru.teamnull.mycode.model.CheckType;

import java.io.File;
import java.util.stream.IntStream;

public abstract class Checker {

    protected final File codeFile;
    protected final Submission submission;

    public Checker(Submission submission) {
        this.submission = submission;

        this.codeFile = FileUtils.createFile(
                submission.getCode(),
                submission.getId().toString() + "-code",
                submission.getLanguage().getExtension()
        );
    }

    protected abstract int getTestsCount();

    protected abstract Mono<CheckResult> check(int index, float timeLimitSeconds, int memoryLimitKB);

    public Flux<CheckResult> check() {
        Task task = submission.getTask();
        return Flux
                .fromStream(IntStream.range(0, getTestsCount()).boxed())
                .map(index -> check(index, task.getTimeLimit(), task.getMemoryLimit()))
                .flatMap(mono -> mono);
    }

    public static Checker createChecker(Submission submission) {
        if (submission.getTask().getCheckType() == CheckType.TEST)
            return new TestChecker(submission);
        else
            return new ValidationChecker(submission);
    }
}
