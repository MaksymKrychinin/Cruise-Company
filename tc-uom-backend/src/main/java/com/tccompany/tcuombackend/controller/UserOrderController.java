package com.tccompany.tcuombackend.controller;

import com.tccompany.tcuombackend.dto.SpecificationTransferDto;
import com.tccompany.tcuombackend.dto.UserOrderDto;
import com.tccompany.tcuombackend.entity.UserOrder;
import com.tccompany.tcuombackend.service.UserOrderService;
import dtos.FilterFieldsDto;
import functions.FilterFieldCheck;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user-orders")
@RequiredArgsConstructor
@CrossOrigin
public class UserOrderController {
    private final UserOrderService userOrderService;

    @GetMapping("")
    ResponseEntity<Page<UserOrderDto>> getAllUserOrder(
            @AuthenticationPrincipal UserDetails userDetails,
            @PageableDefault Pageable pageable) {
        Page<UserOrderDto> allUserOrders = userOrderService.getAllUserOrders(userDetails, pageable);
        return ResponseEntity.ok().body(allUserOrders);
    }

    @GetMapping("/filters")
    public List<FilterFieldsDto> objectFiltersUserOrder() {
        return FilterFieldCheck.listOfObjectFilters(UserOrder.class);
    }

    @GetMapping("/filtered/")
    ResponseEntity<Page<UserOrderDto>> getAllUserOrdersFiltered(
            @AuthenticationPrincipal UserDetails userDetails,
            @PageableDefault Pageable pageable,
            SpecificationTransferDto[] specificationTransferDto) {
        Page<UserOrderDto> allUserOrders = userOrderService.getAllUserOrdersFiltered(userDetails, pageable, specificationTransferDto);
        return ResponseEntity.ok().body(allUserOrders);
    }

    @GetMapping("/{id}")
    ResponseEntity<UserOrderDto> getUserOrderById(@AuthenticationPrincipal UserDetails userDetails,
                                                  @PathVariable Integer id) {
        return ResponseEntity.ok(userOrderService.getUserOrderById(id, userDetails));
    }

    @PostMapping("/")
    ResponseEntity<UserOrderDto> saveUserOrder(
            @AuthenticationPrincipal UserDetails userDetails,
            @RequestBody UserOrder userOrder
    ) {
        return ResponseEntity.ok(userOrderService.saveUserOrder(userOrder, userDetails));
    }

    @PutMapping("/")
    ResponseEntity<UserOrderDto> updateUserOrder(
            @AuthenticationPrincipal UserDetails userDetails,
            @RequestBody UserOrder userOrder
    ) {
        return ResponseEntity.ok(userOrderService.updateUserOrder(userOrder, userDetails));
    }
}
