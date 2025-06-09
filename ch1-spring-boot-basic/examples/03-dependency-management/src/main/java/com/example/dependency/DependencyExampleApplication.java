package com.example.dependency;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
@RestController
@Slf4j
public class DependencyExampleApplication {

    public static void main(String[] args) {
        SpringApplication.run(DependencyExampleApplication.class, args);
    }
    
    @GetMapping("/")
    public Map<String, Object> home() {
        log.info("홈 페이지 요청 처리");
        
        Map<String, Object> response = new HashMap<>();
        response.put("message", "스프링 부트 의존성 관리 예제");
        response.put("server", "Undertow");  // Tomcat 대신 Undertow 사용
        
        // 액추에이터 정보
        Map<String, String> actuator = new HashMap<>();
        actuator.put("health", "/actuator/health");
        actuator.put("info", "/actuator/info");
        response.put("actuator", actuator);
        
        return response;
    }
} 