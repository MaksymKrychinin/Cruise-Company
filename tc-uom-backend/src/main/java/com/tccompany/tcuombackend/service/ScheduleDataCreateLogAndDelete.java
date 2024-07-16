package com.tccompany.tcuombackend.service;

import com.tccompany.tcuombackend.entity.CruiseShip;
import com.tccompany.tcuombackend.repository.CruiseShipRepository;
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
    Date date;

    void renewDate() {
        new Date(System.currentTimeMillis()
                - 1000L * 3600 * 24 * 365);
    }

    // TODO: Make paginated
    @Scheduled(cron = "0 0 12 * * ?")
    public void dumpAndLogData() {
        renewDate();
        List<CruiseShip> allByEndDateGreaterThan =
                cruiseShipRepository
                        .findAllByEndDateLessThan(
                                date);
        allByEndDateGreaterThan
                .stream()
                .map(CruiseShip::toString)
                .forEach(LOG::info);
        cruiseShipRepository.deleteAllByEndDateLessThan(date);
    }
}
