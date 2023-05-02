package com.example.cruiseonspring.mapper;

import com.example.cruiseonspring.dto.UserDto;
import com.example.cruiseonspring.entity.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class UserMapper {
    public UserDto userToDto(User user) {
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

    public User dtoToUser(UserDto userDto) {
        User user = new User();
        user.setId(userDto.getId());
        user.setName(userDto.getName());
        user.setGender(userDto.getGender());
        user.setEmail(userDto.getEmail());
        user.setSurname(userDto.getSurname());
        user.setPassword(userDto.getPassword());
        user.setDateOfBirthday(userDto.getDateOfBirthday());
        user.setPhoneNumber(userDto.getPhoneNumber());
        return user;
    }
}
