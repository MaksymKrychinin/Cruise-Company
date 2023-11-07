package com.example.cruiseonspring.datasetup;

import com.example.cruiseonspring.entity.CruiseShip;
import com.example.cruiseonspring.repository.CruiseShipRepository;
import com.github.javafaker.Faker;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
@Log4j2
public class CruiseShipDataGenerator implements ApplicationListener<ApplicationReadyEvent> {
    private final CruiseShipRepository cruiseShipRepository;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        if (cruiseShipRepository.count() == 0) {
            log.warn("Generating cruise ships...");
            generate();
        }
    }

    public static List<CruiseShip> generateSampleCruiseShips(int numberOfCruises) {
        List<CruiseShip> cruiseShips = new ArrayList<>();
        Faker faker = new Faker();

        for (int i = 0; i < numberOfCruises; i++) {
            CruiseShip cruiseShip = new CruiseShip();
            cruiseShip.setCapacity(faker.number().numberBetween(100, 500)); // Випадкова ємність корабля
            cruiseShip.setRouteFrom(faker.address().cityName()); // Випадкове місце відправлення
            cruiseShip.setRouteTo(faker.address().cityName()); // Випадковий пункт призначення
            cruiseShip.setNumberOfVisitedPorts(faker.number().numberBetween(2, 10)); // Випадкова кількість відвіданих портів
            cruiseShip.setStartDate(new Date(123, 9, 21)); // Випадкова дата початку круїзу (наступний рік)
            cruiseShip.setEndDate(new Date(123, 9, 21)); // Випадкова дата закінчення круїзу (після початку)
            cruiseShip.setOrderedSeats(faker.number().numberBetween(50, cruiseShip.getCapacity())); // Випадкова кількість замовлених місць

            cruiseShips.add(cruiseShip);
        }

        return cruiseShips;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void generate() {
        List<CruiseShip> generatedCruises = generateSampleCruiseShips(30);
        cruiseShipRepository.saveAll(generatedCruises);
    }

}