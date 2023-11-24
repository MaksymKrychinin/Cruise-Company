package com.example.cruiseshipexecuterservice.entity.mongo;

import lombok.Getter;


@Getter
public enum StatisticTypeBaseEntity {
    NEW_CRUISE_SHIP_STATISTIC("new-cruise-ship"),
    NEW_USER_STATISTIC("new-user"),
    NEW_USER_ORDER_STATISTIC("new-user-order");

    private final String value;

    StatisticTypeBaseEntity(String value) {
        this.value = value;
    }
}
