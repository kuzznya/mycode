package ru.teamnull.mycode.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ru.teamnull.mycode.entity.Group;
import ru.teamnull.mycode.service.GroupService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/groups")
@AllArgsConstructor
public class GroupController {
    private final GroupService groupService;

    @GetMapping
    public List<Group> getAllGroups() {
        return groupService.getAll();
    }

    @GetMapping("/{groupId}")
    public Group getGroupById(@PathVariable UUID groupId) {
        return groupService.findById(groupId)
                .orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Group " + groupId + " does not exist"));
    }

    @PostMapping
    public Group addGroup(@RequestBody Group newGroup) {
        return groupService.add(newGroup);
    }
}
