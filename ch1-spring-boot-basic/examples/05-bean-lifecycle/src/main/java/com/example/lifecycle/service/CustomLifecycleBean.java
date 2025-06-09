package com.example.lifecycle.service;

public class CustomLifecycleBean {
    
    public CustomLifecycleBean() {
        System.out.println("CustomLifecycleBean 생성자 호출");
    }
    
    public void customInit() {
        System.out.println("CustomLifecycleBean 커스텀 초기화 메서드 호출");
    }
    
    public void customDestroy() {
        System.out.println("CustomLifecycleBean 커스텀 소멸 메서드 호출");
    }
} 