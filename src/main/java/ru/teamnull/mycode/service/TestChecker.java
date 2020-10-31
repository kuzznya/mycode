package ru.teamnull.mycode.service;

import reactor.core.publisher.Mono;
import ru.teamnull.mycode.entity.CheckResult;
import ru.teamnull.mycode.entity.Submission;
import ru.teamnull.mycode.entity.Test;
import ru.teamnull.mycode.model.SubmissionStatus;

import java.io.File;
import java.util.List;

public class TestChecker extends Checker {

    private final List<Test> tests;

    public TestChecker(Submission submission) {
        super(submission);

        this.tests = submission.getTask().getTests();
    }

    @Override
    protected int getTestsCount() {
        return tests.size();
    }

    private SubmissionStatus testCheck(File codeFile,
                                       File inputFile,
                                       File outputFile,
                                       float timeLimitSeconds,
                                       int memoryLimitKB) {
        // TODO: 31.10.2020 CHECK results
        return SubmissionStatus.OK;
    }

    @Override
    protected Mono<CheckResult> check(int index, float timeLimitSeconds, int memoryLimitKB) {
        if (index > tests.size())
            throw new IndexOutOfBoundsException();

        Test test = tests.get(index);

        Mono<File> inputFileMono = Mono.fromSupplier(() ->
                FileUtils.createFile(
                        test.getInput(),
                        super.submission.getId().toString() + "-" + index + "-input",
                        ".txt")
        );

        Mono<File> outputFileMono = Mono.fromSupplier(() ->
                FileUtils.createFile(
                        test.getOutput(),
                        super.submission.getId().toString() + "-" + index + "-output",
                        ".txt")
        );

        return inputFileMono
                .zipWith(outputFileMono)
                .map(tuple -> testCheck(
                        super.codeFile,
                        tuple.getT1(),
                        tuple.getT2(),
                        timeLimitSeconds,
                        memoryLimitKB)
                )
                .map(status -> CheckResult
                        .builder()
                        .status(status)
                        .submission(submission)
                        .test(test)
                        .build()
                );
    }
}
