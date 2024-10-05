package com.jins.jin.controller;

import com.jins.jin.entity.SensorData;
import com.jins.jin.entity.Users;
import com.jins.jin.repository.SensorDataRepository;
import com.jins.jin.repository.UserRepository;
import com.jins.jin.service.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class SensorController {

    @Autowired
    private SensorDataRepository sensorDataRepository;

    @Autowired
    private MyUserDetailsService myUserDetailsService;
    @Autowired
    private UserRepository userRepository;

    // 메인
    @GetMapping("/")
    public String mainPage() {
        return "main";
    }

  //sensor-date 매핑
    @GetMapping("/sensor-data")
    public String getSensorData(Model model) {
        try {
            // 로그인된 사용자의 ID 가져오기
            Long userId = myUserDetailsService.getLoggedInUserId();
            Users user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));

            // Python 스크립트 실행
            ProcessBuilder processBuilder = new ProcessBuilder("python", "C:\\Users\\최진성\\Desktop\\산소포화도\\o2.py", String.valueOf(userId));
            processBuilder.redirectErrorStream(true);
            Process process = processBuilder.start();
            process.waitFor();


            List<SensorData> sensorDataList = sensorDataRepository.findByUser(user);

            SensorData latestSensorData = sensorDataRepository.findTopByUserOrderByIdDesc(user);

            if (latestSensorData != null) {
                model.addAttribute("sensorData", latestSensorData);
            } else {
                model.addAttribute("sensorData", null);
            }

            model.addAttribute("sensorDataList", sensorDataList);

        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", "Python 실행 중 오류가 발생했습니다: " + e.getMessage());
        }

        return "sensor";
    }
}