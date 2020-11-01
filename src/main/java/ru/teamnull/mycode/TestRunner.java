package ru.teamnull.mycode;

import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
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
    @Profile("MycodeApplicationDev")
    public void run(String... args) throws Exception {
//        User user = new User(
//                "max",
//                "password",
//                "Max",
//                "Golish",
//                "Stanislavovich",
//                "@gmail.com",
//                new Date(),
//                Role.STUDENT
//                );
//        userRepository.save(user);
//        Group itmo = new Group("itmo");
//        groupRepository.save(itmo);
//        Task task = new Task (
//                null,
//                "tree",
//                List.of(itmo),
//                "## РРР-дерево\n\nРешите задачу, жива",
//                List.of(new Test(
//                        1, "10", "5", 1.0f
//                )),
//                new Date(),
//                Collections.emptyList(),
//                1000f,
//                 1000,
//                CheckType.TEST,
//                PostprocessorType.EASY,
//                Collections.emptyList(),
//                Coll
//        );
//

    }
}
