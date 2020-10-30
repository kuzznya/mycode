package ru.teamnull.mycode.controller;

import org.springframework.web.bind.annotation.*;
import ru.teamnull.mycode.entity.User;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/groups/{groupId}/students")
public class StudentController {
    @GetMapping
    public List<User> getAllStudents(@PathVariable UUID groupId) {
        return Collections.emptyList();
    }

    @GetMapping("/{studentId}")
    public User getStudentById(@PathVariable UUID studentId) {
        return new User();
    }

    @PostMapping
    public User addStudent(@RequestParam(name = "student_id") UUID studentId) {
        return new User();
    }

//    @DeleteMapping("/{studentId}")
//    public void deleteStudent() {
//
//    }
}
