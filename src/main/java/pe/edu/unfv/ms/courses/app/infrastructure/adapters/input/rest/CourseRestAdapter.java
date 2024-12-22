package pe.edu.unfv.ms.courses.app.infrastructure.adapters.input.rest;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.unfv.ms.courses.app.application.ports.input.CourseInputPort;
import pe.edu.unfv.ms.courses.app.application.ports.input.StudentsInputPort;
import pe.edu.unfv.ms.courses.app.domain.models.Course;
import pe.edu.unfv.ms.courses.app.domain.models.Student;
import pe.edu.unfv.ms.courses.app.infrastructure.adapters.input.rest.mapper.CourseRestMapper;
import pe.edu.unfv.ms.courses.app.infrastructure.adapters.input.rest.models.request.CourseCreateRequest;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/courses")
public class CourseRestAdapter {

    private final CourseInputPort courseInputPort;
    private final CourseRestMapper courseRestMapper;
    private final StudentsInputPort studentsInputPort;

    @GetMapping
    public List<Course> findAll(){
        return courseInputPort.findAll();
    }

    @GetMapping
    public Course findById(@PathVariable Long id){
        return courseInputPort.findById(id);
    }

    @PostMapping
    public ResponseEntity<Course> save(@Valid @RequestBody CourseCreateRequest request){
        Course course = courseInputPort.save(courseRestMapper.toCourse(request));
        return ResponseEntity
                .created(URI.create("/courses/".concat(course.getId().toString())))
                .body(course);
    }

    @PutMapping("/{id}")
    public Course update(@PathVariable Long id,
                                 @Valid @RequestBody CourseCreateRequest request){
        return courseInputPort.update(id, courseRestMapper.toCourse(request));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id){
        courseInputPort.deleteById(id);
    }

    @PutMapping("/course/{courseId}/student/{studentId}")
    public Student addStudentToCourse(@PathVariable Long courseId, @PathVariable Long studentId){
        return studentsInputPort.addStudentToCourse(courseId, studentId);
    }

    @DeleteMapping("/course/{courseId}/student/{studentId}")
    public Student removeStudentFromCourse(@PathVariable Long courseId, @PathVariable Long studentId){
        return studentsInputPort.removeStudentFromCourse(courseId, studentId);
    }

    @DeleteMapping("/remove-student-from-collection/{studentId}")
    public void removeStudentFromCollection(@PathVariable Long studentId){
        studentsInputPort.removeStudentFromCollection(studentId);
    }
}
