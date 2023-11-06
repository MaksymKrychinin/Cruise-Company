package com.example.cruiseonspring.mapper;

import com.example.cruiseonspring.dto.CruiseShipDto;
import com.example.cruiseonspring.entity.CruiseShip;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface CruiseShipMapper {

    CruiseShip cruiseShipToDto(CruiseShipDto source);

    CruiseShipDto dtoToCruiseShip(CruiseShip source);
}

