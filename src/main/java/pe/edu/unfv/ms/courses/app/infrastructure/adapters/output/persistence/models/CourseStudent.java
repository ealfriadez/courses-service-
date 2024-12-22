package pe.edu.unfv.ms.courses.app.infrastructure.adapters.output.persistence.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@Table(name = "course_student")
@AllArgsConstructor
@NoArgsConstructor
public class CourseStudent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "student_id")
    private Long studentId;

    @Override
    public boolean equals(Object object){
        if(this == object){
            return true;
        }

        if(!(object instanceof CourseStudent courseStudent)){
            return false;
        }

        return this.studentId != null && this.studentId.equals(courseStudent.studentId);
    }
}
