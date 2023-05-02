package com.example.cruiseonspring.service;

import com.example.cruiseonspring.mapper.UserMapper;
import com.example.cruiseonspring.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService{
    UserMapper userMapper;
    UserRepository userRepository;
}
