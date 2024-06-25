package com.tccompany.statisticexecutorservice.repository;

import com.tccompany.statisticexecutorservice.entity.jms.CruiseShip;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CruiseShipRepository extends JpaRepository<CruiseShip, Long> {

    List<CruiseShip> findAll();

    CruiseShip save(CruiseShip subscriber);
}
