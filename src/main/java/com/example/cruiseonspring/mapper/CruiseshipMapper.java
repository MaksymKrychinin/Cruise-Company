package com.example.cruiseonspring.mapper;

import com.example.cruiseonspring.dto.CruiseshipDto;
import com.example.cruiseonspring.entity.Cruiseship;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class CruiseshipMapper {
    public CruiseshipDto cruiseshipToDto(Cruiseship cruiseship) {
        CruiseshipDto cruiseshipDto = new CruiseshipDto();
        cruiseshipDto.setId(cruiseship.getId());
        cruiseshipDto.setCapacity(cruiseship.getCapacity());
        cruiseshipDto.setEndDate(cruiseship.getEndDate());
        cruiseshipDto.setStartDate(cruiseship.getStartDate());
        cruiseshipDto.setOrderedSeats(cruiseship.getOrderedSeats());
        cruiseshipDto.setRouteFrom(cruiseship.getRouteFrom());
        cruiseshipDto.setRouteTo(cruiseship.getRouteTo());
        cruiseshipDto.setNumberOfVisitedPorts(cruiseship.getNumberOfVisitedPorts());
        return cruiseshipDto;
    }

    public Cruiseship dtoToCruiseship(CruiseshipDto cruiseshipDto) {
        Cruiseship cruiseship = new Cruiseship();
        cruiseship.setId(cruiseshipDto.getId());
        cruiseship.setCapacity(cruiseshipDto.getCapacity());
        cruiseship.setEndDate(cruiseshipDto.getEndDate());
        cruiseship.setStartDate(cruiseshipDto.getStartDate());
        cruiseship.setOrderedSeats(cruiseshipDto.getOrderedSeats());
        cruiseship.setRouteFrom(cruiseshipDto.getRouteFrom());
        cruiseship.setRouteTo(cruiseshipDto.getRouteTo());
        cruiseship.setNumberOfVisitedPorts(cruiseshipDto.getNumberOfVisitedPorts());
        return cruiseship;
    }
}
