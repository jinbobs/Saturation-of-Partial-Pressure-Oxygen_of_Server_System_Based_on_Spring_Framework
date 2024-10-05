package com.jins.jin.repository;

import com.jins.jin.entity.SensorData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface SensorDataRepository extends JpaRepository<SensorData, Long> {
    SensorData findTopByOrderByIdDesc();

}