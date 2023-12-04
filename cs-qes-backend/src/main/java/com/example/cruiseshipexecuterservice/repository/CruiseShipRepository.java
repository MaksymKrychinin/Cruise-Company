package com.example.cruiseshipexecuterservice.repository;

import com.example.cruiseshipexecuterservice.entity.jms.CruiseShip;
import com.example.cruiseshipexecuterservice.entity.jms.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CruiseShipRepository extends JpaRepository<CruiseShip, Long> {

    List<CruiseShip> findAll();

    CruiseShip save(CruiseShip subscriber);
}
