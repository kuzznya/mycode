package ru.teamnull.mycode.controller;

import org.springframework.web.bind.annotation.*;
import ru.teamnull.mycode.entity.Task;
import ru.teamnull.mycode.entity.Test;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/groups/{groupId}/tasks/{taskId}/tests")
public class TestController {
    @GetMapping
    public List<Test> getAllTests(@PathVariable UUID groupId,
                                  @PathVariable UUID taskId) {
        return Collections.emptyList();
    }

    @PostMapping
    public Test addTest(@PathVariable UUID groupId,
                        @PathVariable UUID taskId,
                        @RequestBody Test newTest) {
        return new Test();
    }
}
