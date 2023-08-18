package com.example.cruiseonspring;

import com.example.cruiseonspring.config.ApplicationConfig;
import com.example.cruiseonspring.dto.AuthenticationResponse;
import com.example.cruiseonspring.dto.RegisterRequest;
import com.example.cruiseonspring.entity.Role;
import com.example.cruiseonspring.entity.User;
import com.example.cruiseonspring.mapper.UserMapper;
import com.example.cruiseonspring.repository.UserRepository;
import com.example.cruiseonspring.service.AuthenticationService;
import com.example.cruiseonspring.service.AuthenticationServiceImpl;
import com.example.cruiseonspring.service.JwtService;
import com.mysql.cj.log.Log;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.AuthenticationManager;

import java.sql.Date;
import java.util.*;

import static org.apache.commons.lang3.RandomStringUtils.randomNumeric;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doReturn;
import static org.mockito.MockitoAnnotations.openMocks;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest
public class AuthenticationServiceTest {
    @Autowired
    AuthenticationService authenticationService;
    @Autowired
    UserMapper userMapper;
    @Mock
    UserRepository userRepository;
    @Autowired
    JwtService jwtService;
    @Autowired
    AuthenticationManager authenticationManager;
    @BeforeAll
    public void setUp() {
        openMocks(this);
        authenticationService =
                new AuthenticationServiceImpl(userMapper, userRepository, jwtService, authenticationManager);
    }


    @Test
    @DisplayName("JWT_Token_test")
    void register_token_check() {
        RegisterRequest registerRequest = getRegisterRequest("Ivan");
        User userAfterMapper = userMapper.apply(registerRequest);
        User userFromRequest = getUserByRegisterRequest(registerRequest);
        doReturn(userFromRequest).when(userRepository).save(userAfterMapper);
        AuthenticationResponse authenticationResponse = authenticationService.register(registerRequest);
        String token = authenticationResponse.getToken();
        boolean tokenValid = jwtService.isTokenValid(token, userFromRequest);
        //RepositoryWillChangeObject assertEquals(userAfterMapper,userFromRequest);
        System.out.println(tokenValid);
        assertTrue(tokenValid);
    }

    private User getUserByRegisterRequest(RegisterRequest registerRequest) {
        return User.builder()
                .gender(registerRequest.getGender())
                .phoneNumber(registerRequest.getPhoneNumber())
                .dateOfBirthday(registerRequest.getDateOfBirthday())
                .name(registerRequest.getName())
                .email(registerRequest.getEmail())
                .password(registerRequest.getPassword())
                .surname(registerRequest.getSurname())
                .userRole(Role.builder().name("USER").build())
                .id(1)
                .build();
    }

    private RegisterRequest getRegisterRequest(String name) {
        return RegisterRequest.builder()
                .gender("Male")
                .phoneNumber("+380" + randomNumeric(9))
                .dateOfBirthday(new Date(System.currentTimeMillis()).toLocalDate())
                .name("name")
                .email(name + "@gmail.com")
                .password(name + randomNumeric(5))
                .surname("Sur_" + name)
                .build();
    }
}
