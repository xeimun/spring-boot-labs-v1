package com.example.di.service;

import com.example.di.repository.MyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MyService {
    
    // 필드 주입 예제 (권장하지 않음)
    // @Autowired
    // private MyRepository myRepository;
    
    private final MyRepository myRepository;
    
    // 생성자 주입 예제 (권장)
    @Autowired  // 생략 가능 (단일 생성자인 경우)
    public MyService(MyRepository myRepository) {
        this.myRepository = myRepository;
    }
    
    // 세터 주입 예제 (선택적 의존성인 경우)
    /*
    private MyRepository myRepository;
    
    @Autowired
    public void setMyRepository(MyRepository myRepository) {
        this.myRepository = myRepository;
    }
    */
    
    public String getMessage(String name) {
        return myRepository.findMessage() + ", " + name + "!";
    }
} 