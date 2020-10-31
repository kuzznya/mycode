package ru.teamnull.mycode.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.teamnull.mycode.dto.GroupsTask;
import ru.teamnull.mycode.dto.TaskDto;
import ru.teamnull.mycode.entity.Group;
import ru.teamnull.mycode.repository.TaskRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class GroupTaskService {
    private final TaskRepository taskRepository;

    public List<GroupsTask> getAll() {
        List<GroupsTask> groupTasks = new ArrayList<>();
        for(var task : taskRepository.findAll())
            groupTasks.add(new GroupsTask(task.getGroups()
                    .stream()
                    .map(Group::getId)
                    .collect(Collectors.toList()),
                    new TaskDto(task.getId(), task.getProblem(), task.getDeadline())));
        return groupTasks;
    }
}
