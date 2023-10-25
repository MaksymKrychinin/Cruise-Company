package com.example.cruiseonspring;

import com.example.cruiseonspring.dto.AuthenticationResponse;
import com.example.cruiseonspring.dto.RegisterRequest;
import com.example.cruiseonspring.entity.Role;
import com.example.cruiseonspring.entity.User;
import com.example.cruiseonspring.mapper.UserMapper;
import com.example.cruiseonspring.repository.UserRepository;
import com.example.cruiseonspring.service.AuthenticationServiceImpl;
import com.example.cruiseonspring.service.JwtService;
import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Date;
import java.util.Set;

import static org.apache.commons.lang3.RandomStringUtils.randomNumeric;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doReturn;
import static org.mockito.MockitoAnnotations.openMocks;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest
public class AuthenticationServiceTest {
    @InjectMocks
    final AuthenticationServiceImpl authenticationService;
    final UserMapper userMapper;
    @Mock
    UserRepository userRepository;
    final JwtService jwtService;
    AutoCloseable mockClose;

    @Autowired
    public AuthenticationServiceTest(AuthenticationServiceImpl authenticationService,
                                     UserMapper userMapper,
                                     JwtService jwtService) {
        this.authenticationService = authenticationService;
        this.userMapper = userMapper;
        this.jwtService = jwtService;
    }

    @BeforeAll
    public void setUp() {
        mockClose = openMocks(this);
    }

    @AfterAll
    public void releaseMocks() throws Exception {
        mockClose.close();
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
                .userRoles(Set.of(Role.builder().name("USER").build()))
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
