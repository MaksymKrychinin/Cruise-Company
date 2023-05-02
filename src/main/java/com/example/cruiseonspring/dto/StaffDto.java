package com.example.cruiseonspring.dto;

import lombok.Data;

@Data
public class StaffDto {
    private Integer id;
    private RoleDto role;
    private String name;
    private String onCruiseNumber;
}
