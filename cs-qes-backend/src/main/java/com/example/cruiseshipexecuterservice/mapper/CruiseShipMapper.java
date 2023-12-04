package com.example.cruiseshipexecuterservice.mapper;

import com.example.cruiseshipexecuterservice.entity.dto.CruiseShipDto;
import com.example.cruiseshipexecuterservice.entity.jms.CruiseShip;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CruiseShipMapper {
    CruiseShip cruiseShipDtoToCruiseShip(CruiseShipDto cruiseShipDto);
}
