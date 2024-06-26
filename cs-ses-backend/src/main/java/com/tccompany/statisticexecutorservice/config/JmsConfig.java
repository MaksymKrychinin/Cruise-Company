package com.tccompany.statisticexecutorservice.config;

import com.tccompany.statisticexecutorservice.entity.jms.CruiseShip;
import com.tccompany.statisticexecutorservice.entity.jms.User;
import com.tccompany.statisticexecutorservice.entity.jms.UserOrder;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import jakarta.jms.ConnectionFactory;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;

import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableJms
public class JmsConfig {

    @Value("${spring.activemq.broker-url}")
    private String BROKER_URL;
    @Value("${spring.activemq.user}")
    private String BROKER_USERNAME;
    @Value("${spring.activemq.password}")
    private String BROKER_PASSWORD;

    @Bean
    public ConnectionFactory connectionFactory() {
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory();
        connectionFactory.setTrustAllPackages(true);
        connectionFactory.setBrokerURL(BROKER_URL);
        connectionFactory.setPassword(BROKER_USERNAME);
        connectionFactory.setUserName(BROKER_PASSWORD);
        return connectionFactory;
    }

    @Bean
    public MessageConverter messageConverter() {
        MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
        converter.setTargetType(MessageType.BYTES);
        converter.setObjectMapper(objectMapper());
        converter.setTypeIdPropertyName("CruiseShip");//TODO: change to UserOrder/User
        Map<String, Class<?>> typeIdMappings = new HashMap<>();
        typeIdMappings.put("CruiseShip",
              CruiseShip.class);
        typeIdMappings.put("UserOrder",
                UserOrder.class);
        typeIdMappings.put("User",
                User.class);
        converter.setTypeIdMappings(typeIdMappings);
        return converter;
    }

    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        return mapper;
    }
}

