package ru.teamnull.mycode.controller;

import org.springframework.web.bind.annotation.*;
import ru.teamnull.mycode.entity.Group;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/groups")
public class GroupController {
    @GetMapping
    public List<Group> getAllGroups() {
        return Collections.emptyList();
    }

    @PostMapping
    public Group addGroup(@RequestBody Group newGroup) {
        return new Group();
    }
}
