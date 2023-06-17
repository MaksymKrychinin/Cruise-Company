package com.example.cruiseonspring.service;

import com.example.cruiseonspring.dto.UserOrderDto;
import com.example.cruiseonspring.entity.User;
import com.example.cruiseonspring.entity.UserOrder;

import java.util.List;

public interface UserOrderService {
    List<UserOrderDto> getAllUserOrders(User user);
    UserOrderDto saveUserOrder(UserOrder userOrder);
    UserOrderDto updateUserOrder(UserOrder userOrder);
    void deleteUserOrder(Integer id);
}
