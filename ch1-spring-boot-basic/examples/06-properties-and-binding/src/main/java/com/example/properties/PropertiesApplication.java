package com.example.properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

import com.example.properties.config.AppProperties;

@SpringBootApplication
@EnableConfigurationProperties(AppProperties.class)
public class PropertiesApplication {

    @Value("${app.name:기본 이름}")
    private String appName;
    
    @Value("${app.enabled}")
    private boolean enabled;

    public static void main(String[] args) {
        SpringApplication.run(PropertiesApplication.class, args);
    }
    
    @Bean
    public CommandLineRunner printProperties(AppProperties appProperties) {
        return args -> {
            System.out.println("========== @Value를 통한 속성 바인딩 ==========");
            System.out.println("앱 이름: " + appName);
            System.out.println("활성화 여부: " + enabled);
            
            System.out.println("\n========== @ConfigurationProperties를 통한 속성 바인딩 ==========");
            System.out.println("앱 이름: " + appProperties.getName());
            System.out.println("앱 설명: " + appProperties.getDescription());
            System.out.println("앱 버전: " + appProperties.getVersion());
            System.out.println("활성화 여부: " + appProperties.isEnabled());
            System.out.println("타임아웃: " + appProperties.getTimeout());
            System.out.println("최대 크기: " + appProperties.getMaxSize());
            
            System.out.println("\n=== 서버 속성 ===");
            System.out.println("호스트: " + appProperties.getServer().getHost());
            System.out.println("포트: " + appProperties.getServer().getPort());
            
            System.out.println("\n=== 리스트 속성 ===");
            appProperties.getListValues().forEach(System.out::println);
            
            System.out.println("\n=== 맵 속성 ===");
            appProperties.getMapValues().forEach((key, value) -> 
                System.out.println(key + ": " + value));
            
            System.out.println("\n=== 중첩 속성 ===");
            System.out.println("이름: " + appProperties.getNested().getName());
            System.out.println("값: " + appProperties.getNested().getValue());
        };
    }
} 