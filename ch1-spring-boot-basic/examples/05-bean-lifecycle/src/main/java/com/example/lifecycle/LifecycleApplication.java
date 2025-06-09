package com.example.lifecycle;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

import com.example.lifecycle.service.CustomLifecycleBean;
import com.example.lifecycle.service.InterfaceLifecycleBean;

@SpringBootApplication
public class LifecycleApplication {

    public static void main(String[] args) {
        System.out.println("애플리케이션 컨텍스트 시작 전");
        ConfigurableApplicationContext context = SpringApplication.run(LifecycleApplication.class, args);
        System.out.println("애플리케이션 컨텍스트 시작 후");
        
        // 애플리케이션 종료 (소멸 콜백 트리거)
        context.close();
        System.out.println("애플리케이션 컨텍스트 종료 후");
    }
    
    @Bean(initMethod = "customInit", destroyMethod = "customDestroy")
    public CustomLifecycleBean customLifecycleBean() {
        return new CustomLifecycleBean();
    }
    
    @Bean
    public InterfaceLifecycleBean interfaceLifecycleBean() {
        return new InterfaceLifecycleBean();
    }
} 