package ru.teamnull.mycode.service;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import ru.teamnull.mycode.entity.Task;
import ru.teamnull.mycode.entity.Test;
import ru.teamnull.mycode.repository.TaskRepository;
import ru.teamnull.mycode.repository.TestRepository;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class TestService {
    private final TestRepository testRepository;
    private final TaskRepository taskRepository;


    public List<Test> getAllByTaskId(UUID id) {
        return taskRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND))
                .getTests();
    }
}
