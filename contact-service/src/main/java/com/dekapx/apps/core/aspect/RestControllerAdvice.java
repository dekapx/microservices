package com.dekapx.apps.core.aspect;

import com.dekapx.apps.core.exception.ResourceAlreadyExistsException;
import com.dekapx.apps.core.exception.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;

@ControllerAdvice(annotations = RestController.class)
public class RestControllerAdvice {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiErrorResponse> resourceNotFoundException(ResourceNotFoundException exception) {
        final var apiErrorResponse = apiErrorFunction.apply(exception.getMessage(), "Not Found");
        return new ResponseEntity<>(apiErrorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ResourceAlreadyExistsException.class)
    public ResponseEntity<ApiErrorResponse> resourceAlreadyExistsException(ResourceAlreadyExistsException exception) {
        final var apiErrorResponse = apiErrorFunction.apply(exception.getMessage(), "Conflict");
        return new ResponseEntity<ApiErrorResponse>(apiErrorResponse, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<ApiErrorResponse>> handleValidationExceptions(MethodArgumentNotValidException exception) {
        final List<ApiErrorResponse> errors = new ArrayList<>();
        exception.getBindingResult().getAllErrors().forEach((error) -> {
            errors.add(apiErrorFunction.apply(error.getDefaultMessage(), "Bad Request"));
        });
        return new ResponseEntity<List<ApiErrorResponse>>(errors, HttpStatus.BAD_REQUEST);
    }

    private BiFunction<String, String, ApiErrorResponse> apiErrorFunction = (errorMessage, status) ->
            ApiErrorResponse.builder()
                    .errorMessage(errorMessage)
                    .status(status)
                    .timestamp(LocalDateTime.now())
                    .build();

}
