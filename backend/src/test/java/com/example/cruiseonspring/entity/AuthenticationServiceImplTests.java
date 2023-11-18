package com.example.cruiseonspring.entity;

import com.example.cruiseonspring.dto.AuthenticationRequest;
import com.example.cruiseonspring.dto.AuthenticationResponse;
import com.example.cruiseonspring.dto.RegisterRequest;
import com.example.cruiseonspring.exception.FailedToAccessException;
import com.example.cruiseonspring.exception.ValidationException;
import com.example.cruiseonspring.service.AuthenticationService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.PropertySource;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@PropertySource("classpath:application-test.yml")
public class AuthenticationServiceImplTests {

    @Autowired
    private AuthenticationService authenticationService;

    @Test
    public void registerNewUser() {
        RegisterRequest registerRequest = RegisterRequest.builder()
                .email("test@example.com")
                .password("password123")
                .dateOfBirthday(LocalDate.of(1990, 1, 1))
                .gender("male")
                .phoneNumber("+380671234567")
                .name("John")
                .surname("Doe")
                .build();

        AuthenticationResponse authenticationResponse = authenticationService.register(registerRequest);

        assertNotNull(authenticationResponse);
        assertNotNull(authenticationResponse.getToken());
    }

    @Test
    public void authenticateExistingUser() {
        AuthenticationRequest authenticationRequest = AuthenticationRequest.builder()
                .email("test@example.com")
                .password("password123")
                .build();

        AuthenticationResponse authenticationResponse = authenticationService.auth(authenticationRequest);

        assertNotNull(authenticationResponse);
        assertNotNull(authenticationResponse.getToken());
    }

    @Test
    public void failToRegisterUserWithInvalidEmail() {
        RegisterRequest registerRequest = RegisterRequest.builder()
                .email("invalid@example")
                .password("password123")
                .dateOfBirthday(LocalDate.of(1990, 1, 1))
                .gender("male")
                .phoneNumber("+380671234567")
                .name("John")
                .surname("Doe")
                .build();

        assertThrows(ValidationException.class, () -> authenticationService.register(registerRequest));
    }

    @Test
    public void failToRegisterUserWithInvalidPassword() {
        RegisterRequest registerRequest = RegisterRequest.builder()
                .email("test@example.com")
                .password("short")
                .dateOfBirthday(LocalDate.of(1990, 1, 1))
                .gender("male")
                .phoneNumber("+380671234567")
                .name("John")
                .surname("Doe")
                .build();

        assertThrows(ValidationException.class, () -> authenticationService.register(registerRequest));
    }

    @Test
    public void failToAuthenticateUserWithInvalidCredentials() {
        AuthenticationRequest authenticationRequest = AuthenticationRequest.builder()
                .email("test@example.com")
                .password("wrongpassword")
                .build();

        assertThrows(FailedToAccessException.class, () -> authenticationService.auth(authenticationRequest));
    }
}