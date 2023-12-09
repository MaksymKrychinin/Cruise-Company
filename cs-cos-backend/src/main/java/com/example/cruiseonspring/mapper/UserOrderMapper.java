package com.example.cruiseonspring.mapper;

import com.example.cruiseonspring.dto.UserOrderDto;
import com.example.cruiseonspring.entity.UserOrder;
import com.example.cruiseonspring.exception.NotFoundException;
import com.example.cruiseonspring.repository.CruiseShipRepository;
import com.example.cruiseonspring.repository.UserRepository;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring", uses = {CruiseShipMapper.class, UserMapper.class})
public abstract class UserOrderMapper {
    @Autowired
    protected CruiseShipRepository cruiseShipRepository;
    @Autowired
    protected UserRepository userRepository;

    @Mappings({
            @Mapping(target = "frontPassport", ignore = true),
            @Mapping(target = "backPassport", ignore = true),
    })
    public abstract UserOrderDto userOrderToDto(UserOrder userOrder);
}
