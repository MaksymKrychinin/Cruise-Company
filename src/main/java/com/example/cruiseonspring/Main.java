package com.example.cruiseonspring;


import com.example.cruiseonspring.repository.CruiseshipRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


@Component
@AllArgsConstructor
public class Main implements CommandLineRunner {
    CruiseshipRepository cruiseshipRepository;
    @Override
    public void run(String... args) {
        main(args);
    }

    private void main(String[] args) {
    }
}
