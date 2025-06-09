package com.example.di.controller;

import com.example.di.service.MyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor  // 생성자 주입을 Lombok으로 간소화
public class MyController {

    private final MyService myService;  // 생성자 주입 (final 필드)
    
    @GetMapping("/message/{name}")
    public String getMessage(@PathVariable String name) {
        return myService.getMessage(name);
    }
} 