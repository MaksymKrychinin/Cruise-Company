package com.tccompany.statisticexecutorservice.repository;

import com.tccompany.statisticexecutorservice.entity.mongo.Statistic;
import com.tccompany.statisticexecutorservice.entity.mongo.StatisticTypeBaseEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface StatisticRepository extends MongoRepository<Statistic, String> {
    List<Statistic> findAll();

    Optional<Statistic> findByEntityType(StatisticTypeBaseEntity statisticTypeBaseEntity);

    @Override
    Statistic save(Statistic entity);
}
