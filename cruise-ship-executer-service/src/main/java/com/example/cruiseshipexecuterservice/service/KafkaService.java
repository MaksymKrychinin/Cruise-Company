package com.example.cruiseshipexecuterservice.service;

import com.example.cruiseshipexecuterservice.entity.CruiseShip;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class KafkaService {

    @KafkaListener(topics = "new-cruise-ship", groupId = "statistic-group-1")
    public void consume(CruiseShip cruiseShip) {
        System.out.println("Consumed message: " + cruiseShip);
    }
}
