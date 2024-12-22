package pe.edu.unfv.ms.courses.app.infrastructure.adapters.output.persistence.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import pe.edu.unfv.ms.courses.app.infrastructure.adapters.output.persistence.models.CourseEntity;

public interface CourseJpaRepository extends CrudRepository<CourseEntity, Long> {

    @Query("DELETE FROM CourseStudent cs WHERE cs.studentId = ?1")
    void deleteCourseStudentByStudentId(Long studentId);
}
