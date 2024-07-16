package com.tccompany.tcvalidation.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.http.HttpStatus;

@EqualsAndHashCode(callSuper = true)
@Data
public class ValidationException extends RuntimeException {
    private final HttpStatus httpStatus = HttpStatus.BAD_REQUEST;

    public ValidationException(String message, Throwable cause) {
        super(message, cause);
    }

    public ValidationException(String message) {
        super(message);
    }
}