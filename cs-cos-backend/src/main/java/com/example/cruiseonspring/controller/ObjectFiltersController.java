package com.example.cruiseonspring.controller;


import com.example.cruiseonspring.annotation.FilterFieldCheck;
import com.example.cruiseonspring.entity.UserOrder;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/filters")
@RequiredArgsConstructor
@CrossOrigin
public class ObjectFiltersController {
    
   public Map<String, String> objectFilters(String className) {
      return FilterFieldCheck.mapOfObjectFilters(UserOrder.class);
   }
}
