package ru.teamnull.mycode.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.teamnull.mycode.entity.Task;
import ru.teamnull.mycode.entity.Test;
import ru.teamnull.mycode.entity.Validation;
import ru.teamnull.mycode.repository.GroupRepository;
import ru.teamnull.mycode.service.GroupService;
import ru.teamnull.mycode.service.TaskService;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/groups/{groupId}/tasks/{taskId}/validation")
@AllArgsConstructor
public class ValidationController {
    private final TaskService taskService;

    @GetMapping
    public Validation getValidation(@PathVariable UUID groupId,
                                    @PathVariable UUID taskId) {
        return taskService.getById(groupId, taskId).getValidation();
    }

    @PostMapping
    public Validation addValidation(@PathVariable UUID groupId,
                        @PathVariable UUID taskId,
                        @RequestBody Validation newValidation) {
        Task task = taskService.getById(groupId, taskId);
        task.setValidation(newValidation);
        taskService.addTask(groupId, task);
        return newValidation;
    }
}