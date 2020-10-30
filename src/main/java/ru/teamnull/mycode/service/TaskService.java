package ru.teamnull.mycode.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.teamnull.mycode.repository.GroupRepository;
import ru.teamnull.mycode.repository.TaskRepository;

@Service
@AllArgsConstructor
public class TaskService {
    private final TaskRepository taskRepository;
}
