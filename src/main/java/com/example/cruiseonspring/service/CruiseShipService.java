package com.example.cruiseonspring.service;

import com.example.cruiseonspring.dto.CruiseShipDto;
import com.example.cruiseonspring.entity.CruiseShip;

import java.util.List;

public interface CruiseShipService {
    List<CruiseShipDto> getAllCruiseShips();

    CruiseShipDto getCruiseShipById(Integer id);

    CruiseShip saveCruiseShip(CruiseShipDto cruiseShip);

    CruiseShip updateCruiseShip(CruiseShipDto cruiseShip);

    void deleteCruiseShip(Integer id);
}
