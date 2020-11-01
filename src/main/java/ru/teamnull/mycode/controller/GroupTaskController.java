package ru.teamnull.mycode.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import ru.teamnull.mycode.dto.GroupsTask;
import ru.teamnull.mycode.service.GroupTaskService;

@RestController("/tasks")
@AllArgsConstructor
public class GroupTaskController {
    private final GroupTaskService groupTaskService;

    @GetMapping("/tasks")
    public Flux<GroupsTask> getGroupTasks() {
        return Flux.fromIterable(groupTaskService.getAll());
    }
}
