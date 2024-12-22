package pe.edu.unfv.ms.courses.app.application.ports.input;

import pe.edu.unfv.ms.courses.app.domain.models.Student;

import java.util.Optional;

public interface StudentsInputPort {

    Student addStudentToCourse(Long courseId, Long studentId);
    Student removeStudentFromCourse(Long courseId, Long studentId);
    void removeStudentFromCollection(Long studentId);
}
