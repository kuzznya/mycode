package ru.teamnull.mycode.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import ru.teamnull.mycode.entity.Group;
import ru.teamnull.mycode.repository.GroupRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class GroupService {
    private final GroupRepository groupRepository;

    public List<Group> getAll() {
        return groupRepository.findAll();
    }

    public Group add(Group group) {
        return groupRepository.save(group);
    }

    public Optional<Group> findById(UUID id) {
        return groupRepository.findById(id);
    }
}
