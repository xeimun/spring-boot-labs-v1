package com.example.profile.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
public class ProfileConfig {

    @Bean
    @Profile("dev")  // dev 프로파일에서만 활성화
    public String devMessage() {
        return "개발 환경에서만 사용 가능한 빈입니다.";
    }
    
    @Bean
    @Profile("prod")  // prod 프로파일에서만 활성화
    public String prodMessage() {
        return "운영 환경에서만 사용 가능한 빈입니다.";
    }
    
    @Bean
    @Profile("!prod")  // prod 프로파일이 아닌 모든 환경에서 활성화
    public String nonProdMessage() {
        return "운영 환경이 아닌 경우에만 사용 가능한 빈입니다.";
    }
    
    @Bean
    @Profile("dev & !legacy")  // dev 프로파일이면서 legacy 프로파일이 아닌 경우 활성화
    public String modernDevMessage() {
        return "최신 개발 환경에서만 사용 가능한 빈입니다.";
    }
} 