package com.jins.jin.entity;


import jakarta.persistence.*;

@Entity
@Table(name = "sensordata")
public class SensorData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // IDENTITY로 변경
    private Long id; // 데이터베이스에서 자동 생성되는 ID
    private double hr; // 맥박
    private double spo2; // 산소포화도

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getHr() {
        return hr;
    }

    public void setHr(double hr) {
        this.hr = hr;
    }

    public double getSpo2() {
        return spo2;
    }

    public void setSpo2(double spo2) {
        this.spo2 = spo2;
    }

    // 기본 생성자 및 매개변수 생성자
    public SensorData() {}

    public SensorData(double hr, double spo2) {
        this.hr = hr;
        this.spo2 = spo2;
    }

}

