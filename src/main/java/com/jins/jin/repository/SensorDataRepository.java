package com.jins.jin.repository;

import com.jins.jin.entity.SensorData;
import com.jins.jin.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SensorDataRepository extends JpaRepository<SensorData, Long> {
    SensorData findTopByOrderByIdDesc();

    // 특정 user_id에 대한 가장 최신 데이터 가져오기
    SensorData findTopByUserOrderByIdDesc(Users user);
    // 특정 사용자의 모든 SensorData 가져오기
    List<SensorData> findByUser(Users user);

}