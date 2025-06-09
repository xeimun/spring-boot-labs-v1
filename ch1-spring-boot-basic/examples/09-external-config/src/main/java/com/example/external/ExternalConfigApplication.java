package com.example.external;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;

import com.example.external.config.ExternalConfig;

@SpringBootApplication
@EnableConfigurationProperties(ExternalConfig.class)
public class ExternalConfigApplication {

    @Value("${app.name:기본 애플리케이션}")
    private String appName;
    
    @Value("${app.description:설명 없음}")
    private String appDescription;

    public static void main(String[] args) {
        SpringApplication.run(ExternalConfigApplication.class, args);
    }
    
    @Bean
    public CommandLineRunner configDemo(Environment environment, ExternalConfig config) {
        return args -> {
            System.out.println("\n=== 외부 설정 예제 ===");
            
            // @Value 어노테이션으로 주입된 값
            System.out.println("\n@Value 어노테이션으로 주입된 값:");
            System.out.println("앱 이름: " + appName);
            System.out.println("앱 설명: " + appDescription);
            
            // Environment에서 값 가져오기
            System.out.println("\nEnvironment에서 값 가져오기:");
            System.out.println("서버 포트: " + environment.getProperty("server.port", "8080"));
            System.out.println("활성 프로파일: " + String.join(", ", environment.getActiveProfiles()));
            
            // 시스템 속성 및 환경 변수
            System.out.println("\n시스템 속성 및 환경 변수:");
            System.out.println("JAVA_HOME: " + environment.getProperty("JAVA_HOME"));
            System.out.println("user.home: " + environment.getProperty("user.home"));
            
            // @ConfigurationProperties로 바인딩된 값
            System.out.println("\n@ConfigurationProperties로 바인딩된 값:");
            System.out.println("데이터베이스 URL: " + config.getDb().getUrl());
            System.out.println("데이터베이스 사용자명: " + config.getDb().getUsername());
            System.out.println("데이터베이스 비밀번호: " + maskPassword(config.getDb().getPassword()));
            System.out.println("API 키: " + maskApiKey(config.getApi().getKey()));
            System.out.println("백엔드 URL: " + config.getApi().getUrl());
            
            System.out.println("\n설정 우선순위 참고:");
            System.out.println("1. 명령행 인수 (--app.name=명령행에서_설정한_이름)");
            System.out.println("2. 시스템 속성 (-Dapp.name=시스템_속성에서_설정한_이름)");
            System.out.println("3. 환경 변수 (APP_NAME=환경변수에서_설정한_이름)");
            System.out.println("4. 외부 설정 파일 (/config/application.yml)");
            System.out.println("5. 패키지 내부 설정 파일 (src/main/resources/application.yml)");
        };
    }
    
    private String maskPassword(String password) {
        if (password == null || password.isEmpty()) {
            return "[비어 있음]";
        }
        return "*".repeat(password.length());
    }
    
    private String maskApiKey(String apiKey) {
        if (apiKey == null || apiKey.isEmpty()) {
            return "[비어 있음]";
        }
        if (apiKey.length() <= 4) {
            return "*".repeat(apiKey.length());
        }
        return apiKey.substring(0, 4) + "*".repeat(apiKey.length() - 4);
    }
} 