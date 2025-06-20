package com.project.capstone.config;

import com.project.capstone.security.JwtAuthenticationFilter;
import com.project.capstone.security.JwtTokenProvider;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;

@Configuration
public class SecurityConfig {

    @Value("${jwt.secret}")
    private String jwtSecret;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http, JwtTokenProvider jwtTokenProvider) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        //requestMatchers() -> 특정 URL 경로를 지정
                        //.permitAll() -> 해당 경로는 인증 불필요
                        .requestMatchers(HttpMethod.POST, "/users").permitAll()
                        .requestMatchers(HttpMethod.POST, "/auth/login").permitAll()
                        .requestMatchers(HttpMethod.POST, "/auth/reissue").permitAll()
                        // 다른 요청들은 .authenticated()로 전부 인증이 필요하다
                        .anyRequest().authenticated()
                )
                // 이 아래 부분은 내가 만든 커스텀 필터를 기존 필터 체인에 끼워 넣는 부분이다.
                .addFilterBefore(
                        new JwtAuthenticationFilter(jwtTokenProvider),
                        UsernamePasswordAuthenticationFilter.class
                );
        return http.build();
    }

    @Bean
    public SecretKey secretKey() {
        //시크릿 키를 application.yml로 따로 분리
        return Keys.hmacShaKeyFor(jwtSecret.getBytes(StandardCharsets.UTF_8));
    }

    @Bean
    public JwtTokenProvider jwtTokenProvider(SecretKey secretKey) {
        return new JwtTokenProvider(secretKey);
    }
}
