package ru.teamnull.mycode.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.teamnull.mycode.entity.Course;

import java.util.UUID;

public interface CourseRepository extends JpaRepository<Course, UUID> {
}