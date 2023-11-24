package com.example.cruiseshipexecuterservice.entity.jms;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;

@Entity
@Getter
public class User {
    @Id
    String id;
    String email;
    String phoneNumber;
}
