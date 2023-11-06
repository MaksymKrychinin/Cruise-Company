package com.example.cruiseonspring.exception;

import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.Level;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@Log4j2
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value
            = {FailedToAccessException.class})
    protected ResponseEntity<Object> handleConflictFailedToAccessException(
            FailedToAccessException ex, WebRequest request) {
        log.log(Level.INFO, ex.getMessage());
        return handleExceptionInternal(ex, ex.getMessage(),
                new HttpHeaders(), ex.getStatus(), request);
    }

    @ExceptionHandler(value
            = {NotFoundException.class})
    protected ResponseEntity<Object> handleConflictNotFoundException(
            NotFoundException ex, WebRequest request) {
        log.log(Level.INFO, ex.getMessage());
        return handleExceptionInternal(ex, ex.getMessage(),
                new HttpHeaders(), ex.getStatus(), request);
    }

    @ExceptionHandler(value
            = {ValidationException.class})
    protected ResponseEntity<Object> handleConflictValidationException(
            ValidationException ex, WebRequest request) {
        log.log(Level.INFO, ex.getMessage());
        return handleExceptionInternal(ex, ex.getMessage(),
                new HttpHeaders(), ex.getHttpStatus(), request);
    }

    @ExceptionHandler(value
            = {Exception.class})
    protected ResponseEntity<Object> handleConflictException(
            Exception ex, WebRequest request) {
        log.log(Level.WARN, ex.getMessage());
        return handleExceptionInternal(ex, ex.getMessage(),
                new HttpHeaders(), HttpStatusCode.valueOf(404), request);
    }
}
