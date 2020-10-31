package ru.teamnull.mycode.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.teamnull.mycode.entity.Test;
import ru.teamnull.mycode.entity.Validation;
import ru.teamnull.mycode.repository.GroupRepository;
import ru.teamnull.mycode.service.GroupService;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/groups/{groupId}/tasks/{taskId}/validation")
@AllArgsConstructor
public class ValidationController {
    private final GroupService groupService;

//    @GetMapping
//    public List<Validation> getValidation(@PathVariable UUID groupId,
//                                          @PathVariable UUID taskId) {
//        return groupService.findById(groupId).orElseThrow()
//    }

    @PostMapping
    public Validation addValidation(@PathVariable UUID groupId,
                        @PathVariable UUID taskId,
                        @RequestBody Validation newValidation) {
        return new Validation();
    }
}