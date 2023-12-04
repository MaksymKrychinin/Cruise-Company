package com.example.cruiseshipexecuterservice.controller;

import com.example.cruiseshipexecuterservice.entity.dto.CruiseShipDto;
import com.example.cruiseshipexecuterservice.entity.jms.User;
import com.example.cruiseshipexecuterservice.service.SubscribeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("/cruse-ship")
@RequiredArgsConstructor
public class CruiseShipSubscribeController {
    private final SubscribeService subscribeService;

    @PostMapping("/subscribe")
    ResponseEntity<Void> subscribe(User userDetails, @RequestBody CruiseShipDto cruiseShip) {
        subscribeService.subscribe(userDetails, cruiseShip);
        return ResponseEntity.ok().build();
    }
}
