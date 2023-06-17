package com.example.cruiseonspring;


import com.example.cruiseonspring.repository.RoleRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;


@Component
@AllArgsConstructor
public class Main implements CommandLineRunner {
    RoleRepository roleRepository;
    PasswordEncoder passwordEncoder;
    @Override
    public void run(String... args) throws InterruptedException {

    }
}
