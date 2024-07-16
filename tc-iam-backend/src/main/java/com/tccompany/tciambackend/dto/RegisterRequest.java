package com.tccompany.tciambackend.dto;


import jakarta.validation.constraints.*;
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
    @NotNull(message = "Password should not be null")
    @NotEmpty(message = "Password should not be empty")
    @Size(min = 8, message = "Password should be at least 8 characters")
    private String password;
    @NotNull(message = "Date of birthday should not be null")
    @Past(message = "Date of birthday should be in the past")
    private LocalDate dateOfBirthday;
    @NotNull(message = "Gender should not be null")
    private String gender;
    @NotNull(message = "Phone number should not be null")
    @NotEmpty(message = "Phone number should not be empty")
    @Pattern(regexp = "^(\\+\\d{1,3}[- ]?)?\\d{10}$", message = "Phone number should be valid")
    private String phoneNumber;
    @NotNull(message = "Name should not be null")
    @NotEmpty(message = "Name should not be empty")
    private String name;
    @NotNull(message = "Surname should not be null")
    @NotEmpty(message = "Surname should not be empty")
    private String surname;
    @Email(message = "Email should be valid")
    private String email;
}
