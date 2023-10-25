package com.example.cruiseonspring.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class FailedToAccessException extends RuntimeException {
    private HttpStatus status = HttpStatus.NOT_ACCEPTABLE;

    public FailedToAccessException(String message, Throwable cause) {
        super(message, cause);
    }

    public FailedToAccessException(String message) {
        super(message);
    }

}