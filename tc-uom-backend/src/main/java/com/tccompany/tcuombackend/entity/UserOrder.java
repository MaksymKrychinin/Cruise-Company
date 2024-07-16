package com.tccompany.tcuombackend.entity;

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
public class UserOrder {
    @Id
    @Column(name = "id_users_orders", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "front_passport", nullable = false)
    private String frontPassport;

    @Column(name = "back_passport", nullable = false)
    private String backPassport;

    @Column(name = "status", nullable = false, length = 45)
    private String status;
}