package com.example.cruiseonspring.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value
            = {FailedToAccessException.class})
    protected ResponseEntity<Object> handleConflict(
            FailedToAccessException ex, WebRequest request) {
        System.out.println("FailedToAccessException: " + ex.getMessage());
        return handleExceptionInternal(ex, ex.getMessage(),
                new HttpHeaders(), ex.getStatus(), request);
    }

}
