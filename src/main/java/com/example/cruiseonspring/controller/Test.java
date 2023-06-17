package com.example.cruiseonspring.controller;

import com.example.cruiseonspring.dto.RegisterRequest;
import com.example.cruiseonspring.service.AuthenticationService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;

@RestController
@RequestMapping("/test")
@AllArgsConstructor
public class Test {
    AuthenticationService service;
    @GetMapping("/")
    public String getAdminToken(){
        var admin = RegisterRequest.builder()
                .name("Admin")
                .surname("Admin")
                .email("admin@mail.com")
                .password("password")
                .dateOfBirthday(new Date(System.currentTimeMillis()).toLocalDate())
                .phoneNumber("+380995405401")
                .gender("Male")
                .build();
        String adminToken = "Admin token: " + service.register(admin).getToken();
        System.out.println(adminToken);
        return adminToken;
    }
}
