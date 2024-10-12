package com.jins.jin.controller;

import com.jins.jin.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import com.jins.jin.entity.Users;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // 회원가입 페이지를 보여주는 GET 요청 처리
    @GetMapping("/register")
    public String showRegisterPage() {
        return "register";  // 회원가입 페이지로 이동
    }


    // 회원가입 처리
    @PostMapping("/register")
    public String registerUser(Users users) {
        // 비밀번호 암호화 후 저장
        users.setPassword(passwordEncoder.encode(users.getPassword()));
        userRepository.save(users);
        System.out.println("저장완료");
        return "redirect:/";
    }




}