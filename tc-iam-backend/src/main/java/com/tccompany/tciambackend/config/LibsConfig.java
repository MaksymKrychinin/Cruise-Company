package com.tccompany.tciambackend.config;

import com.tccompany.tcvalidation.utils.ValidationUtils;
import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class LibsConfig {
    private final Validator validator;

    @Bean
    public ValidationUtils validationUtils() {
        return new ValidationUtils(validator);
    }
}