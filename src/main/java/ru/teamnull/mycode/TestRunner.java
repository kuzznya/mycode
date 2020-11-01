package ru.teamnull.mycode;

import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ru.teamnull.mycode.entity.*;
import ru.teamnull.mycode.model.*;
import ru.teamnull.mycode.repository.*;
import ru.teamnull.mycode.service.Checker;

import java.util.*;

@Component
@AllArgsConstructor
public class TestRunner implements CommandLineRunner {
    private final UserRepository userRepository;
    private final GroupRepository groupRepository;
    private final SampleRepository sampleRepository;
    private final SubmissionRepository submissionRepository;
    private final TaskRepository taskRepository;
    private final TestRepository testRepository;
    private final ValidationRepository validationRepository;

    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
//        ru.teamnull.mycode.util.Loader.loadNativeLibrary();
//        Group group = new Group(null, "M3205", Collections.emptyList(), Collections.emptySet());
//        groupRepository.save(group);
//        User user = new User(
//                null,
//                "kuzznya",
//                passwordEncoder.encode("test"),
////                "test",
//                "Ilya",
//                "Kuznetsov",
//                "Andreevich",
//                "ikuz2002@gmail.com",
//                new Date(),
//                Role.STUDENT,
//                group,
//                Collections.emptyList()
//        );
//        userRepository.save(user);
//        Task task = new Task(
//                null,
//                "avl",
//                List.of(group),
//                "test",
//                List.of(new Sample(null, "1", "5")),
//                new Date(),
//                Collections.emptyList(),
//                5.0f, 32_000,
//                CheckType.TEST,
//                PostprocessorType.EASY,
//                Collections.emptyList(),
//                null
//        );
//        taskRepository.save(task);
//        Test test = new Test(1, "10 0", "something", 1.0f);
//        Test test1 = new Test(2, "test", "test", 2.0f);
//        Test test2 = new Test(3, "1 2", "3", 3.0f);
//        task.setTests(List.of(test, test1, test2));
//        testRepository.save(test);
//        testRepository.save(test1);
//        testRepository.save(test2);
//        group.setTasks(List.of(task));
//
//        taskRepository.save(task);
//        groupRepository.save(group);
//        Submission submission = Submission.builder()
//                .user(user)
//                .task(task)
//                .code("#include <iostream>\n\n" +
//                        "int main() {\n" +
//                        "std::cout << \"something\";\n" +
//                        "}")
//                .language(Language.CPP)
//                .status(SubmissionStatus.OK)
//                .timestamp(new Date())
//                .build();
//        Submission submission1 = Submission.builder()
//                .user(user)
//                .task(task)
//                .code("public class Main {\n" +
//                        "public static void main(String[] args) {\n" +
//                        "System.out.println(\"Hello World!\");\n" +
//                        "}\n" +
//                        "}")
//                .language(Language.JAVA)
//                .status(SubmissionStatus.OK)
//                .timestamp(new Date())
//                .build();
//        submissionRepository.save(submission);
//        submissionRepository.save(submission1);
////        submissionRepository.deleteById(submission1.getId());
////        taskRepository.deleteById(task.getId());
//        userRepository.getOne(user.getId());
////        userRepository.deleteById(user.getId());
//
//        Checker.createChecker(submission)
//                .check()
//                .doOnNext(System.out::println)
//                .subscribe();
//
//        Checker.createChecker(submission1)
//                .check()
//                .doOnNext(System.out::println)
//                .subscribe();
    }
}
