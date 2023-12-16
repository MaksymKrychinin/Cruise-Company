package com.example.cruiseonspring.entity;

import com.example.cruiseonspring.annotation.FilterFieldClass;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@Table(name = "users-orders")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@FilterFieldClass(idIgnore = true)
public class UserOrder {
    @Id
    @Column(name = "id_users_orders", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_user", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY, optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_cruise_ship", nullable = false)
    private CruiseShip cruiseShip;

    @Column(name = "front_passport", nullable = false)
    private String frontPassport;

    @Column(name = "back_passport", nullable = false)
    private String backPassport;

    @Column(name = "status", nullable = false, length = 45)
    private String status;

}