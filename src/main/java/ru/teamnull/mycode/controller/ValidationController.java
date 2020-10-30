package ru.teamnull.mycode.controller;

import org.springframework.web.bind.annotation.*;
import ru.teamnull.mycode.entity.Test;
import ru.teamnull.mycode.entity.Validation;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/groups/{groupId}/tasks/{taskId}/validation")
public class ValidationController {
    @GetMapping
    public List<Validation> getValidation(@PathVariable UUID groupId,
                                          @PathVariable UUID taskId) {
        return Collections.emptyList();
    }

    @PostMapping
    public Validation addValidation(@PathVariable UUID groupId,
                        @PathVariable UUID taskId,
                        @RequestBody Validation newValidation) {
        return new Validation();
    }
}