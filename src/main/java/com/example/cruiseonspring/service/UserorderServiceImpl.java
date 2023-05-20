package com.example.cruiseonspring.service;

import com.example.cruiseonspring.mapper.UserorderMapper;
import com.example.cruiseonspring.repository.UserRepository;
import com.example.cruiseonspring.repository.UserorderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserorderServiceImpl implements UserorderService {
    private final UserorderRepository userorderRepository;
    private final UserorderMapper userorderMapper;


}
