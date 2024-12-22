package pe.edu.unfv.ms.courses.app.infrastructure.adapters.output.restclient;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pe.edu.unfv.ms.courses.app.application.ports.output.StudentsOutputPort;
import pe.edu.unfv.ms.courses.app.domain.exceptions.CourseNotFoundException;
import pe.edu.unfv.ms.courses.app.domain.models.Student;
import pe.edu.unfv.ms.courses.app.infrastructure.adapters.output.persistence.models.CourseStudent;
import pe.edu.unfv.ms.courses.app.infrastructure.adapters.output.persistence.repository.CourseJpaRepository;
import pe.edu.unfv.ms.courses.app.infrastructure.adapters.output.restclient.client.StudentFeignClient;

import java.util.List;

@Component
@RequiredArgsConstructor
public class StudentRestClientAdapter implements StudentsOutputPort {

    private final StudentFeignClient client;
    private final CourseJpaRepository repository;

    @Override
    public Student addStudentToCourse(Long courseId, Long studentId) {
        return repository.findById(courseId)
                .map(courseEntity -> {
                    Student student = client.findById(studentId);   //FeignException -> 404, 400, 500
                    CourseStudent courseStudent = new CourseStudent();
                    courseStudent.setStudentId(student.getId());
                    courseEntity.addCourseStudent(courseStudent);
                    repository.save(courseEntity);
                    return student;
                })
                .orElseThrow(CourseNotFoundException::new);
    }

    @Override
    public Student removeStudentFromCourse(Long courseId, Long studentId) {
        return repository.findById(courseId)
                .map(courseEntity -> {
                    Student student = client.findById(studentId);
                    CourseStudent courseStudent = new CourseStudent();
                    courseStudent.setId(student.getId());
                    courseEntity.removeCourseStudent(courseStudent);
                    repository.save(courseEntity);
                    return student;
                }).orElseThrow(CourseNotFoundException::new);
    }

    @Override
    public void removeStudentFromCollection(Long studentId) {
        repository.deleteCourseStudentByStudentId(studentId);
    }
}
