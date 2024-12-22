package pe.edu.unfv.ms.courses.app.application.ports.input;

import pe.edu.unfv.ms.courses.app.domain.models.Course;

import java.util.List;

public interface CourseInputPort {

    Course findById(Long id);
    List<Course> findAll();
    Course save(Course course);
    Course update(Long id, Course course);
    void deleteById(Long id);
}
