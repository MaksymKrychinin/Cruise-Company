package com.example.cruiseonspring.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class UserDto {
    private Integer id;
    private String password;
    private LocalDate dateOfBirthday;
    private String gender;
    private String phoneNumber;
    private String name;
    private String surname;
    private String email;
}
