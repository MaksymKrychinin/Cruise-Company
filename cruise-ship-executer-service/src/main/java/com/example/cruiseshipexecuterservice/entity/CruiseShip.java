package com.example.cruiseshipexecuterservice.entity;


import lombok.*;

import java.sql.Date;


@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CruiseShip {
    private Integer id;
    private Integer capacity;
    private String routeFrom;
    private String routeTo;
    private Integer numberOfVisitedPorts;
    private Date startDate;
    private Date endDate;
    private Integer orderedSeats;
}