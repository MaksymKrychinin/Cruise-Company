package com.example.cruiseonspring.mapper;

import com.example.cruiseonspring.dto.UserOrderDto;
import com.example.cruiseonspring.entity.UserOrder;
import com.example.cruiseonspring.exception.NotFoundException;
import com.example.cruiseonspring.repository.CruiseShipRepository;
import com.example.cruiseonspring.repository.UserRepository;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring")
public abstract class UserOrderMapper {
    @Autowired
    protected CruiseShipRepository cruiseShipRepository;
    @Autowired
    protected UserRepository userRepository;

    @Mappings({
            @Mapping(target = "frontPassport", ignore = true),
            @Mapping(target = "backPassport", ignore = true)
    })
    public abstract UserOrderDto userOrderToDto(UserOrder userOrder);

/*    @AfterMapping
    protected void userByReferenceId(
            UserOrder userOrder,
            @MappingTarget UserOrderDto.UserOrderDtoBuilder userOrderDto) {
        userOrderDto.user(
                userRepository
                        .findById(userOrder.getUser().getId()).orElseThrow(()->new NotFoundException("User not found")));//TODO: order->dto
    }

    @AfterMapping
    protected void cruiseShipByReferenceId(
            @MappingTarget UserOrder userOrder,
             UserOrderDto.UserOrderDtoBuilder userOrderDto) {
        userOrderDto.cruiseShip(
                cruiseShipRepository
                        .findById(userOrder.getCruiseShip().getId()).orElseThrow(()->new NotFoundException("CruiseShip not found")));//TODO: add exception
    }*/
}
