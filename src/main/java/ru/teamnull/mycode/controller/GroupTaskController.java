package ru.teamnull.mycode.controller;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
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
    public List<GroupsTask> getGroupTasks() {
        return groupTaskService.getAll();
    }
}
