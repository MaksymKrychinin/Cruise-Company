package com.example.cruiseonspring.mapper;

import com.example.cruiseonspring.dto.UserOrderDto;
import com.example.cruiseonspring.entity.UserOrder;
import com.example.cruiseonspring.repository.CruiseShipRepository;
import com.example.cruiseonspring.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
@AllArgsConstructor
public class UserOrderMapper implements Function<UserOrder, UserOrderDto> {
    private final CruiseShipRepository cruiseShipRepository;
    private final UserRepository userRepository;
    @Override
    public UserOrderDto apply(UserOrder userOrder) {
        return UserOrderDto.builder()
                .id(userOrder.getId())
                .user(userRepository.findById(userOrder.getUser().getId()).get())
                .status(userOrder.getStatus())
                .cruiseShip(cruiseShipRepository.findById(userOrder.getCruiseShip().getId()).get())
                .build();
    }
}
