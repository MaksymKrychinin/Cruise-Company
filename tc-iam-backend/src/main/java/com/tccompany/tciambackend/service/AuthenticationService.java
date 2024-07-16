package com.tccompany.tciambackend.service;

import com.tccompany.tciambackend.dto.AuthenticationRequest;
import com.tccompany.tciambackend.dto.AuthenticationResponse;
import com.tccompany.tciambackend.dto.RegisterRequest;



public interface AuthenticationService {
    AuthenticationResponse register(RegisterRequest request);
    AuthenticationResponse auth(AuthenticationRequest request);
}
