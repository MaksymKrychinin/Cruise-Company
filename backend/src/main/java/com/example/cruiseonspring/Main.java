package com.example.cruiseonspring;


import com.example.cruiseonspring.entity.CruiseShip;
import com.example.cruiseonspring.repository.CruiseShipRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.awt.print.Pageable;


@Component
@AllArgsConstructor
public class Main implements CommandLineRunner {
    CruiseShipRepository cruiseShipRepository;

    @Override
    public void run(String... args) {
        PageRequest of = PageRequest.of(0, 9);
        Page<CruiseShip> allWhereOrderedSeatsLessThanCapacity = cruiseShipRepository.findAllWhereOrderedSeatsLessThanCapacity(of);
        System.out.println(allWhereOrderedSeatsLessThanCapacity);
    }
}
