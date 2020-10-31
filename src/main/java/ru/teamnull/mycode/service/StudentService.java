package ru.teamnull.mycode.service;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;
import ru.teamnull.mycode.entity.Group;
import ru.teamnull.mycode.entity.User;
import ru.teamnull.mycode.repository.GroupRepository;
import ru.teamnull.mycode.repository.UserRepository;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service
@AllArgsConstructor
public class StudentService {
    private final GroupRepository groupRepository;
    private final GroupService groupService;

    public Set<User> getAllByGroupId(UUID groupId) {
        return groupRepository.findById(groupId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND))
                .getStudents();
    }

    public User getStudentById(UUID groupId, UUID studentId) {
        return getAllByGroupId(groupId)
                .stream()
                .filter(student -> student.getId().equals(studentId))
                .findFirst().orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public User addStudent(UUID studentId,
                           UUID groupId) {
        Group group = groupService.findById(groupId)
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        Set<User> students = group.getStudents();
        User student = getStudentById(groupId, studentId);
        students.add(student);
        group.setStudents(students);
        groupService.add(group);
        return student;
    }
}
