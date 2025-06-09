package com.example.devtools;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
@RestController
@Slf4j
public class DevtoolsApplication {

    public static void main(String[] args) {
        SpringApplication.run(DevtoolsApplication.class, args);
    }
    
    @GetMapping("/")
    public Map<String, Object> home() {
        log.info("홈 페이지 요청 처리");
        
        Map<String, Object> response = new HashMap<>();
        response.put("message", "DevTools & Actuator 예제에 오신 것을 환영합니다!");
        response.put("description", "개발 중 코드를 변경하면 자동으로 재시작됩니다");
        
        // 액추에이터 정보
        Map<String, String> actuator = new HashMap<>();
        actuator.put("health", "/actuator/health");
        actuator.put("info", "/actuator/info");
        actuator.put("metrics", "/actuator/metrics");
        actuator.put("prometheus", "/actuator/prometheus");
        response.put("actuator", actuator);
        
        return response;
    }
    
    @GetMapping("/throw")
    public String throwException() {
        log.warn("예외 발생 엔드포인트 호출됨");
        throw new RuntimeException("의도적으로 발생시킨 예외입니다");
    }
} 