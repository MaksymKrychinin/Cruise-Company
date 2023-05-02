package com.example.cruiseonspring.entity;


import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;

@Entity
@Table(name = "cruiseships")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Cruiseship {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idcruiseShip")
    private Integer id;

    @Column(name = "capacity", nullable = false)
    private Integer capacity;
    @Column(name = "routeFrom", nullable = false)
    private String routeFrom;
    @Column(name = "routeTo", nullable = false)
    private String routeTo;
    @Column(name = "numberOfVisitedPorts", nullable = false)
    private Integer numberOfVisitedPorts;
    @Column(name = "startDate", nullable = false)
    private Date startDate;
    @Column(name = "endDate", nullable = false)
    private Date endDate;
    @Column(name = "orderedSeats", nullable = false)
    private Integer orderedSeats;
}