package com.dekapx.apps.core.aspect;

import com.dekapx.apps.core.exception.ResourceAlreadyExistsException;
import com.dekapx.apps.core.exception.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@ControllerAdvice(annotations = RestController.class)
public class RestControllerAdvice {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiErrorResponse> resourceNotFoundException(ResourceNotFoundException exception) {
        final var apiErrorResponse = ApiErrorResponse.builder()
                .errorMessage(exception.getMessage())
                .status("NOT_FOUND")
                .timestamp(LocalDateTime.now())
                .build();
        return new ResponseEntity<>(apiErrorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ResourceAlreadyExistsException.class)
    public ResponseEntity<ApiErrorResponse> resourceAlreadyExistsException(ResourceAlreadyExistsException exception) {
        final var apiErrorResponse = ApiErrorResponse.builder()
                .errorMessage(exception.getMessage())
                .status("CONFLICT")
                .timestamp(LocalDateTime.now())
                .build();

        return new ResponseEntity<ApiErrorResponse>(apiErrorResponse, HttpStatus.CONFLICT);
    }
}
