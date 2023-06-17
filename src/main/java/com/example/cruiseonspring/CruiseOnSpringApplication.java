package com.example.cruiseonspring;

import com.example.cruiseonspring.dto.RegisterRequest;
import com.example.cruiseonspring.entity.Role;
import com.example.cruiseonspring.entity.User;
import com.example.cruiseonspring.repository.RoleRepository;
import com.example.cruiseonspring.service.AuthenticationService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.sql.Date;

@SpringBootApplication
public class CruiseOnSpringApplication {

    public static void main(String[] args) {
        SpringApplication.run(CruiseOnSpringApplication.class, args);
    }
}
