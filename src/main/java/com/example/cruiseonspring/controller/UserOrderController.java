package com.example.cruiseonspring.controller;

import com.example.cruiseonspring.dto.UserOrderDto;
import com.example.cruiseonspring.entity.UserOrder;
import com.example.cruiseonspring.service.UserOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/v1/userOrder")
@RequiredArgsConstructor
@CrossOrigin
public class UserOrderController {
    private UserOrderService userOrderService;

    @GetMapping("")
    List<UserOrderDto> getAllUserOrder(@AuthenticationPrincipal UserDetails userDetails) {
        return userOrderService.getAllUserOrders(userDetails);
    }

    @GetMapping("/{id}")
    UserOrderDto getUserOrderById(@AuthenticationPrincipal UserDetails userDetails,
                                  @PathVariable Integer orderId) {
        return userOrderService.getUserOrderById(orderId, userDetails);
    }

    @PostMapping("")
    UserOrderDto saveUserOrder(
            @AuthenticationPrincipal UserDetails userDetails,
            @RequestBody UserOrder userOrder
    ) {
        return userOrderService.saveUserOrder(userOrder, userDetails);
    }

    @PutMapping("")
    UserOrderDto updateUserOrder(
            @AuthenticationPrincipal UserDetails userDetails,
            @RequestBody UserOrder userOrder
    ) {
        return userOrderService.updateUserOrder(userOrder, userDetails);
    }
}
