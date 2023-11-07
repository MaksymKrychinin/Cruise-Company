package com.example.cruiseonspring.mapper;

import com.example.cruiseonspring.dto.RegisterRequest;
import com.example.cruiseonspring.entity.Role;
import com.example.cruiseonspring.entity.User;
import com.example.cruiseonspring.repository.RoleRepository;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Set;


@Mapper(componentModel = "spring")
public abstract class UserMapper {
    @Autowired
    protected PasswordEncoder passwordEncoder;
    @Autowired
    protected RoleRepository roleRepository;


    public abstract User registerRequestToUser(RegisterRequest registerRequest);
    @AfterMapping
    protected void encodePassword(RegisterRequest registerRequest,@MappingTarget User.UserBuilder user) {
        user.password(passwordEncoder.encode(registerRequest.getPassword()));
    }

    @AfterMapping
    protected void setDefaultRole(@MappingTarget User.UserBuilder user) {
        user.userRoles(Set.of(roleRepository.findByName("ROLE_USER")
                .orElse(Role.builder().name("ROLE_USER").build())));
    }
}