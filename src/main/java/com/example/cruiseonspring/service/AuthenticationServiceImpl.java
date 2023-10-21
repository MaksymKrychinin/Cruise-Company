package com.example.cruiseonspring.service;

import com.example.cruiseonspring.dto.AuthenticationRequest;
import com.example.cruiseonspring.dto.AuthenticationResponse;
import com.example.cruiseonspring.dto.RegisterRequest;
import com.example.cruiseonspring.entity.User;
import com.example.cruiseonspring.exception.NotFoundException;
import com.example.cruiseonspring.mapper.UserMapper;
import com.example.cruiseonspring.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    UserMapper userMapper;
    UserRepository userRepository;
    JwtService jwtService;
    AuthenticationManager authenticationManager;

    @Override
    public AuthenticationResponse register(RegisterRequest request) {
        User user = userMapper.apply(request);
        System.out.println("User: " + user.toString());
        userRepository.save(user);
        String jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    @Override
    public AuthenticationResponse auth(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword())
        );
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() ->
                        NotFoundException
                                .builder()
                                .message("User not found by email")
                                .httpStatus(HttpStatus.NOT_FOUND)
                                .build());
        String jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }
}
