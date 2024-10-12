package com.jins.jin.controller;

import com.jins.jin.entity.SensorData;
import com.jins.jin.entity.Users;
import com.jins.jin.repository.SensorDataRepository;
import com.jins.jin.repository.UserRepository;
import com.jins.jin.service.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder; // PasswordEncoder 추가
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;

import java.util.List;

@Controller
public class MyPageController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder; // PasswordEncoder 주입

    @Autowired
    private MyUserDetailsService myUserDetailsService;

    @Autowired
    private SensorDataRepository  sensorDataRepository;

    // 마이페이지 요청 처리
    @GetMapping("/mypage")
    public String showMyPage(Model model) {
        Long userId = getLoggedInUserId();
        Users user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));

        model.addAttribute("user", user);

        // 해당 유저의 센서 데이터 리스트 가져오기
        List<SensorData> sensorDataList = sensorDataRepository.findByUser(user);

        SensorData recentSensorData = sensorDataRepository.findTopByUserIdOrderByMeasurementDateDesc(userId);

        // 최근 데이터를 모델에 추가하여 뷰에서 접근 가능하게 함
        model.addAttribute("recentSensorData", recentSensorData);

        // 모델에 데이터 추가
        model.addAttribute("sensorDataList", sensorDataList);
        return "mypage";
    }

    // 사용자 정보 수정 요청 처리
    @PostMapping("/update-profile")
    public String updateProfile(@RequestParam("username") String username,
                                @RequestParam("password") String password) {
        Long userId = getLoggedInUserId();
        Users user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));

        // 사용자 이름 수정
        user.setUsername(username);

        // 비밀번호가 입력되었으면 암호화하여 저장
        if (!password.isEmpty()) {
            String encryptedPassword = passwordEncoder.encode(password); // 비밀번호 암호화
            user.setPassword(encryptedPassword);
        }

        // 변경된 사용자 정보 저장
        userRepository.save(user);

        return "redirect:/mypage"; // 마이페이지로 리다이렉트
    }

    // 현재 로그인된 사용자의 ID를 반환하는 메서드
    private Long getLoggedInUserId() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            String username = ((UserDetails) principal).getUsername();
            Users user = userRepository.findByUsername(username);
            return user.getId();
        }
        return null;
    }
}
