package com.jins.jin.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.stereotype.Component;

@Component
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf ->csrf.disable()) //세션 비활성화하기!
                .authorizeHttpRequests((authz) -> authz
                        // 인증 없이 접근할 수 있는 경로 설정
                        .requestMatchers( "/register", "/css/**", "/js/**","/images/**","/","/login").permitAll()

                        // 그 외 모든 요청은 인증 필요
                        .anyRequest().authenticated()
                )
                .formLogin((form) -> form
                        .loginPage("/login")
                        .defaultSuccessUrl("/", true)  // 로그인 성공 시 리다이렉트할 경로
                        .failureUrl("/?error=true") // 로그인 실패 시 메인 페이지로 리디렉트
                        .permitAll()
                )
                .logout((logout) -> logout
                        .logoutUrl("/logout")  // 로그아웃 URL 설정
                        .logoutSuccessUrl("/")  // 로그아웃 성공 시 리다이렉트할 경로
                        .invalidateHttpSession(true)  // 세션 무효화
                        .deleteCookies("JSESSIONID")  // 세션 쿠키 삭제
                        .permitAll()
                );


        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();  //  BCrypt
    }
}