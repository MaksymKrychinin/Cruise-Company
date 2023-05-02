package com.example.cruiseonspring.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Cruiseship not found")
public class CruiseshipNotFoundException extends RuntimeException{

    public CruiseshipNotFoundException(String message) {
        super(message);
    }
}
