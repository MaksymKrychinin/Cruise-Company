package com.example.cruiseonspring.mapper;

import com.example.cruiseonspring.config.ApplicationConfig;
import com.example.cruiseonspring.dto.RegisterRequest;
import com.example.cruiseonspring.entity.Role;
import com.example.cruiseonspring.entity.User;
import com.example.cruiseonspring.repository.RoleRepository;
import org.mapstruct.*;
import org.mapstruct.control.MappingControl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Set;


@Mapper(componentModel = "spring", uses = {RoleRepository.class, ApplicationConfig.class})
public abstract class UserMapper {
    @Autowired
    protected PasswordEncoder passwordEncoder;
    @Autowired
    protected RoleRepository roleRepository;


    public abstract User registerRequestToUser(RegisterRequest registerRequest);

    public abstract RegisterRequest userToRegisterRequest(User user);

    @AfterMapping
    protected void encodePassword(RegisterRequest registerRequest,@MappingTarget User.UserBuilder user) {
        user.password(passwordEncoder.encode(registerRequest.getPassword()));
    }

    @AfterMapping
    protected void setDefaultRole(@MappingTarget User.UserBuilder user) {
        user.userRoles(Set.of(roleRepository.findByName("USER")
                .orElse(Role.builder().name("USER").build())));
    }
}