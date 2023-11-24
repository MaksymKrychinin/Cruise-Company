package com.example.cruiseonspring.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CruiseShipDto {
    @NotNull
    @Positive(message = "Capacity can't be less 0")
    private Integer capacity;
    @Size(message = "County name can't be less 2 symbols", min = 2, max = 32)
    private String routeFrom;
    @Size(message = "County name can't be less 2 symbols", min = 2, max = 32)
    private String routeTo;
    @PositiveOrZero
    private Integer numberOfVisitedPorts;
    @Future
    private LocalDate startDate;
    @Future
    private LocalDate endDate;
    @PositiveOrZero
    private Integer orderedSeats;
}
