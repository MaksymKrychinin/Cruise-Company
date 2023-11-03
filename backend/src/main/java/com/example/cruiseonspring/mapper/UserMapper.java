package com.example.cruiseonspring.mapper;

import com.example.cruiseonspring.dto.RegisterRequest;
import com.example.cruiseonspring.entity.Role;
import com.example.cruiseonspring.entity.User;
import com.example.cruiseonspring.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.function.Function;

@Component
@RequiredArgsConstructor
public class UserMapper implements Function<RegisterRequest, User> {
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    @Override
    public User apply(RegisterRequest request) {
        return User.builder()
                .email(request.getEmail())
                .dateOfBirthday(request.getDateOfBirthday())
                .gender(request.getGender())
                .name(request.getName())
                .surname(request.getSurname())
                .phoneNumber(request.getPhoneNumber())
                .password(passwordEncoder.encode(request.getPassword()))
                .userRoles(Set.of(roleRepository.findByName("USER")
                        .orElse(Role.builder().name("USER").build())))
                .build();
    }
}
