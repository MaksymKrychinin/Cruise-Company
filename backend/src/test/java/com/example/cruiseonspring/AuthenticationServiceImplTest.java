package com.example.cruiseonspring;

import com.example.cruiseonspring.Utils.ValidationUtils;
import com.example.cruiseonspring.dto.AuthenticationRequest;
import com.example.cruiseonspring.dto.AuthenticationResponse;
import com.example.cruiseonspring.dto.RegisterRequest;
import com.example.cruiseonspring.entity.User;
import com.example.cruiseonspring.exception.FailedToAccessException;
import com.example.cruiseonspring.mapper.UserMapper;
import com.example.cruiseonspring.repository.UserRepository;
import com.example.cruiseonspring.service.AuthenticationServiceImpl;
import com.example.cruiseonspring.service.JwtService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@PropertySource("classpath:application-test.yml")
public class AuthenticationServiceImplTest {

    @InjectMocks
    private AuthenticationServiceImpl authenticationService;

    @Mock
    private UserMapper userMapper;

    @Mock
    private UserRepository userRepository;

    @Mock
    private JwtService jwtService;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private ValidationUtils validationUtils;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void registerTest() {
        RegisterRequest request = RegisterRequest.builder()
                .email("test@example.com")
                .password("password123")
                .dateOfBirthday(LocalDate.of(1990, 1, 1))
                .gender("male")
                .phoneNumber("+380671234567")
                .name("John")
                .surname("Doe")
                .build();
        User user = User.builder()
                .email("test@example.com")
                .password("password123")
                .dateOfBirthday(LocalDate.of(1990, 1, 1))
                .gender("male")
                .phoneNumber("+380671234567")
                .name("John")
                .surname("Doe")
                .build();
        when(userMapper.registerRequestToUser(request)).thenReturn(user);
        when(jwtService.generateToken(user)).thenReturn("token");

        AuthenticationResponse response = authenticationService.register(request);

        verify(validationUtils, times(1)).validate(request);
        verify(userRepository, times(1)).save(user);
        assertEquals("token", response.getToken());
    }

    @Test
    public void authTest() {
        AuthenticationRequest request =
                AuthenticationRequest.builder()
                        .email("test@example.com")
                        .password("password123").build();
        User user = User.builder()
                .email("test@example.com")
                .password("password123")
                .dateOfBirthday(LocalDate.of(1990, 1, 1))
                .gender("male")
                .phoneNumber("+380671234567")
                .name("John")
                .surname("Doe")
                .build();
        user.setPassword("encodedPassword");
        request.setPassword("password");
        when(userRepository.findByEmail("test@example.com")).thenReturn(Optional.of(user));
        when(passwordEncoder.matches(anyString(), anyString())).thenReturn(true);
        when(jwtService.generateToken(user)).thenReturn("token");

        AuthenticationResponse response = authenticationService.auth(request);

        verify(validationUtils, times(1)).validate(request);
        assertEquals("token", response.getToken());
    }

    @Test
    public void authTestFailedToAccess() {
        AuthenticationRequest request = new AuthenticationRequest();
        when(userRepository.findByEmail(anyString())).thenReturn(Optional.empty());

        assertThrows(FailedToAccessException.class, () -> authenticationService.auth(request));
    }
}