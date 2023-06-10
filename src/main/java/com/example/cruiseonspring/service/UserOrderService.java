package com.example.cruiseonspring.service;

import com.example.cruiseonspring.dto.UserorderDto;
import com.example.cruiseonspring.entity.User;
import com.example.cruiseonspring.entity.UserOrder;

import java.util.List;

public interface UserOrderService {
    List<UserorderDto> getAllUserOrders(User user);
    UserorderDto saveUserOrder(UserOrder userOrder);
    UserorderDto updateUserOrder(UserOrder userOrder);
    void deleteUserOrder(Integer id);
}
