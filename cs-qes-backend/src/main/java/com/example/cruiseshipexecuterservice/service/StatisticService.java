package com.example.cruiseshipexecuterservice.service;

import com.example.cruiseshipexecuterservice.entity.jms.CruiseShip;
import com.example.cruiseshipexecuterservice.entity.jms.User;
import com.example.cruiseshipexecuterservice.entity.jms.UserOrder;
import com.example.cruiseshipexecuterservice.entity.mongo.Statistic;
import com.example.cruiseshipexecuterservice.entity.mongo.StatisticTypeBaseEntity;
import com.example.cruiseshipexecuterservice.repository.StatisticRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.Month;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class StatisticService {
    private final StatisticRepository statisticRepository;

    @Transactional
    public void updateStatistic(CruiseShip cruiseShip) {
        Statistic statistic = statisticRepository.findByEntityType(StatisticTypeBaseEntity.NEW_CRUISE_SHIP_STATISTIC)
                .orElse(Statistic.builder()
                        .entityType(StatisticTypeBaseEntity.NEW_CRUISE_SHIP_STATISTIC)
                        .yearMonthStatistic(new HashMap<>())
                        .build());
        Map<Integer, Map<Month, Integer>> yearMonthStatistic = statistic.getYearMonthStatistic();
        LocalDate date = LocalDate.now();
        yearMonthStatistic.computeIfAbsent(date.getYear(), k -> new HashMap<>())
                .merge(date.getMonth(), 1, Integer::sum);
        statisticRepository.save(statistic);
    }

    @Transactional
    public void updateStatistic(User user) {
        Statistic statistic = statisticRepository.findByEntityType(StatisticTypeBaseEntity.NEW_USER_STATISTIC)
                .orElse(Statistic.builder()
                        .entityType(StatisticTypeBaseEntity.NEW_USER_STATISTIC)
                        .yearMonthStatistic(new HashMap<>())
                        .build());
        Map<Integer, Map<Month, Integer>> yearMonthStatistic = statistic.getYearMonthStatistic();
        LocalDate date = LocalDate.now();
        yearMonthStatistic.computeIfAbsent(date.getYear(), k -> new HashMap<>())
                .merge(date.getMonth(), 1, Integer::sum);
        statisticRepository.save(statistic);
    }

    @Transactional
    public void updateStatistic(UserOrder userOrder) {
        Statistic statistic = statisticRepository.findByEntityType(StatisticTypeBaseEntity.NEW_USER_ORDER_STATISTIC)
                .orElse(Statistic.builder()
                        .entityType(StatisticTypeBaseEntity.NEW_USER_ORDER_STATISTIC)
                        .yearMonthStatistic(new HashMap<>())
                        .build());
        Map<Integer, Map<Month, Integer>> yearMonthStatistic = statistic.getYearMonthStatistic();
        LocalDate date = LocalDate.now();
        yearMonthStatistic.computeIfAbsent(date.getYear(), k -> new HashMap<>())
                .merge(date.getMonth(), 1, Integer::sum);
        statisticRepository.save(statistic);
    }

}
