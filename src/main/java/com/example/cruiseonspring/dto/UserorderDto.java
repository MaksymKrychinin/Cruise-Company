package com.example.cruiseonspring.dto;

import lombok.Data;

@Data
public class UserorderDto {
    private Integer id;
    private UserDto idUser;
    private CruiseshipDto idCruiseShip;
    private String frontPassport;
    private String backPassport;
    private String status;
}
