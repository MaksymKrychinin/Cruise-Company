package com.example.cruiseshipexecuterservice.service;

import com.example.cruiseshipexecuterservice.entity.CruiseShip;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Log4j2
public class JmsService {

    @JmsListener(destination = "cruise-ships")
    public void receiveAndForwardMessageFromQueue(
            @Payload CruiseShip cruiseShip) {
        log.info(cruiseShip);
    }
}

