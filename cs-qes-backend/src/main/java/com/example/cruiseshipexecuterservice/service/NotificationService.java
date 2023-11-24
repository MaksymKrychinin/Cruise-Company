package com.example.cruiseshipexecuterservice.service;

import com.example.cruiseshipexecuterservice.annotation.FieldCheck;
import com.example.cruiseshipexecuterservice.entity.Subscriber;
import com.example.cruiseshipexecuterservice.entity.jms.CruiseShip;
import com.example.cruiseshipexecuterservice.entity.jms.User;
import com.example.cruiseshipexecuterservice.repository.SubscriberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.cruiseshipexecuterservice.annotation.FieldCheck.anyMatch;

@Service
@RequiredArgsConstructor
public class NotificationService {
    private final SubscriberRepository subscriberRepository;

    public void sendNotification(CruiseShip cruiseShip) {
        List<Subscriber> allByCruiseShip = subscriberRepository.findAll();
        List<User> listUsers = allByCruiseShip.stream()
                .filter(subscriber ->
                        subscriber.getCruiseShips()
                                .stream().anyMatch(current -> anyMatch(current, cruiseShip)))
                .map(Subscriber::getUser)
                .toList();
        for (User user : listUsers) {
            if (user.getEmail() != null) {
                System.out.println("Sending email to " + user.getEmail());//TODO: send email
            }
            if (user.getPhoneNumber() != null) {
                System.out.println("Sending sms to " + user.getPhoneNumber());//TODO: send sms
            }
        }
    }


}
