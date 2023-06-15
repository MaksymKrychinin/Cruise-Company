package com.example.cruiseonspring.dto;

import lombok.Data;

@Data
public class UserOrderDto {
    private Integer id;
    private Integer idUser;
    private Integer idCruiseShip;
    private String frontPassport;
    private String backPassport;
    private String status;
}
