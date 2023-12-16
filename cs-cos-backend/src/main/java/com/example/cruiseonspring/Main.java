package com.example.cruiseonspring;


import com.example.cruiseonspring.annotation.FilterFieldCheck;
import com.example.cruiseonspring.entity.UserOrder;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


@Component
@AllArgsConstructor
public class Main implements CommandLineRunner {
    @Override
    public void run(String... args) {

    }

    public static void main(String[] args) {
        FilterFieldCheck.mapOfObjectFilters(UserOrder.class).forEach((k, v) -> System.out.println(k + " " + v));
    }
}
