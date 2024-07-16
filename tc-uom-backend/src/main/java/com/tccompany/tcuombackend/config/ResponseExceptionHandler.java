package com.tccompany.tcuombackend.config;

import com.tccompany.tcvalidation.exception.FailedToAccessException;
import com.tccompany.tcvalidation.exception.NotFoundException;
import com.tccompany.tcvalidation.exception.ValidationException;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@Log4j2
public class ResponseExceptionHandler extends ResponseEntityExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(value
            = {FailedToAccessException.class})
    protected ResponseEntity<Object> handleConflictFailedToAccessException(
            FailedToAccessException ex, WebRequest request) {
        log.warn(ex.getMessage());
        return handleExceptionInternal(ex, ex.getMessage(),
                new HttpHeaders(), ex.getStatus(), request);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(value
            = {NotFoundException.class})
    protected ResponseEntity<Object> handleConflictNotFoundException(
            NotFoundException ex, WebRequest request) {
        log.warn(ex.getMessage());
        return handleExceptionInternal(ex, ex.getMessage(),
                new HttpHeaders(), ex.getStatus(), request);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(value
            = {ValidationException.class})
    protected ResponseEntity<Object> handleConflictValidationException(
            ValidationException ex, WebRequest request) {
        log.warn(ex.getMessage());
        return handleExceptionInternal(ex, ex.getMessage(),
                new HttpHeaders(), ex.getHttpStatus(), request);
    }
}
