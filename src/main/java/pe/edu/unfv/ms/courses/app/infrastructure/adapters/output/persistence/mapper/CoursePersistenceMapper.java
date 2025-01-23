package pe.edu.unfv.ms.courses.app.infrastructure.adapters.output.persistence.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import pe.edu.unfv.ms.courses.app.domain.models.Course;
import pe.edu.unfv.ms.courses.app.infrastructure.adapters.output.persistence.models.CourseEntity;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CoursePersistenceMapper {

    Course toCourse(CourseEntity entity);

    CourseEntity toCourseEntity(Course course);

    List<Course> toCourses(List<CourseEntity> entities);
}
