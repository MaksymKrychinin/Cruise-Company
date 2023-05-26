package com.example.cruiseonspring.service;

import com.example.cruiseonspring.dto.UserorderDto;
import com.example.cruiseonspring.entity.User;
import com.example.cruiseonspring.entity.Userorder;

import java.util.List;

public interface UserorderService {
    List<UserorderDto> getAllUserOrders(User user);
    UserorderDto saveUserOrder(Userorder userOrder);
    UserorderDto updateUserOrder(Userorder userOrder);
    void deleteUserOrder(Integer id);
}
