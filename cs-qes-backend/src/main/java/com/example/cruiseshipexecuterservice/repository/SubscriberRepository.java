package com.example.cruiseshipexecuterservice.repository;

import com.example.cruiseshipexecuterservice.entity.Subscriber;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SubscriberRepository extends JpaRepository<Subscriber, Long> {


    List<Subscriber> findAll();

    Subscriber save(Subscriber subscriber);
}
