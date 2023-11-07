package com.example.cruiseonspring.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaConfig {

    @Bean
    public NewTopic taskTopic() {
        return TopicBuilder.name(AppConstants.TOPIC_NAME_NEW_CRUISE_SHIP)
                .partitions(1)
                .replicas(1)
                .build();
    }
}