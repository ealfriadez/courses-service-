package pe.edu.unfv.ms.courses.app.domain.models;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Course {

    private Long id;
    private String name;
    private List<Student> students;
}
