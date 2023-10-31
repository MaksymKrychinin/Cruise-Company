package com.example.cruiseonspring.mapper;

import com.example.cruiseonspring.dto.CruiseShipDto;
import com.example.cruiseonspring.entity.CruiseShip;


public interface CruiseShipMapper {
    CruiseShip cruiseShipToDto(CruiseShipDto source);
    CruiseShipDto dtoToCruiseShip(CruiseShip source);
}

