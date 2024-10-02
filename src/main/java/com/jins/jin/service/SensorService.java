package com.jins.jin.service;

import com.jins.jin.entity.SensorData;
import com.jins.jin.repository.SensorDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SensorService {

    private final SensorDataRepository sensorDataRepository;

    @Autowired
    public void saveSensorData(SensorData sensorData) {
        sensorDataRepository.save(sensorData); // 데이터 저장
    }
    public SensorService(SensorDataRepository sensorDataRepository) {
        this.sensorDataRepository = sensorDataRepository;
    }



    public SensorData getLatestSensorData() {
        // 가장 최근의 SensorData를 가져오는 로직
        return sensorDataRepository.findTopByOrderByIdDesc(); // 데이터베이스의 최신 데이터 반환
    }
    public List<SensorData> getAllSensorData() {
        return sensorDataRepository.findAll();
    }
}
