package com.example.cruiseonspring.service;

import com.example.cruiseonspring.config.AppConstants;
import com.example.cruiseonspring.entity.CruiseShip;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@Log4j2
@RequiredArgsConstructor
public class KafkaProducerService {
    private final KafkaTemplate<String, CruiseShip> kafkaTemplateCruiseShip;
    private final AppConstants appConstants;

    public void saveCruiseShip(CruiseShip user) {
        log.info(String.format("Cruise ship created -> %s", user));
        kafkaTemplateCruiseShip.send(appConstants.TOPIC_NAME_NEW_CRUISE_SHIP, user);
    }

}
