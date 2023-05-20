package com.example.cruiseonspring.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Entity
@Table(name = "userorders")
@ToString
public class Userorder {
    @Id
    @Column(name = "idUserOrders", nullable = false)
    private Integer id;

    @MapsId("idUser")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "idUser", nullable = false, referencedColumnName = "idusers")
    private User user;

    @MapsId("idCruiseShip")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "idCruiseShip", nullable = false)
    private Cruiseship cruiseShip;

    @Column(name = "frontPassport", nullable = false)
    private String frontPassport;

    @Column(name = "backPassport", nullable = false)
    private String backPassport;

    @Column(name = "status", nullable = false, length = 45)
    private String status;

}