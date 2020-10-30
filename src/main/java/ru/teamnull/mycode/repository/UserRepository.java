package ru.teamnull.mycode.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.teamnull.mycode.entity.User;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    User findByUsername(String username);
}
