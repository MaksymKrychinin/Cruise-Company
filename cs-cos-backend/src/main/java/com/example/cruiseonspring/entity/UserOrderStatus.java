package com.example.cruiseonspring.entity;

import jakarta.persistence.*;
import lombok.*;

/*
@Getter
@Setter
@Entity
@Table(name = "users-orders-status")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserOrderStatus {

    @Id
    @Column(name = "id_users_orders_status", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private Status status;
    @Column(name = "description")
    private String Description;
    @Column(name = "id_users_orders", nullable = false)
    @OneToOne(fetch = FetchType.LAZY, optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_users_orders", nullable = false)
    private UserOrder userOrder;
    enum Status {
        PENDING,
        APPROVED,
        REJECTED,
        PRIZED
    }
}
*/
