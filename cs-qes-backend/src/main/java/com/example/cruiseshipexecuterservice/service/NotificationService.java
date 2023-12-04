package com.example.cruiseshipexecuterservice.service;

import com.example.cruiseshipexecuterservice.entity.jms.CruiseShip;
import com.example.cruiseshipexecuterservice.entity.jms.User;
import com.example.cruiseshipexecuterservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.cruiseshipexecuterservice.annotation.FieldCheck.anyMatch;

@Service
@RequiredArgsConstructor
public class NotificationService {
    private final UserRepository userRepository;

    public void sendNotification(CruiseShip cruiseShip) {
        List<User> listUsers = userRepository.findAll().stream()
                .filter(user ->
                        user.getCruiseShips()
                                .stream().anyMatch(current -> anyMatch(current, cruiseShip)))
                .toList();
        for (User user : listUsers) {
            if (user.getEmail() != null) {
                System.out.println("Sending email to " + user.getEmail());//TODO: send email
            }
            if (user.getTelegramNickname() != null) {
                System.out.println("Sending message to " + user.getTelegramChatId());//TODO: send message
            }
        }
    }


}
