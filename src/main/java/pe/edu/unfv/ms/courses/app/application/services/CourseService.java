package pe.edu.unfv.ms.courses.app.application.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pe.edu.unfv.ms.courses.app.application.ports.input.CourseInputPort;
import pe.edu.unfv.ms.courses.app.application.ports.input.ExternalStudentsInputPort;
import pe.edu.unfv.ms.courses.app.application.ports.output.CoursePersistencePort;
import pe.edu.unfv.ms.courses.app.application.ports.output.ExternalStudentsOutputPort;
import pe.edu.unfv.ms.courses.app.domain.exceptions.CourseNotFoundException;
import pe.edu.unfv.ms.courses.app.domain.models.Course;
import pe.edu.unfv.ms.courses.app.domain.models.Student;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseService implements CourseInputPort, ExternalStudentsInputPort {

    private final CoursePersistencePort coursePersistencePort;
    private final ExternalStudentsOutputPort externalStudentsOutputPort;

    @Override
    public Course findById(Long id) {
        return coursePersistencePort.findById(id)
                .orElseThrow(CourseNotFoundException::new);
    }

    @Override
    public List<Course> findAll() {
        return coursePersistencePort.findAll();
    }

    @Override
    public Course save(Course course) {
        return coursePersistencePort.save(course);
    }

    @Override
    public Course update(Long id, Course course) {
        return coursePersistencePort.findById(id)
                .map(savedCourse -> {
                    savedCourse.setName(course.getName());
                    return coursePersistencePort.save(savedCourse);
                }).orElseThrow(CourseNotFoundException::new);
    }

    @Override
    public void deleteById(Long id) {
        if(coursePersistencePort.findById(id).isEmpty()){
            throw new CourseNotFoundException();
        }
        coursePersistencePort.deleteById(id);
    }

    @Override
    public Student addStudentToCourse(Long courseId, Long studentId) {
        return externalStudentsOutputPort.addStudentToCourse(courseId, studentId);
    }

    @Override
    public Student removeStudentFromCourse(Long courseId, Long studentId) {
        return externalStudentsOutputPort.removeStudentFromCourse(courseId, studentId);
    }

    @Override
    public void removeStudentFromCollection(Long studentId) {
        externalStudentsOutputPort.removeStudentFromCollection(studentId);
    }
}
