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
public class UserOrderMapper implements Function<UserOrder,UserOrderDto> {

    private final CruiseShipRepository cruiseshipRepository;
    private final UserRepository userRepository;


    @Override
    public UserOrderDto apply(UserOrder userOrder) {
        UserOrderDto userorderDto = new UserOrderDto();
        userorderDto.setId(userOrder.getId());
        userorderDto.setStatus(userOrder.getStatus());
        userorderDto.setIdCruiseShip(userOrder.getCruiseShip().getId());
        userorderDto.setFrontPassport(userOrder.getFrontPassport());
        userorderDto.setBackPassport(userOrder.getBackPassport());
        userorderDto.setIdUser(userOrder.getUser().getId());
        return userorderDto;
    }
}
