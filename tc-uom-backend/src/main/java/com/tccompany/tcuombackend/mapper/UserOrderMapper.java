package com.tccompany.tcuombackend.mapper;

import com.tccompany.tcuombackend.dto.UserOrderDto;
import com.tccompany.tcuombackend.entity.UserOrder;
import org.mapstruct.*;

@Mapper(componentModel = "spring", uses = {})
public abstract class UserOrderMapper {

    @Mappings({
            @Mapping(target = "frontPassport", ignore = true),
            @Mapping(target = "backPassport", ignore = true),
    })
    public abstract UserOrderDto userOrderToDto(UserOrder userOrder);
}
