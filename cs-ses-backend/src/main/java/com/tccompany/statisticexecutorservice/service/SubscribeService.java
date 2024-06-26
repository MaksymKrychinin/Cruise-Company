package com.tccompany.statisticexecutorservice.service;

import com.tccompany.statisticexecutorservice.entity.dto.CruiseShipDto;
import com.tccompany.statisticexecutorservice.entity.jms.CruiseShip;
import com.tccompany.statisticexecutorservice.entity.jms.User;
import com.tccompany.statisticexecutorservice.mapper.CruiseShipMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SubscribeService {
    private final CruiseShipMapper cruiseShipDtoMapper;
    private final UserService userService;

    public void subscribe(User userDetails, CruiseShipDto cruiseShipDto) {
        String email = userDetails.getEmail();
        CruiseShip cruiseShip = cruiseShipDtoMapper.cruiseShipDtoToCruiseShip(cruiseShipDto);
        userService.subscribe(email, cruiseShip);
    }
}
