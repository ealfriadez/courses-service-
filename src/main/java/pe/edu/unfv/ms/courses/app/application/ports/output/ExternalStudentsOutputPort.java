package pe.edu.unfv.ms.courses.app.application.ports.output;

import pe.edu.unfv.ms.courses.app.domain.models.Student;

public interface ExternalStudentsOutputPort {

    Student addStudentToCourse(Long courseId, Long studentId);
    Student removeStudentFromCourse(Long courseId, Long studentId);
    void removeStudentFromCollection(Long studentId);
}
