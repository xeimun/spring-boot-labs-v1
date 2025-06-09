package com.example.logging.service;

import java.util.UUID;

import org.slf4j.MDC;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j  // Lombok의 @Slf4j 사용하여 log 필드 자동 생성
public class LoggingService {
    
    public void logTrace() {
        try (MDC.MDCCloseable closeable = MDC.putCloseable("requestId", generateRequestId())) {
            log.trace("이것은 TRACE 레벨 로그 메시지입니다");
            log.trace("매개변수가 있는 TRACE 로그: {}, {}", "값1", "값2");
        }
    }
    
    public void logDebug() {
        try (MDC.MDCCloseable closeable = MDC.putCloseable("requestId", generateRequestId())) {
            log.debug("이것은 DEBUG 레벨 로그 메시지입니다");
            log.debug("매개변수가 있는 DEBUG 로그: {}, {}", "값1", "값2");
        }
    }
    
    public void logInfo() {
        try (MDC.MDCCloseable closeable = MDC.putCloseable("requestId", generateRequestId())) {
            log.info("이것은 INFO 레벨 로그 메시지입니다");
            log.info("매개변수가 있는 INFO 로그: {}, {}", "값1", "값2");
        }
    }
    
    public void logWarn() {
        try (MDC.MDCCloseable closeable = MDC.putCloseable("requestId", generateRequestId())) {
            log.warn("이것은 WARN 레벨 로그 메시지입니다");
            log.warn("매개변수가 있는 WARN 로그: {}, {}", "값1", "값2");
        }
    }
    
    public void logError() {
        try (MDC.MDCCloseable closeable = MDC.putCloseable("requestId", generateRequestId())) {
            log.error("이것은 ERROR 레벨 로그 메시지입니다");
            log.error("매개변수가 있는 ERROR 로그: {}, {}", "값1", "값2");
            
            try {
                // 예외 발생 및 로깅
                throw new RuntimeException("테스트 예외 발생");
            } catch (Exception e) {
                log.error("예외가 발생했습니다", e);
            }
        }
    }
    
    /**
     * 문자열 연결과 매개변수화된 메시지 성능 비교 예제
     */
    public void performanceExample(String username, String action) {
        // 권장하지 않음 (문자열 연결) - 로그 레벨에 관계없이 문자열 연결 연산 발생
        log.debug("사용자 " + username + "가 " + action + " 작업을 수행했습니다");
        
        // 권장함 (매개변수화된 메시지) - 로그 레벨이 활성화된 경우에만 문자열 형식화 발생
        log.debug("사용자 {}가 {} 작업을 수행했습니다", username, action);
    }
    
    private String generateRequestId() {
        return UUID.randomUUID().toString();
    }
} 