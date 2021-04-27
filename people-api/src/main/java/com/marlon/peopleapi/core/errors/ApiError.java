package com.marlon.peopleapi.core.errors;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.List;

@AllArgsConstructor
@Builder
@Getter
public class ApiError {

    private HttpStatus httpStatus;
    private String message;
    private List<String> errors;

}
