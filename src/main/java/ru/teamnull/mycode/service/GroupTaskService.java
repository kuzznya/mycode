package ru.teamnull.mycode.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.teamnull.mycode.dto.GroupTask;
import ru.teamnull.mycode.dto.TaskDto;
import ru.teamnull.mycode.repository.GroupRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class GroupTaskService {
    private final GroupRepository groupRepository;

    public List<GroupTask> getAll() {
        List<GroupTask> groupTasks = new ArrayList<>();
        for(var group : groupRepository.findAll())
            for(var task : group.getTasks())
                groupTasks.add(new GroupTask(group.getId(),
                        new TaskDto(task.getId(), task.getProblem(), task.getDeadline())));
        return groupTasks;
    }
}
