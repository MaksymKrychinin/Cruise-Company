package com.example.cruiseonspring.service;

import com.example.cruiseonspring.dto.UserorderDto;
import com.example.cruiseonspring.entity.User;
import com.example.cruiseonspring.entity.Userorder;
import com.example.cruiseonspring.mapper.UserorderMapper;
import com.example.cruiseonspring.repository.UserorderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserorderServiceImpl implements UserorderService {
    private final UserorderRepository userorderRepository;
    private final UserorderMapper userorderMapper;


    @Override
    public List<UserorderDto> getAllUserOrders(User user) {
        return userorderRepository.findAllByIdUserEquals(user)
                .stream()
                .map(userorderMapper::userorderToDto)
                .collect(Collectors.toList());
    }

    @Override
    public UserorderDto saveUserOrder(Userorder userOrder) {
        return userorderMapper.userorderToDto(userorderRepository.save(userOrder));
    }

    @Override
    public UserorderDto updateUserOrder(Userorder userOrder) {
        return userorderMapper.userorderToDto(userorderRepository.save(userOrder));
    }

    @Override
    public void deleteUserOrder(Integer id) {
        userorderRepository.deleteById(id);
    }
}
