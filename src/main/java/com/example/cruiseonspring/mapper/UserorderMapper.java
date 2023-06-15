package com.example.cruiseonspring.mapper;

import com.example.cruiseonspring.dto.UserOrderDto;
import com.example.cruiseonspring.entity.UserOrder;
import com.example.cruiseonspring.entity.Userorder;
import com.example.cruiseonspring.repository.CruiseShipRepository;
import com.example.cruiseonspring.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class UserorderMapper {

    private final CruiseShipRepository cruiseshipRepository;
    private final UserRepository userRepository;

    public UserOrderDto userorderToDto(UserOrder userorder) {
        UserOrderDto userorderDto = new UserOrderDto();
        userorderDto.setId(userorder.getId());
        userorderDto.setStatus(userorder.getStatus());
        userorderDto.setIdCruiseShip(userorder.getCruiseShip().getId());
        userorderDto.setFrontPassport(userorder.getFrontPassport());
        userorderDto.setBackPassport(userorder.getBackPassport());
        userorderDto.setIdUser(userorder.getUser().getId());
        return userorderDto;
    }

    public UserOrder dtoToUserorder(UserOrderDto userorderDto) {
        UserOrder userorder = new UserOrder();
        userorder.setId(userorderDto.getId());
        userorder.setStatus(userorderDto.getStatus());
        userorder.setCruiseShip(cruiseshipRepository.findById(userorderDto.getIdCruiseShip()).get());
        userorder.setFrontPassport(userorderDto.getFrontPassport());
        userorder.setBackPassport(userorderDto.getBackPassport());
        userorder.setUser(userRepository.findById(userorderDto.getIdUser()).get());
        return userorder;
    }
}
