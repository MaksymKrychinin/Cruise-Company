package com.example.cruiseonspring;


import com.example.cruiseonspring.annotation.FilterFieldCheck;
import com.example.cruiseonspring.entity.UserOrder;
import com.example.cruiseonspring.mapper.UserOrderMapper;
import com.example.cruiseonspring.repository.BaseSpecification;
import com.example.cruiseonspring.repository.UserOrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;


@Component
@AllArgsConstructor
public class Main implements CommandLineRunner {
    @Autowired
    UserOrderRepository userOrderRepository;
    @Autowired
    UserOrderMapper userOrderMapper;

    @Override
    public void run(String... args) {
        Page<UserOrder> userOrder =
                userOrderRepository
                        .findAll(new BaseSpecification<>("routeFrom","Tambraburgh","cruiseShip"), PageRequest.of(0, 100));
        userOrder.map(userOrderMapper::userOrderToDto).forEach(System.out::println);
    }

    public static void main(String[] args) {

        FilterFieldCheck.mapOfObjectFilters(UserOrder.class).forEach((k, v) -> System.out.println(k + " " + v));
    }
}
