package pe.edu.unfv.ms.courses.app.application.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pe.edu.unfv.ms.courses.app.application.ports.input.CourseInputPort;
import pe.edu.unfv.ms.courses.app.application.ports.input.StudentsInputPort;
import pe.edu.unfv.ms.courses.app.application.ports.output.CoursePersistencePort;
import pe.edu.unfv.ms.courses.app.application.ports.output.StudentsOutputPort;
import pe.edu.unfv.ms.courses.app.domain.exceptions.CourseNotFoundException;
import pe.edu.unfv.ms.courses.app.domain.models.Course;
import pe.edu.unfv.ms.courses.app.domain.models.Student;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CourseService implements CourseInputPort, StudentsInputPort {

    private final CoursePersistencePort coursePersistencePort;
    private final StudentsOutputPort studentsOutputPort;

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
                .map(courseDb -> {
                    courseDb.setName(course.getName());
                    return coursePersistencePort.save(courseDb);
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
        return studentsOutputPort.addStudentToCourse(courseId, studentId);
    }

    @Override
    public Student removeStudentFromCourse(Long courseId, Long studentId) {
        return studentsOutputPort.removeStudentFromCourse(courseId, studentId);
    }

    @Override
    public void removeStudentFromCollection(Long studentId) {
        studentsOutputPort.removeStudentFromCollection(studentId);
    }
}
