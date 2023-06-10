package com.example.cruiseonspring.mapper;

import com.example.cruiseonspring.dto.CruiseShipDto;
import com.example.cruiseonspring.entity.CruiseShip;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class CruiseShipDtoToEntityMapper implements Function<CruiseShipDto, CruiseShip>{
    @Override
    public CruiseShip apply(CruiseShipDto cruiseShipDto) {
        CruiseShip cruiseShip = new CruiseShip();
        cruiseShip.setId(cruiseShipDto.getId());
        cruiseShip.setCapacity(cruiseShipDto.getCapacity());
        cruiseShip.setNumberOfVisitedPorts(cruiseShipDto.getNumberOfVisitedPorts());
        cruiseShip.setOrderedSeats(cruiseShipDto.getOrderedSeats());
        cruiseShip.setRouteTo(cruiseShipDto.getRouteTo());
        cruiseShip.setRouteFrom(cruiseShipDto.getRouteFrom());
        cruiseShip.setEndDate(cruiseShipDto.getEndDate());
        cruiseShip.setStartDate(cruiseShipDto.getStartDate());
        return cruiseShip;
    }
}
