package com.example.cruiseonspring.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "User Order not found")
public class UserOrderNotFountException extends RuntimeException{

    public UserOrderNotFountException(String message) {
        super(message);
    }
}
