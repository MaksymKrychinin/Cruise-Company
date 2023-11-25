package com.example.cruiseshipexecuterservice.entity.jms;


import com.example.cruiseshipexecuterservice.annotation.SubscribeClass;
import com.example.cruiseshipexecuterservice.annotation.SubscribeParam;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

import java.sql.Date;


@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@SubscribeClass
public class CruiseShip {
    @Id
    @SubscribeParam(ignore = true)
    private Integer id;
    private Integer capacity;
    private String routeFrom;
    private String routeTo;
    private Integer numberOfVisitedPorts;
    private Date startDate;
    private Date endDate;
    private Integer orderedSeats;
}