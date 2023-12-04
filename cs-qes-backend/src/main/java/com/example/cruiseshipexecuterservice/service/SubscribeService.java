package com.example.cruiseshipexecuterservice.service;

import com.example.cruiseshipexecuterservice.entity.dto.CruiseShipDto;
import com.example.cruiseshipexecuterservice.entity.jms.CruiseShip;
import com.example.cruiseshipexecuterservice.entity.jms.User;
import com.example.cruiseshipexecuterservice.mapper.CruiseShipMapper;
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
