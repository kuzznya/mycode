package ru.teamnull.mycode.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.teamnull.mycode.entity.Course;
import ru.teamnull.mycode.repository.CourseRepository;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class CourseService {
    private final CourseRepository courseRepository;

    public List<Course> allCourses () {
        return courseRepository.findAll();
    }

    public Course findById(UUID uuid) {
        return courseRepository.findById(uuid).orElseThrow(EntityNotFoundException::new);
    }


}
