package pe.edu.unfv.ms.courses.app.infrastructure.adapters.input.rest;

import feign.FeignException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import pe.edu.unfv.ms.courses.app.domain.exceptions.CourseNotFoundException;
import pe.edu.unfv.ms.courses.app.domain.exceptions.StudentNotFoundException;
import pe.edu.unfv.ms.courses.app.infrastructure.adapters.input.rest.models.enums.ErrorType;
import pe.edu.unfv.ms.courses.app.infrastructure.adapters.input.rest.models.response.ErrorResponse;

import java.time.LocalDate;
import java.util.Collections;

import static pe.edu.unfv.ms.courses.app.infrastructure.adapters.input.rest.models.enums.ErrorType.*;
import static pe.edu.unfv.ms.courses.app.infrastructure.adapters.input.rest.models.enums.ErrorType.SYSTEM;
import static pe.edu.unfv.ms.courses.app.infrastructure.adapters.utils.CourseErrorCatalog.*;

@Slf4j
@RestControllerAdvice
public class GlobalControllerAdvice {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(CourseNotFoundException.class)
    public ErrorResponse handleCourseNotFoundException(){

        log.error("Course not found");

        return ErrorResponse.builder()
                .code(COURSE_NOT_FOUND.getCode())
                .errorType(FUNCTIONAL)
                .genericMessage(COURSE_NOT_FOUND.getGenericMessage())
                .timestamp(LocalDate.now().toString())
                .build();
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(StudentNotFoundException.class)
    public ErrorResponse handleStudentNotFoundException(){

        log.error("Student not found.");

        return ErrorResponse.builder()
                .code(STUDENT_NOT_FOUND.getCode())
                .errorType(FUNCTIONAL)
                .genericMessage(STUDENT_NOT_FOUND.getGenericMessage())
                .timestamp(LocalDate.now().toString())
                .build();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ErrorResponse handleMethodArgumentNotValidException(
            MethodArgumentNotValidException e){

        log.error("Invalid arguments.");

        BindingResult result = e.getBindingResult();
        return ErrorResponse.builder()
                .code(COURSE_BAD_PARAMETERS.getCode())
                .errorType(FUNCTIONAL)
                .genericMessage(COURSE_BAD_PARAMETERS.getGenericMessage())
                .details(result.getFieldErrors().stream()
                        .map(DefaultMessageSourceResolvable::getDefaultMessage)
                        .toList())
                .timestamp(LocalDate.now().toString())
                .build();
    }

    @ExceptionHandler(FeignException.class)
    public ResponseEntity<ErrorResponse> handleFeignException(FeignException e){
        ErrorResponse errorResponse = ErrorResponse.builder()
                .code(WEB_CLIENT_ERROR.getCode())
                .errorType(FUNCTIONAL)
                .genericMessage(WEB_CLIENT_ERROR.getGenericMessage())
                .details(Collections.singletonList(e.getMessage()))
                .timestamp(LocalDate.now().toString())
                .build();

        return ResponseEntity.status(e.status())
                .body(errorResponse);
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ErrorResponse handleInternalServerError(Exception e){

        log.error("Error server internal.");

        return ErrorResponse.builder()
                .code(INTERNAL_SERVER_ERROR.getCode())
                .errorType(SYSTEM)
                .genericMessage(INTERNAL_SERVER_ERROR.getGenericMessage())
                .details(Collections.singletonList(e.getMessage()))
                .timestamp(LocalDate.now().toString())
                .build();
    }
}
