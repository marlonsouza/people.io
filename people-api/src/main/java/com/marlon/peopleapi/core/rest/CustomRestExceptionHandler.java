package com.marlon.peopleapi.core.rest;

import com.marlon.peopleapi.core.errors.ApiError;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.persistence.EntityExistsException;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class CustomRestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({ConstraintViolationException.class})
    public ResponseEntity<Object> handleConstraintViolation(ConstraintViolationException ex, WebRequest req){
        List<String> errors = ex.getConstraintViolations().stream()
                .map(ConstraintViolation::getMessage)
                .collect(Collectors.toList());

        ApiError apiError = ApiError.builder()
                                .httpStatus(HttpStatus.BAD_REQUEST)
                                .message(ex.getLocalizedMessage())
                                .errors(errors)
                                .build();

        return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getHttpStatus());
    }

    @ExceptionHandler({EntityExistsException.class})
    public ResponseEntity<Object> handleEntityExistsException(EntityExistsException ex, WebRequest req){
        ApiError apiError = ApiError.builder()
                .httpStatus(HttpStatus.BAD_REQUEST)
                .message(ex.getLocalizedMessage())
                .errors(Arrays.asList(ex.getMessage()))
                .build();

        return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getHttpStatus());
    }
}
