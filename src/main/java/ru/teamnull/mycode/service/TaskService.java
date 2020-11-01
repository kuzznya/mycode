package ru.teamnull.mycode.service;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import ru.teamnull.mycode.entity.Group;
import ru.teamnull.mycode.entity.Task;
import ru.teamnull.mycode.repository.GroupRepository;
import ru.teamnull.mycode.repository.TaskRepository;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class TaskService {
    private final TaskRepository taskRepository;
    private final GroupRepository groupRepository;

    public List<Task> getTasksByGroupId(UUID groupId) {
        return groupRepository.findById(groupId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND))
                .getTasks();
    }

    public Task getById(UUID groupId, UUID taskId) {
        return taskRepository.findById(taskId)
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public Task addTask(UUID groupId, Task task) {
        Group group = groupRepository.findById(groupId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        group.getTasks().add(task);
        task.getGroups().add(group);
        Task toReturn = taskRepository.save(task);
        groupRepository.save(group);
        return toReturn;
    }

    public void deleteById(UUID id) {
        taskRepository.deleteById(id);
    }
}
