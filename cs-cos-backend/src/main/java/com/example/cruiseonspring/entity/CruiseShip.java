package com.example.cruiseonspring.entity;


import annotations.FilterFieldClass;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "cruise-ships")
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FilterFieldClass(idIgnore = true)
public class CruiseShip {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cruise_ship")
    private Integer id;
    @Column(name = "capacity", nullable = false)
    private Integer capacity;
    @Column(name = "route_from", nullable = false)
    private String routeFrom;
    @Column(name = "route_to", nullable = false)
    private String routeTo;
    @Column(name = "number_of_visited_ports", nullable = false)
    private Integer numberOfVisitedPorts;
    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;
    @Column(name = "end_date", nullable = false)
    private LocalDate endDate;
    @Column(name = "ordered_seats", nullable = false)
    private Integer orderedSeats;
}