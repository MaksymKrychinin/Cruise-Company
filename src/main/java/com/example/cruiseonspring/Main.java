package com.example.cruiseonspring;


import com.example.cruiseonspring.repository.CruiseShipRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


@Component
@AllArgsConstructor
public class Main implements CommandLineRunner {
    CruiseShipRepository cruiseshipRepository;
    @Override
    public void run(String... args) {
        main(args);
    }

    private void main(String[] args) {
    }
}
