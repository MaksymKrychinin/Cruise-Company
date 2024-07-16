package com.tccompany.tciambackend.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {
    private Integer id;
    private Set<RoleDto> userRoles;
    private String password;
    private LocalDate dateOfBirthday;
    private String gender;
    private String phoneNumber;
    private String name;
    private String surname;
    private String email;
}