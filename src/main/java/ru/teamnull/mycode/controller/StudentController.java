package ru.teamnull.mycode.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ru.teamnull.mycode.entity.Group;
import ru.teamnull.mycode.entity.User;
import ru.teamnull.mycode.service.GroupService;
import ru.teamnull.mycode.service.StudentService;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@RestController
@RequestMapping("/groups/{groupId}/students")
@AllArgsConstructor
public class StudentController {
    private final StudentService studentService;
    private final GroupService groupService;
    
    @GetMapping
    public Set<User> getAllStudents(@PathVariable UUID groupId) {
        return studentService.getAllByGroupId(groupId);
    }

    @GetMapping("/{studentId}")
    public User getStudentById(@PathVariable UUID groupId,
                               @PathVariable UUID studentId) {
        return studentService.getStudentById(groupId, studentId);
    }

    @PostMapping
    public User addStudent(@RequestParam(name = "student_id") UUID studentId,
                           @PathVariable UUID groupId) {
        return studentService.addStudent(studentId, groupId);
    }

//    @DeleteMapping("/{studentId}")
//    public void deleteStudent() {
//
//    }
}
