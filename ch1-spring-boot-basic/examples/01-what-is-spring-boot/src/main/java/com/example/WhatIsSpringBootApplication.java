package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class WhatIsSpringBootApplication {

    public static void main(String[] args) {
        SpringApplication.run(WhatIsSpringBootApplication.class, args);
    }
    
    @GetMapping("/")
    public String home() {
        return "스프링 부트 애플리케이션이 성공적으로 시작되었습니다!";
    }
} 