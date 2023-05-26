package com.example.cruiseonspring.service;

import com.example.cruiseonspring.dto.cruiseship.CruiseShipDtoForUser;
import com.example.cruiseonspring.dto.cruiseship.CruiseShipDtoValid;
import com.example.cruiseonspring.entity.CruiseShip;

import java.util.List;

public interface CruiseShipService {
    List<CruiseShipDtoForUser> getAllCruiseShips();

    CruiseShipDtoForUser getCruiseShipById(Integer id);

    CruiseShip saveCruiseShip(CruiseShipDtoValid cruiseShip);

    CruiseShip updateCruiseShip(CruiseShipDtoValid cruiseShip);

    void deleteCruiseShip(Integer id);
}
