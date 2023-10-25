package com.example.cruiseonspring.service;

import com.example.cruiseonspring.dto.UserOrderDto;
import com.example.cruiseonspring.entity.CruiseShip;
import com.example.cruiseonspring.entity.UserOrder;
import com.example.cruiseonspring.exception.FailedToAccessException;
import com.example.cruiseonspring.exception.NotFoundException;
import com.example.cruiseonspring.mapper.UserOrderMapper;
import com.example.cruiseonspring.repository.UserRepository;
import com.example.cruiseonspring.repository.UserorderRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserOrderServiceImpl implements UserOrderService {
    private final UserorderRepository userorderRepository;
    private final UserOrderMapper userorderMapper;
    private final UserRepository userRepository;
    private final CruiseShipServiceImpl cruiseShipService;


    @Override
    public UserOrderDto getUserOrderById(Integer orderId, UserDetails userDetails) {
        UserOrder userOrder = userorderRepository.findById(orderId)
                .orElseThrow(() -> new NotFoundException("User order" + orderId + "not found"));
        if (!userOrder.getUser().getEmail().equals(userDetails.getUsername())) {
            throw new NotFoundException("User can't access another peoples orders");
        }
        return userorderMapper.apply(userOrder);
    }

    @Override
    public List<UserOrderDto> getAllUserOrders(UserDetails user) {
        List<UserOrder> allByUserEmail = userorderRepository.findAllByUserEmail(user.getUsername());
        List<UserOrderDto> collect = allByUserEmail
                .stream()
                .map(userorderMapper)
                .collect(Collectors.toList());
        System.out.println(collect);
        return collect;
    }


    @Override
    @Transactional
    public UserOrderDto saveUserOrder(UserOrder userOrder, UserDetails userDetails) {
        CruiseShip cruiseShip = cruiseShipService.getCruiseShipById(userOrder.getCruiseShip().getId());
        int capacity = cruiseShip.getCapacity();
        if (capacity <= cruiseShip.getOrderedSeats()) {
            throw new FailedToAccessException("Not enough seats");
        }
        userOrder.setUser(userRepository.findByEmail(userDetails.getUsername())
                .orElseThrow(() -> new NotFoundException("User not found")));
        userOrder.setCruiseShip(cruiseShip);
        UserOrder save = userorderRepository.save(userOrder);
        return userorderMapper.apply(save);
    }

    @Override
    public UserOrderDto updateUserOrder(UserOrder userOrder, UserDetails userDetails) {
        UserOrder userOrderToUpdate = userorderRepository.findById(
                        userOrder.getId())
                .orElseThrow(() -> new NotFoundException("User order not found"));
        if (!userOrderToUpdate.getUser().getEmail().equals(userDetails.getUsername())) {
            throw new FailedToAccessException("User can't access this order");
        }
        return userorderMapper.apply(userorderRepository.save(userOrder));
    }

    @Override
    public void deleteUserOrder(Integer id) {
        userorderRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User order not found"));
        userorderRepository.deleteById(id);
    }
}
