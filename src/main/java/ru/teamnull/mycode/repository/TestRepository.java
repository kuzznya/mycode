package ru.teamnull.mycode.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.teamnull.mycode.entity.Test;

import java.util.UUID;

@Repository
public interface TestRepository extends JpaRepository<Test, UUID> {
}
