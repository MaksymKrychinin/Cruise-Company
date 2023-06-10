package com.example.cruiseonspring.mapper;

import com.example.cruiseonspring.dto.cruiseship.CruiseShipDtoForUser;
import com.example.cruiseonspring.entity.CruiseShip;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class CruiseShipToDtoMapper implements Function<CruiseShip, CruiseShipDtoForUser> {
    @Override
    public CruiseShipDtoForUser apply(CruiseShip cruiseship) {
        CruiseShipDtoForUser cruiseShipDto = new CruiseShipDtoForUser();
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

