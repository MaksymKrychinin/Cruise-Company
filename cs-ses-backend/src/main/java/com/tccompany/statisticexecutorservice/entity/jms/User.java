package com.tccompany.statisticexecutorservice.entity.jms;


import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    String id;
    @Column(unique = true)
    String email;
    @Column(unique = true)
    String telegramNickname;
    String telegramChatId;
    @OneToMany(fetch = FetchType.EAGER)
    private List<CruiseShip> cruiseShips;
}
