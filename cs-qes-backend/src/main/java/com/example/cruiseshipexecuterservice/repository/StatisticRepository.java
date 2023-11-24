package com.example.cruiseshipexecuterservice.repository;

import com.example.cruiseshipexecuterservice.entity.mongo.Statistic;
import com.example.cruiseshipexecuterservice.entity.mongo.StatisticTypeBaseEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface StatisticRepository extends MongoRepository<Statistic, String> {
    List<Statistic> findAll();

    Optional<Statistic> findByEntityType(StatisticTypeBaseEntity statisticTypeBaseEntity);

    @Override
    Statistic save(Statistic entity);
}
