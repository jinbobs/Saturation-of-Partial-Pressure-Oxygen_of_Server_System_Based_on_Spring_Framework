package com.jins.jin.config;


import com.jins.jin.entity.SensorData;
import com.jins.jin.entity.Users;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public SensorData sensorData() {
        return new SensorData();
    }

    @Bean
    public Users users(){
        return new Users();
    }
}