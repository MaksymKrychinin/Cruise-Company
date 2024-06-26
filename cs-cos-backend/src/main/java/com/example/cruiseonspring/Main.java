package com.example.cruiseonspring;

import com.example.cruiseonspring.dto.SpecificationTransferDto;
import com.example.cruiseonspring.dto.UserOrderDto;
import com.example.cruiseonspring.entity.User;
import com.example.cruiseonspring.entity.UserOrder;
import com.example.cruiseonspring.service.UserOrderService;
import functions.FilterFieldCheck;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.lang.reflect.Array;
import java.util.List;


@Component
@AllArgsConstructor
public class Main implements CommandLineRunner {
    @Autowired
    UserOrderService userOrderService;

    @Override
    public void run(String... args) {
        Page<UserOrderDto> userOrder =
                userOrderService
                        .getAllUserOrdersFiltered(
                                new User().builder().email("1qwe").build(),
                                PageRequest.of(0, 100),
                                List.of(new SpecificationTransferDto("cruiseShip.routeFrom", "Tambraburgh"),
                                                new SpecificationTransferDto("cruiseShip.routeTo", "Trompton"))
                                        .toArray(SpecificationTransferDto[]::new)
                        );
        userOrder.forEach(System.out::println);
    }

    public static void main(String[] args) {

        FilterFieldCheck.mapOfObjectFilters(UserOrder.class).forEach((k, v) -> System.out.println(k + " " + v));
    }
}
