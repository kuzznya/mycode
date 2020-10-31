package ru.teamnull.mycode.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.teamnull.mycode.entity.Task;
import ru.teamnull.mycode.service.TaskService;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@RestController
@RequestMapping("/groups/{groupId}/tasks")
@AllArgsConstructor
public class TaskController {
    private final TaskService taskService;

    @GetMapping
    public List<Task> getAllTasks(@PathVariable UUID groupId) {
        return taskService.getTasksByGroupId(groupId);
    }

    @PostMapping
    public Task addTask(@RequestBody Task newTask) {
        return taskService.addTask(newTask);
    }

    @GetMapping("/{taskId}")
    public Task getTaskById(@PathVariable UUID groupId,
                            @PathVariable UUID taskId) {
        return taskService.getById(groupId, taskId);
    }

    @DeleteMapping("/{taskId}")
    public void deleteTaskById(@PathVariable UUID taskId) {
        taskService.deleteById(taskId);
    }
}
