package com.example.cruiseonspring.dto;


import com.example.cruiseonspring.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegisterRequest {
    private String password;
    private LocalDate dateOfBirthday;
    private String gender;
    private String phoneNumber;
    private String name;
    private String surname;
    private String email;
}
