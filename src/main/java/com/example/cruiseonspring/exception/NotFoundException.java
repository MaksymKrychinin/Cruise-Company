package com.example.cruiseonspring.exception;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Data
@Builder
public class NotFoundException extends RuntimeException {
    private final String message;
    private final HttpStatus httpStatus;
}