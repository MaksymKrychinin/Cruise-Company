package com.example.cruiseonspring.dto;

import com.example.cruiseonspring.entity.CruiseShip;
import com.example.cruiseonspring.entity.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserOrderDto {
    private Integer id;
    private User user;
    private CruiseShip cruiseShip;
    private String frontPassport;
    private String backPassport;
    private String status;
}
