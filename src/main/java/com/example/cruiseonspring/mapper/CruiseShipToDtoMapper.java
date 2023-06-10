package com.example.cruiseonspring.mapper;

import com.example.cruiseonspring.dto.CruiseShipDto;
import com.example.cruiseonspring.entity.CruiseShip;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class CruiseShipToDtoMapper implements Function<CruiseShip, CruiseShipDto> {
    @Override
    public CruiseShipDto apply(CruiseShip cruiseship) {
        CruiseShipDto cruiseShipDto = new CruiseShipDto();
        cruiseShipDto.setId(cruiseship.getId());
        cruiseShipDto.setCapacity(cruiseship.getCapacity());
        cruiseShipDto.setNumberOfVisitedPorts(cruiseship.getNumberOfVisitedPorts());
        cruiseShipDto.setOrderedSeats(cruiseship.getOrderedSeats());
        cruiseShipDto.setRouteTo(cruiseship.getRouteTo());
        cruiseShipDto.setRouteFrom(cruiseship.getRouteFrom());
        cruiseShipDto.setEndDate(cruiseship.getEndDate());
        cruiseShipDto.setStartDate(cruiseship.getStartDate());
        return cruiseShipDto;
    }
}

