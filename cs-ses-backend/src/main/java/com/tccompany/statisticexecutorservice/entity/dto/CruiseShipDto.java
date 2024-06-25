package com.tccompany.statisticexecutorservice.entity.dto;

import lombok.Builder;
import lombok.Data;

import java.sql.Date;

@Data
@Builder
public class CruiseShipDto {
    private Integer capacity;
    private String routeFrom;
    private String routeTo;
    private Integer numberOfVisitedPorts;
    private Date startDate;
    private Date endDate;
    private Integer orderedSeats;
}
