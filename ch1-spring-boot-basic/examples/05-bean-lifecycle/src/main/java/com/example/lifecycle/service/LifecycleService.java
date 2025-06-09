package com.example.lifecycle.service;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.stereotype.Service;

@Service
public class LifecycleService {

    public LifecycleService() {
        System.out.println("LifecycleService 생성자 호출");
    }
    
    @PostConstruct
    public void init() {
        System.out.println("LifecycleService @PostConstruct 메서드 호출");
    }
    
    @PreDestroy
    public void cleanup() {
        System.out.println("LifecycleService @PreDestroy 메서드 호출");
    }
    
    public String getMessage() {
        return "LifecycleService 사용 중";
    }
} 