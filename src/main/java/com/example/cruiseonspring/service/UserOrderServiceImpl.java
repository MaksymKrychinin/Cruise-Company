package com.example.cruiseonspring.service;

import com.example.cruiseonspring.dto.UserOrderDto;
import com.example.cruiseonspring.entity.UserOrder;
import com.example.cruiseonspring.exception.UserNotFoundException;
import com.example.cruiseonspring.exception.UserOrderNotFountException;
import com.example.cruiseonspring.mapper.UserOrderMapper;
import com.example.cruiseonspring.repository.UserRepository;
import com.example.cruiseonspring.repository.UserorderRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserOrderServiceImpl implements UserOrderService {
    private final UserorderRepository userorderRepository;
    private final UserOrderMapper userorderMapper;
    private final UserRepository userRepository;


    @Override
    public UserOrderDto getUserOrderById(Integer orderId, UserDetails userDetails) {
        UserOrder userOrder = userorderRepository.findById(orderId)
                .orElseThrow(() -> new UserOrderNotFountException("User order" + orderId + "not found"));
        if (!userOrder.getUser().getName().equals(userDetails.getUsername())){
            throw new UserOrderNotFountException("User can't access another peoples orders");
        }
        return userorderMapper.apply(userOrder);
    }

    @Override
    public List<UserOrderDto> getAllUserOrders(UserDetails user) {
        return userorderRepository.findAllByUserEmail(user.getUsername())
                .stream()
                .map(userorderMapper)
                .collect(Collectors.toList());
    }


    @Override
    public UserOrderDto saveUserOrder(UserOrder userOrder, UserDetails userDetails) {
        userOrder.setUser(userRepository.findByEmail(userDetails.getUsername())
                .orElseThrow(() -> new UserNotFoundException("User not found ;(")));
        return userorderMapper.apply(userorderRepository.save(userOrder));
    }

    @Override
    public UserOrderDto updateUserOrder(UserOrder userOrder, UserDetails userDetails) {
        UserOrder userOrderToUpdate = userorderRepository.findById(
                        userOrder.getId())
                .orElseThrow(() -> new UserOrderNotFountException("User order not found"));
        if (!userOrderToUpdate.getUser().getName().equals(userDetails.getUsername())) {
            throw new UserOrderNotFountException("User can't access this order");
        }
        return userorderMapper.apply(userorderRepository.save(userOrder));
    }

    @Override
    public void deleteUserOrder(Integer id) {
        userorderRepository.deleteById(id);
    }
}
