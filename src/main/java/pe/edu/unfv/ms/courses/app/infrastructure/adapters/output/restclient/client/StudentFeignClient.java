package pe.edu.unfv.ms.courses.app.infrastructure.adapters.output.restclient.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import pe.edu.unfv.ms.courses.app.domain.models.Student;

import java.util.List;

@FeignClient(name = "students-service", url = "http//localhost:8090")
public interface StudentFeignClient {

    @GetMapping("/students/{id}")
    Student findById(@PathVariable Long id);

    @GetMapping("/students/find-by-ids")
    List<Student> findByIds(@RequestParam List<Long> ids);
}
