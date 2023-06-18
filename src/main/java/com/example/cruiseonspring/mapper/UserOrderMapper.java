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

    @Override
    public UserOrderDto apply(UserOrder userOrder) {
        return UserOrderDto.builder()
                .id(userOrder.getId())
                .idUser(userOrder.getUser().getId())
                .backPassport(userOrder.getBackPassport())
                .frontPassport(userOrder.getFrontPassport())
                .status(userOrder.getStatus())
                .idCruiseShip(userOrder.getCruiseShip().getId())
                .build();
    }
}
