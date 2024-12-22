package pe.edu.unfv.ms.courses.app.application.ports.output;

import pe.edu.unfv.ms.courses.app.domain.models.Course;

import java.util.List;
import java.util.Optional;

public interface CoursePersistencePort {

    Optional<Course> findById(Long id);
    List<Course> findAll();
    Course save(Course course);
    void deleteById(Long id);
}
