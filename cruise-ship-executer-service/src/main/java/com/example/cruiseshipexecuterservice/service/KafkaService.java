package com.example.cruiseshipexecuterservice.service;

import com.example.cruiseshipexecuterservice.entity.CruiseShip;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Log4j2
@KafkaListener(topics = "new-cruise-ship", groupId = "statistic-group")
public class KafkaService {
    @KafkaHandler(isDefault = true)
    public void consume(CruiseShip cruiseShip) {
        System.out.println("Consumed message: " + cruiseShip);
    }
    @KafkaHandler
    void listen(String message) {
        log.info("KafkaHandler[String] {}", message);
    }

    @KafkaHandler
    void listenDefault(Object object) {
        log.info("KafkaHandler[Default] {}", object);
    }
}
