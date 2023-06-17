package com.example.cruiseonspring.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@Table(name = "user-orders")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserOrder {
    @Id
    @Column(name = "id_user_orders", nullable = false)
    private Integer id;

    @MapsId("user")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_user", nullable = false, referencedColumnName = "id_user")
    private User user;

    @MapsId("cruise-ships")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_cruise_ship", nullable = false)
    private CruiseShip cruiseShip;

    @Column(name = "front_passport", nullable = false)
    private String frontPassport;

    @Column(name = "back_passport", nullable = false)
    private String backPassport;

    @Column(name = "status", nullable = false, length = 45)
    private String status;

}