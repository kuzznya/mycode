package ru.teamnull.mycode.controller;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import ru.teamnull.mycode.dto.GroupsTask;
import ru.teamnull.mycode.service.GroupTaskService;

import java.util.List;

@RestController("/tasks")
@AllArgsConstructor
public class GroupTaskController {
    private final GroupTaskService groupTaskService;

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @GetMapping("/tasks")
    public Flux<GroupsTask> getGroupTasks() {
        return Flux.fromIterable(groupTaskService.getAll());
    }
}
