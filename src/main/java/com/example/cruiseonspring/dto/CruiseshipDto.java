package com.example.cruiseonspring.dto;

import lombok.Data;

import java.sql.Date;

@Data
public class CruiseshipDto {
    private Integer id;
    private Integer capacity;
    private String routeFrom;
    private String routeTo;
    private Integer numberOfVisitedPorts;
    private Date startDate;
    private Date endDate;
    private Integer orderedSeats;
}
