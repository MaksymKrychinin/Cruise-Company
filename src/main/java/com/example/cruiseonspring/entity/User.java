package com.example.cruiseonspring.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "users")
@ToString
public class User {
    @Id
    @Column(name = "idusers", nullable = false)
    private Integer id;

    @MapsId("role")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "role", nullable = false)
    private Role role;

    @Column(name = "password", nullable = false, length = 45)
    private String password;

    @Column(name = "dateOfBirthday", nullable = false)
    private LocalDate dateOfBirthday;

    @Column(name = "gender", nullable = false, length = 45)
    private String gender;

    @Column(name = "phoneNumber", nullable = false, length = 45)
    private String phoneNumber;

    @Column(name = "name", nullable = false, length = 45)
    private String name;

    @Column(name = "surname", nullable = false, length = 45)
    private String surname;

    @Column(name = "email", nullable = false, length = 45)
    private String email;

}