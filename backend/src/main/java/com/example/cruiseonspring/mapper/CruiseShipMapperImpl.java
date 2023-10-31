package com.example.cruiseonspring.mapper;

import com.example.cruiseonspring.dto.CruiseShipDto;
import com.example.cruiseonspring.entity.CruiseShip;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier
public class CruiseShipMapperImpl implements CruiseShipMapper {

    @Override
    public CruiseShip cruiseShipToDto(CruiseShipDto source) {
        if ( source == null ) {
            return null;
        }

        CruiseShip.CruiseShipBuilder cruiseShip = CruiseShip.builder();

        cruiseShip.capacity( source.getCapacity() );
        cruiseShip.routeFrom( source.getRouteFrom() );
        cruiseShip.routeTo( source.getRouteTo() );
        cruiseShip.numberOfVisitedPorts( source.getNumberOfVisitedPorts() );
        cruiseShip.startDate( source.getStartDate() );
        cruiseShip.endDate( source.getEndDate() );
        cruiseShip.orderedSeats( source.getOrderedSeats() );

        return cruiseShip.build();
    }

    @Override
    public CruiseShipDto dtoToCruiseShip(CruiseShip source) {
        if ( source == null ) {
            return null;
        }

        CruiseShipDto.CruiseShipDtoBuilder cruiseShipDto = CruiseShipDto.builder();

        cruiseShipDto.capacity( source.getCapacity() );
        cruiseShipDto.routeFrom( source.getRouteFrom() );
        cruiseShipDto.routeTo( source.getRouteTo() );
        cruiseShipDto.numberOfVisitedPorts( source.getNumberOfVisitedPorts() );
        cruiseShipDto.startDate( source.getStartDate() );
        cruiseShipDto.endDate( source.getEndDate() );
        cruiseShipDto.orderedSeats( source.getOrderedSeats() );

        return cruiseShipDto.build();
    }
}
