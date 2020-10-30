package ru.teamnull.mycode.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.teamnull.mycode.entity.Node;

import java.util.UUID;

public interface NodeRepository extends JpaRepository<Node, UUID> {
}
