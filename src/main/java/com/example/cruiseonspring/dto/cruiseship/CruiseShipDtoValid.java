package com.example.cruiseonspring.dto.cruiseship;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.sql.Date;
@Data
public class CruiseShipDtoValid {
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
