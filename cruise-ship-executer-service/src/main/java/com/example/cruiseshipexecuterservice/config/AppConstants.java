package com.example.cruiseshipexecuterservice.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class AppConstants {
    @Value("${kafka.topic.name.new-cruise-ship}")
    public final String TOPIC_NAME_NEW_CRUISE_SHIP="new-cruise-ship";
}
