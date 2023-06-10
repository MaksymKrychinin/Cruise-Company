package com.example.cruiseonspring.mapper;

import com.example.cruiseonspring.dto.UserorderDto;
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

    public UserorderDto userorderToDto(UserOrder userorder) {
        UserorderDto userorderDto = new UserorderDto();
        userorderDto.setId(userorder.getId());
        userorderDto.setStatus(userorder.getStatus());
        userorderDto.setIdCruiseShip(userorder.getCruiseShip().getId());
        userorderDto.setFrontPassport(userorder.getFrontPassport());
        userorderDto.setBackPassport(userorder.getBackPassport());
        userorderDto.setIdUser(userorder.getUser().getId());
        return userorderDto;
    }

    public UserOrder dtoToUserorder(UserorderDto userorderDto) {
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
