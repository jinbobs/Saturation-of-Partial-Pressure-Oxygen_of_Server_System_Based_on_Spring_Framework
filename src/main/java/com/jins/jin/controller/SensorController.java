package com.jins.jin.controller;


import com.jins.jin.entity.SensorData;
import com.jins.jin.service.SensorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;



    @Controller
    public class SensorController {

        @Autowired
        private final SensorService sensorService;



        @Autowired
        public SensorController(SensorService sensorService) {
            this.sensorService = sensorService;
        }


        @GetMapping("/")
        public String mainPage() {
            return "main"; // main.html 반환
        }

        // POST 요청을 통해 SensorData 수신



        // GET 요청을 통해 SensorData를 가져오는 메서드
        @GetMapping("/sensor-data")
        public String getSensorData(Model model) {
            try {
                // Python 스크립트 실행
                ProcessBuilder processBuilder = new ProcessBuilder("python", "C:\\Users\\최진성\\Desktop\\산소포화도\\o2.py");
                processBuilder.redirectErrorStream(true);
                Process process = processBuilder.start();

                // Python 실행 결과 읽기
                BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
                StringBuilder output = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    output.append(line).append("\n");
                }

                process.waitFor();


                System.out.println("Python 실행 결과: " + output.toString());

            } catch (Exception e) {
                e.printStackTrace();
                model.addAttribute("error", "Python 실행 중 오류가 발생했습니다: " + e.getMessage());
                return "sensor";
            }


            SensorData sensorData = sensorService.getLatestSensorData();


            if (sensorData != null) {
                model.addAttribute("hr", sensorData.getHr());
                model.addAttribute("spo2", sensorData.getSpo2());
            } else {
                // 데이터가 없을 때 처리
                model.addAttribute("hr", "데이터 없음");
                model.addAttribute("spo2", "데이터 없음");
            }

            // DB에 저장된 모든 데이터를 차트로 표시하기 위해 추가
            List<SensorData> sensorDataList = sensorService.getAllSensorData();
            model.addAttribute("sensorDataList", sensorDataList);


            return "sensor"; // sensor.html 반환
        }

        @PostMapping("/sensor-data")
        public ResponseEntity<String> receiveSensorData(@RequestBody SensorData sensorData) {
            try {
                // 데이터 로그 출력
                System.out.println("Received HR: " + sensorData.getHr());
                System.out.println("Received SPO2: " + sensorData.getSpo2());

                // 데이터 저장
                sensorService.saveSensorData(sensorData);
                return ResponseEntity.ok("Data received successfully");
            } catch (Exception e) {
                // 예외 발생 시 서버 에러와 메시지 출력
                e.printStackTrace();  // 에러 로그 출력
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
            }
        }

    }
