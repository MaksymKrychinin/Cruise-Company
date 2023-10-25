package com.example.cruiseonspring.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class FailedToAccessException extends RuntimeException {
    private final HttpStatus status = HttpStatus.BAD_REQUEST;

    public FailedToAccessException(String message, Throwable cause) {
        super(message, cause);
    }

    public FailedToAccessException(String message) {
        super(message);
    }

}