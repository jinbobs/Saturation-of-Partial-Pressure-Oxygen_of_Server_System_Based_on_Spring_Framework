package com.jins.jin.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.stereotype.Component;

@Component
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((authz) -> authz
                        // 인증 없이 접근할 수 있는 경로 설정
                        .requestMatchers( "/login", "/register", "/css/**", "/js/**").permitAll()
                        // 그 외 모든 요청은 인증 필요
                        .anyRequest().authenticated()
                )
                .formLogin((form) -> form
                        .loginPage("/login")  // 로그인 페이지 경로 설정
                        .defaultSuccessUrl("/", true)  // 로그인 성공 후 리다이렉트할 경로
                        .permitAll()  // 로그인 페이지는 모두에게 허용
                )
                .logout((logout) -> logout.permitAll());  // 로그아웃 허용


        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();  // 비밀번호 암호화를 위한 BCrypt 사용
    }
}