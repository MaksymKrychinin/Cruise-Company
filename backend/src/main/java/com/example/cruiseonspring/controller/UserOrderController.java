package com.example.cruiseonspring.controller;

import com.example.cruiseonspring.dto.UserOrderDto;
import com.example.cruiseonspring.entity.UserOrder;
import com.example.cruiseonspring.service.UserOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/userOrder")
@RequiredArgsConstructor
@CrossOrigin
public class UserOrderController {
    private final UserOrderService userOrderService;

    @GetMapping("")
    ResponseEntity<List<UserOrderDto>> getAllUserOrder(@AuthenticationPrincipal UserDetails userDetails) {
        List<UserOrderDto> allUserOrders = userOrderService.getAllUserOrders(userDetails);
        return ResponseEntity.ok().body(allUserOrders);
    }

    @GetMapping("/{id}")
    ResponseEntity<UserOrderDto> getUserOrderById(@AuthenticationPrincipal UserDetails userDetails,
                                                  @PathVariable Integer id) {
        return ResponseEntity.ok(userOrderService.getUserOrderById(id, userDetails));
    }

    @PostMapping("")
    ResponseEntity<UserOrderDto> saveUserOrder(
            @AuthenticationPrincipal UserDetails userDetails,
            @RequestBody UserOrder userOrder
    ) {
        return ResponseEntity.ok(userOrderService.saveUserOrder(userOrder, userDetails));
    }

    @PutMapping("")
    ResponseEntity<UserOrderDto> updateUserOrder(
            @AuthenticationPrincipal UserDetails userDetails,
            @RequestBody UserOrder userOrder
    ) {
        return ResponseEntity.ok(userOrderService.updateUserOrder(userOrder, userDetails));
    }
}