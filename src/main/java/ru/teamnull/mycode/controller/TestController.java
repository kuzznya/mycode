package ru.teamnull.mycode.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ru.teamnull.mycode.entity.Task;
import ru.teamnull.mycode.entity.Test;
import ru.teamnull.mycode.service.TaskService;
import ru.teamnull.mycode.service.TestService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/groups/{groupId}/tasks/{taskId}/tests")
@AllArgsConstructor
public class TestController {
    private final TestService testService;
    private final TaskService taskService;

    @GetMapping
    public List<Test> getAllTests(@PathVariable UUID taskId) {
        return testService.getAllByTaskId(taskId);
    }

    @PostMapping
    public Test addTest(@PathVariable UUID groupId,
                        @PathVariable UUID taskId,
                        @RequestBody Test newTest) {
        Task task = taskService.getById(groupId, taskId);
        task.getTests().add(newTest);
        taskService.addTask(groupId, task);
        return newTest;
    }
}
