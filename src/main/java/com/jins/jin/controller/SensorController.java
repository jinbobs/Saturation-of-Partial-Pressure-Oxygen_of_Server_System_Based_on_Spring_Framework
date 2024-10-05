package com.jins.jin.controller;

import com.jins.jin.entity.SensorData;
import com.jins.jin.repository.SensorDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SensorController {

    @Autowired
    private SensorDataRepository sensorDataRepository;

    // 메인 페이지 매핑
    @GetMapping("/")
    public String mainPage() {
        return "main"; // main.html 반환
    }

    @GetMapping("/sensor-data")
    public String getSensorData(Model model) {
        try {
            // Python 스크립트 실행
            ProcessBuilder processBuilder = new ProcessBuilder("python", "C:\\Users\\최진성\\Desktop\\산소포화도\\o2.py");
            processBuilder.redirectErrorStream(true);
            Process process = processBuilder.start();

            // 프로세스 종료 대기
            process.waitFor();
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", "Python 실행 중 오류가 발생했습니다: " + e.getMessage());
            return "sensor";
        }

        // DB에서 가장 최근 데이터를 가져오기
        SensorData latestSensorData = sensorDataRepository.findTopByOrderByIdDesc();
        model.addAttribute("sensorData", latestSensorData);

        return "sensor"; // sensor.html 반환
    }
}
