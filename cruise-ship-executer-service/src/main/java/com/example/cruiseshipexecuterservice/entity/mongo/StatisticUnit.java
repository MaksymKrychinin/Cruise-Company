package com.example.cruiseshipexecuterservice.entity.mongo;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.time.LocalDate;
import java.util.Map;

@Document(collection = "statistic")
public class StatisticUnit {
    @MongoId
    String id;
    Map<String, Map<LocalDate, Integer>> statistic;
}
