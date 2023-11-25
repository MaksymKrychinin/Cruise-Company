package com.example.cruiseonspring.mapper;

import com.example.cruiseonspring.dto.UserOrderDto;
import com.example.cruiseonspring.entity.UserOrder;
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
            @Mapping(target = "user", ignore = true),
            @Mapping(target = "cruiseShip", ignore = true)
    })
    public abstract UserOrderDto userOrderToDto(UserOrder userOrder);

    @AfterMapping
    protected void userByReferenceId(
            UserOrder userOrder,
            @MappingTarget UserOrderDto.UserOrderDtoBuilder userOrderDto) {
        userOrderDto.user(
                userRepository
                        .findById(userOrder.getUser().getId()).orElseThrow());//TODO: add exception
    }

    @AfterMapping
    protected void cruiseShipByReferenceId(
            UserOrder userOrder,
            @MappingTarget UserOrderDto.UserOrderDtoBuilder userOrderDto) {
        userOrderDto.cruiseShip(
                cruiseShipRepository
                        .findById(userOrder.getCruiseShip().getId()).orElseThrow());//TODO: add exception
    }
}
