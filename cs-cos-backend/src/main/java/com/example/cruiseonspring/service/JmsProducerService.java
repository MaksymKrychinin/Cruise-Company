package com.example.cruiseonspring.service;

import com.example.cruiseonspring.entity.CruiseShip;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

@Service
@Log4j2
@RequiredArgsConstructor
public class JmsProducerService {

    @Value("${spring.activemq.queue}")
    private String queue;

    private final JmsTemplate jmsTemplate;

    public void sendToQueue(CruiseShip cruiseShip) {
        jmsTemplate.convertAndSend(queue, cruiseShip);
        log.info(String.format("Cruise ship created -> %s", cruiseShip));
    }
}
