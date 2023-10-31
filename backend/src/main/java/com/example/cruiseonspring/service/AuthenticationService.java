package com.example.cruiseonspring.service;

import com.example.cruiseonspring.dto.AuthenticationRequest;
import com.example.cruiseonspring.dto.AuthenticationResponse;
import com.example.cruiseonspring.dto.RegisterRequest;



public interface AuthenticationService {
    AuthenticationResponse register(RegisterRequest request);
    AuthenticationResponse auth(AuthenticationRequest request);
}
