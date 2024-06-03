package com.jins.jin.config;

import com.jins.jin.entity.RandomValue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public RandomValue randomValue() {
        return new RandomValue();
    }
}