package pe.edu.unfv.ms.courses.app.infrastructure.adapters.input.rest.models.request;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CourseCreateRequest {

    @NotBlank(message = "Field name cannot be null or empty")
    private String name;
}
