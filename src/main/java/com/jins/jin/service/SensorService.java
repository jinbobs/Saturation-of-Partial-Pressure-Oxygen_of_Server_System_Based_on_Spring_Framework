package com.jins.jin.service;

import com.jins.jin.entity.SensorData;
import com.jins.jin.repository.SensorDataRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SensorService {


    private final SensorDataRepository sensorDataRepository;

    @Autowired
    public SensorService(SensorDataRepository sensorDataRepository) {
        this.sensorDataRepository = sensorDataRepository;
    }
    @Transactional
    public void saveSensorData(SensorData sensorData) {
        sensorDataRepository.save(sensorData); // 데이터 저장
    }

    //나중에 사용할수도 있는  메소드들 미리 써놓음
    public SensorData getLatestSensorData() {

        return sensorDataRepository.findTopByOrderByIdDesc();
    }
    public List<SensorData> getAllSensorData() {
        return sensorDataRepository.findAll();
    }
}
