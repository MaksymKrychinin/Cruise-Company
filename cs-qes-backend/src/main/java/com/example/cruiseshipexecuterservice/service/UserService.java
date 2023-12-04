package com.example.cruiseshipexecuterservice.service;

import com.example.cruiseshipexecuterservice.entity.jms.CruiseShip;
import com.example.cruiseshipexecuterservice.entity.jms.User;
import com.example.cruiseshipexecuterservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public User save(User user) {
        return userRepository.save(user);
    }

    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public void subscribe(String email, CruiseShip cruiseShip) {
        User user = findByEmail(email)
                .orElse(save(User.builder().email(email).build()));
        user.getCruiseShips().add(cruiseShip);
        save(user);
    }
}
