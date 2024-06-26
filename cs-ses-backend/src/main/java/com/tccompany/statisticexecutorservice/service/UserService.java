package com.tccompany.statisticexecutorservice.service;

import com.tccompany.statisticexecutorservice.entity.jms.CruiseShip;
import com.tccompany.statisticexecutorservice.entity.jms.User;
import com.tccompany.statisticexecutorservice.repository.UserRepository;
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
