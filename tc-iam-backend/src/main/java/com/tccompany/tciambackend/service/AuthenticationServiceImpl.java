package com.tccompany.tciambackend.service;

import com.tccompany.tciambackend.dto.AuthenticationRequest;
import com.tccompany.tciambackend.dto.AuthenticationResponse;
import com.tccompany.tciambackend.dto.RegisterRequest;
import com.tccompany.tciambackend.entity.User;
import com.tccompany.tciambackend.mapper.UserMapper;
import com.tccompany.tciambackend.repository.UserRepository;
import com.tccompany.tcvalidation.exception.FailedToAccessException;
import com.tccompany.tcvalidation.utils.ValidationUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserMapper userMapper;
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final ValidationUtils validationUtils;

    @Override
    public AuthenticationResponse register(RegisterRequest request) {
        validationUtils.validate(request);
        User user = userMapper.registerRequestToUser(request);
        User save = userRepository.save(user);
        String jwtToken = jwtService.generateToken(save);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    @Override
    public AuthenticationResponse auth(AuthenticationRequest request) {
        validationUtils.validate(request);
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new FailedToAccessException("User not found by email"));
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new FailedToAccessException("Password does not match");
        }
        new UsernamePasswordAuthenticationToken(
                request.getEmail(),
                request.getPassword()
        );
        String jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }
}
