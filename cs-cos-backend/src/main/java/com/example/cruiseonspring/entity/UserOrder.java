package com.example.cruiseonspring.entity;

import com.example.cruiseonspring.annotation.FilterFieldClass;
import com.example.cruiseonspring.annotation.FilterFieldParam;
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

    @ManyToOne(fetch = FetchType.EAGER, optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_cruise_ship", nullable = false)
    private CruiseShip cruiseShip;

    @ManyToOne(fetch = FetchType.EAGER, optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_users", nullable = false)
    private User user;

    @FilterFieldParam(ignore = true)
    @Column(name = "front_passport", nullable = false)
    private String frontPassport;

    @FilterFieldParam(ignore = true)
    @Column(name = "back_passport", nullable = false)
    private String backPassport;

    @Column(name = "status", nullable = false, length = 45)
    private String status;
}