package com.example.cruiseonspring.service;

import com.example.cruiseonspring.dto.UserOrderDto;
import com.example.cruiseonspring.entity.UserOrder;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public interface UserOrderService {
    UserOrderDto getUserOrderById(Integer orderId, UserDetails userDetails);
    List<UserOrderDto> getAllUserOrders(UserDetails user);
    UserOrderDto saveUserOrder(UserOrder userOrder, UserDetails userDetails);
    UserOrderDto updateUserOrder(UserOrder userOrder, UserDetails userDetails);
    void deleteUserOrder(Integer id);
}
