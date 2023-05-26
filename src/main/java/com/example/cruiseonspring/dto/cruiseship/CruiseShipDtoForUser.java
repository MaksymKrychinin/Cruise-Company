package com.example.cruiseonspring.dto.cruiseship;

import lombok.Data;

import java.sql.Date;

@Data
public class CruiseShipDtoForUser {
    private Integer id;
    private Integer capacity;
    private String routeFrom;
    private String routeTo;
    private Integer numberOfVisitedPorts;
    private Date startDate;
    private Date endDate;
    private Integer orderedSeats;
}
