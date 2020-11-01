package ru.teamnull.mycode.service;

import reactor.core.publisher.Mono;
import ru.teamnull.mycode.entity.CheckResult;
import ru.teamnull.mycode.entity.Submission;
import ru.teamnull.mycode.entity.Test;
import ru.teamnull.mycode.model.SubmissionStatus;
import ru.teamnull.mycode.util.Loader;

import java.io.*;
import java.util.List;

public class TestChecker extends Checker {

    private static final String tempPath = "/Users/kuzznya";

    private final List<Test> tests;

    public TestChecker(Submission submission) {
        super(submission);

        this.tests = submission.getTask().getTests();
    }

    @Override
    protected int getTestsCount() {
        return tests.size();
    }

    private static native int testCheck(String cnativePath, float timeLimit, int memoryLimit,
                                        String codePath, String inputPath, String outputPath);

    private SubmissionStatus testCheck(File codeFile,
                                       File inputFile,
                                       File outputFile,
                                       float timeLimitSeconds,
                                       int memoryLimitKB) {
        int result = testCheck(
                System.getProperty("java.library.path"),
                timeLimitSeconds, memoryLimitKB,
                codeFile.getAbsolutePath(), inputFile.getAbsolutePath(), outputFile.getAbsolutePath());

        //Compilation Error
        //OK
        //Wrong Answer
        //Memory Limit Exceeded
        //Time Limit Exceeded
        //Runtime Error

        System.out.println(result);
        switch (result) {
            case 1:
                return SubmissionStatus.CE;
            case 2:
                return SubmissionStatus.OK;
            case 3:
                return SubmissionStatus.WA;
            case 4:
                return SubmissionStatus.ML;
            case 5:
                return SubmissionStatus.TL;
            case 6:
                return SubmissionStatus.RE;
            default:
                return SubmissionStatus.UNKNOWN_ERROR;
        }
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

    static {
        try {
            Loader.loadNativeLibrary();
        } catch (Exception ignored) { }
    }
}
