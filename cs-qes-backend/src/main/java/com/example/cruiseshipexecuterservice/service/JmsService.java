package com.example.cruiseshipexecuterservice.service;

import com.example.cruiseshipexecuterservice.entity.jms.CruiseShip;
import com.example.cruiseshipexecuterservice.entity.jms.User;
import com.example.cruiseshipexecuterservice.entity.jms.UserOrder;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Log4j2
public class JmsService {
    private final StatisticService statisticService;
    private final NotificationService notificationService;

    @JmsListener(destination = "cruise-ships")
    public void receiveAndForwardMessageFromQueue(
            @Payload CruiseShip cruiseShip) {
        log.info(cruiseShip);
        statisticService.updateStatistic(cruiseShip);
        notificationService.sendNotification(cruiseShip);
    }
    @JmsListener(destination = "users")
    public void receiveAndForwardMessageFromQueue(
            @Payload User user) {
        log.info(user);
        statisticService.updateStatistic(user);
    }
    @JmsListener(destination = "user-orders")
    public void receiveAndForwardMessageFromQueue(
            @Payload UserOrder cruiseShip) {
        log.info(cruiseShip);
        statisticService.updateStatistic(cruiseShip);
    }
}

