package com.example.logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.logging.service.LoggingService;

@SpringBootApplication
@RestController
public class LoggingApplication {
    
    private static final Logger logger = LoggerFactory.getLogger(LoggingApplication.class);
    
    private final LoggingService loggingService;
    
    public LoggingApplication(LoggingService loggingService) {
        this.loggingService = loggingService;
    }

    public static void main(String[] args) {
        logger.info("애플리케이션 시작 중...");
        SpringApplication.run(LoggingApplication.class, args);
        logger.info("애플리케이션 시작 완료!");
    }
    
    @GetMapping("/")
    public String home() {
        logger.info("홈 페이지 요청 처리");
        return "로깅 예제 애플리케이션에 오신 것을 환영합니다! 다음 엔드포인트를 사용해보세요: /log?level=INFO";
    }
    
    @GetMapping("/log")
    public String logMessage(@RequestParam(defaultValue = "INFO") String level) {
        logger.info("로그 메시지 요청 처리: level={}", level);
        
        switch (level.toUpperCase()) {
            case "TRACE":
                loggingService.logTrace();
                return "TRACE 레벨 로그 메시지를 생성했습니다.";
            case "DEBUG":
                loggingService.logDebug();
                return "DEBUG 레벨 로그 메시지를 생성했습니다.";
            case "INFO":
                loggingService.logInfo();
                return "INFO 레벨 로그 메시지를 생성했습니다.";
            case "WARN":
                loggingService.logWarn();
                return "WARN 레벨 로그 메시지를 생성했습니다.";
            case "ERROR":
                loggingService.logError();
                return "ERROR 레벨 로그 메시지를 생성했습니다.";
            default:
                logger.warn("지원하지 않는 로그 레벨: {}", level);
                return "지원하지 않는 로그 레벨입니다. (TRACE, DEBUG, INFO, WARN, ERROR 중 하나를 사용하세요)";
        }
    }
} 