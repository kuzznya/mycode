package ru.teamnull.mycode.controller;

import org.springframework.web.bind.annotation.*;
import ru.teamnull.mycode.entity.Task;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/groups/{groupId}/tasks")
public class TaskController {

    @GetMapping
    public List<Task> getAllTasks(@PathVariable UUID groupId) {
        return Collections.emptyList();
    }

    @PostMapping
    public Task addTask(@RequestBody Task newTask) {
        return new Task();
    }

    @GetMapping("/{taskId}")
    public Task getTaskById(@PathVariable UUID taskId) {
        return new Task();
    }

    @DeleteMapping("/{taskId}")
    public void deleteTaskById(@PathVariable UUID taskId) {
    }


}
