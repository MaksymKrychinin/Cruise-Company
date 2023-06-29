package com.example.cruiseonspring.service;

import com.example.cruiseonspring.entity.CruiseShip;
import com.example.cruiseonspring.repository.CruiseShipRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ScheduleDataCreateLogAndDelete {
    Logger LOG = LoggerFactory.getLogger(ScheduleDataCreateLogAndDelete.class);
    CruiseShipRepository cruiseShipRepository;
    @Scheduled(cron = "0 0 12 * * ?")
    public void dumpData(){
        List<CruiseShip> allByEndDateGreaterThan =
                cruiseShipRepository
                        .findAllByEndDateGreaterThan(
                                new Date(System.currentTimeMillis()
                                        + 1000L * 3600 * 24 * 365));

        allByEndDateGreaterThan
                .stream()
                .map(CruiseShip::toString)
                .forEach(LOG::info);
    }
}
