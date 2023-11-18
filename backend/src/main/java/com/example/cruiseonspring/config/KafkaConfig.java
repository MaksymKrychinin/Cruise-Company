package com.example.cruiseonspring.config;

import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;


@Configuration
@RequiredArgsConstructor
public class KafkaConfig {
    private final AppConstants appConstants;
    @Bean
    public NewTopic taskTopic() {
        return TopicBuilder.name(appConstants.TOPIC_NAME_NEW_CRUISE_SHIP)
                .partitions(1)
                .replicas(1)
                .build();
    }
}
