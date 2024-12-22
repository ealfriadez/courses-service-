package pe.edu.unfv.ms.courses.app.infrastructure.adapters.input.rest.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import pe.edu.unfv.ms.courses.app.domain.models.Course;
import pe.edu.unfv.ms.courses.app.infrastructure.adapters.input.rest.models.request.CourseCreateRequest;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CourseRestMapper {

    Course toCourse(CourseCreateRequest request);
}
