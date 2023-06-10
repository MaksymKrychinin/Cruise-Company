package com.example.cruiseonspring.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.sql.Date;

@Data
public class CruiseShipDto {
    @NotNull
    private Integer id;
    @NotNull
    @Positive(message = "Capacity can't be less 0")
    private Integer capacity;
    @Size(message = "County name can't be less 2 symbols", min = 2, max = 32)
    private String routeFrom;
    @Size(message = "County name can't be less 2 symbols", min = 2, max = 32)
    private String routeTo;
    @PositiveOrZero
    private Integer numberOfVisitedPorts;
    //todo @DateLaterThanNow
    private Date startDate;
    //todo @DateLaterThanNow
    private Date endDate;
    @PositiveOrZero
    private Integer orderedSeats;
}
