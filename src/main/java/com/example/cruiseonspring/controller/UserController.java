package com.example.cruiseonspring.controller;

import com.example.cruiseonspring.service.UserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {
    UserService userService;

}
