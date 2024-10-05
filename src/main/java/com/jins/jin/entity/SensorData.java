package com.jins.jin.entity;


import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "sensordata")
public class SensorData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private double hr; // 맥박
    private double spo2; // 산소포화도

    private LocalDate measurementDate;


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

    public LocalDate getMeasurementDate() {
        return measurementDate;
    }

    public void setMeasurementDate(LocalDate measurementDate) {
        this.measurementDate = measurementDate;
    }
    // 기본 생성자 및 매개변수 생성자
    public SensorData() {}

    public SensorData(double hr, double spo2) {
        this.hr = hr;
        this.spo2 = spo2;
    }

}

