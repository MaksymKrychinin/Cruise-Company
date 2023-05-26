package com.example.cruiseonspring.mapper;

import com.example.cruiseonspring.dto.UserDto;
import com.example.cruiseonspring.entity.User;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class UserMapper implements Function<User, UserDto> {
    @Override
    public UserDto apply(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setName(user.getName());
        userDto.setGender(user.getGender());
        userDto.setEmail(user.getEmail());
        userDto.setSurname(user.getSurname());
        userDto.setPassword("secrets_pass");
        userDto.setDateOfBirthday(user.getDateOfBirthday());
        userDto.setPhoneNumber(user.getPhoneNumber());
        return userDto;
    }
}
