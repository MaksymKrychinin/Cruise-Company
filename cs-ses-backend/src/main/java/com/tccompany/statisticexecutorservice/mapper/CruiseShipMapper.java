package com.tccompany.statisticexecutorservice.mapper;

import com.tccompany.statisticexecutorservice.entity.dto.CruiseShipDto;
import com.tccompany.statisticexecutorservice.entity.jms.CruiseShip;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CruiseShipMapper {
    CruiseShip cruiseShipDtoToCruiseShip(CruiseShipDto cruiseShipDto);
}
