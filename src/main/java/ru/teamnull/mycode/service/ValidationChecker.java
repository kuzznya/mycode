package ru.teamnull.mycode.service;

import reactor.core.publisher.Mono;
import ru.teamnull.mycode.entity.CheckResult;
import ru.teamnull.mycode.entity.Submission;
import ru.teamnull.mycode.entity.Validation;
import ru.teamnull.mycode.model.SubmissionStatus;

import java.io.File;

public class ValidationChecker extends Checker {

    private Validation validation;

    private final File generator;

    private final File validator;

    public ValidationChecker(Submission submission) {
        super(submission);

        validation = submission.getTask().getValidation();

        generator = FileUtils.createFile(
                validation.getGenerator(),
                submission.getId() + "-generator",
                validation.getLanguage().getExtension()
        );

        validator = FileUtils.createFile(
                validation.getValidator(),
                submission.getId() + "-validator",
                validation.getLanguage().getExtension()
        );
    }

    @Override
    protected int getTestsCount() {
        return validation.getTestCount();
    }

    private static native int validationCheck(float timeLimit, int memoryLimit,
                                              String codePath, String generatorPath, String validatorPath);

    private SubmissionStatus validationCheck(File codeFile,
                                             File generator,
                                             File validator,
                                             float timeLimitSeconds,
                                             int memoryLimitKB) {
        // TODO: 31.10.2020 CHECK results
        return SubmissionStatus.OK;
    }

    @Override
    protected Mono<CheckResult> check(int index, float timeLimitSeconds, int memoryLimitKB) {
        return Mono.fromSupplier(() ->
                validationCheck(
                        super.codeFile,
                        generator,
                        validator,
                        timeLimitSeconds,
                        memoryLimitKB)
        ).map(status -> CheckResult
                        .builder()
                        .submission(submission)
                        .status(status)
                        .validation(validation)
                        .validationNumber(index)
                        .build()
        );
    }
}
