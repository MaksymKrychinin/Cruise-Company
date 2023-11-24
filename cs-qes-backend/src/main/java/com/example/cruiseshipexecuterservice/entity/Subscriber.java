package com.example.cruiseshipexecuterservice.entity;

import com.example.cruiseshipexecuterservice.entity.jms.CruiseShip;
import com.example.cruiseshipexecuterservice.entity.jms.User;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.*;

import java.util.List;


@Entity
@Getter
@Setter
@RequiredArgsConstructor
@Data
public class Subscriber {
    @Id
    private String id;
    @OneToOne
    private User user;
    @OneToMany
    private List<CruiseShip> cruiseShips;
}
