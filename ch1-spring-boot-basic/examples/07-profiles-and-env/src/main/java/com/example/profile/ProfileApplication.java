package com.example.profile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;

@SpringBootApplication
public class ProfileApplication {

    @Value("${app.message}")
    private String appMessage;
    
    @Value("${spring.profiles.active:default}")
    private String activeProfile;
    
    @Autowired
    private Environment environment;

    public static void main(String[] args) {
        SpringApplication.run(ProfileApplication.class, args);
    }
    
    @Bean
    public CommandLineRunner profileDemo() {
        return args -> {
            System.out.println("\n=== 프로파일 및 환경 변수 정보 ===");
            System.out.println("활성화된 프로파일: " + activeProfile);
            System.out.println("환경에서 직접 조회: " + String.join(", ", environment.getActiveProfiles()));
            System.out.println("애플리케이션 메시지: " + appMessage);
            System.out.println("서버 포트: " + environment.getProperty("server.port"));
            
            // 환경 변수 또는 시스템 프로퍼티에서 정보 출력
            System.out.println("\n=== 환경 변수 및 시스템 프로퍼티 ===");
            System.out.println("JAVA_HOME: " + environment.getProperty("JAVA_HOME"));
            System.out.println("user.home: " + environment.getProperty("user.home"));
            
            // 특정 프로파일에만 있는 속성 확인
            System.out.println("\n=== 특정 프로파일 속성 ===");
            System.out.println("app.log-level: " + environment.getProperty("app.log-level"));
            System.out.println("h2 콘솔 활성화: " + environment.getProperty("spring.h2.console.enabled"));
            
            // 기능 플래그 확인
            System.out.println("\n=== 기능 플래그 ===");
            System.out.println("feature-a 활성화: " + environment.getProperty("app.feature-flags.feature-a"));
            System.out.println("feature-b 활성화: " + environment.getProperty("app.feature-flags.feature-b"));
        };
    }
} 