package com.example.devtools.actuator;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

import java.util.Random;

/**
 * 커스텀 헬스 인디케이터 예제
 */
@Component
@Slf4j
public class HealthCheckController implements HealthIndicator {

    private boolean serviceUp = true;
    private final Random random = new Random();
    
    @Override
    public Health health() {
        if (serviceUp) {
            return Health.up()
                    .withDetail("message", "애플리케이션이 정상 작동 중입니다")
                    .withDetail("timestamp", System.currentTimeMillis())
                    .build();
        } else {
            return Health.down()
                    .withDetail("message", "애플리케이션에 문제가 발생했습니다")
                    .withDetail("timestamp", System.currentTimeMillis())
                    .build();
        }
    }
    
    @RestController
    @RequestMapping("/admin")
    @Slf4j
    public static class HealthSimulationController {
        
        private final HealthCheckController healthCheckController;
        
        public HealthSimulationController(HealthCheckController healthCheckController) {
            this.healthCheckController = healthCheckController;
        }
        
        @GetMapping("/health/up")
        public String simulateHealthUp() {
            log.info("시스템 상태를 UP으로 변경합니다");
            healthCheckController.serviceUp = true;
            return "시스템 상태가 UP으로 설정되었습니다. /actuator/health에서 확인하세요.";
        }
        
        @GetMapping("/health/down")
        public String simulateHealthDown() {
            log.info("시스템 상태를 DOWN으로 변경합니다");
            healthCheckController.serviceUp = false;
            return "시스템 상태가 DOWN으로 설정되었습니다. /actuator/health에서 확인하세요.";
        }
    }
    
    /**
     * 랜덤하게 헬스 상태를 변경하는 스케줄러 (데모용)
     * 실제 프로덕션에서는 사용하지 않음
     */
    @Component
    @EnableScheduling
    @Slf4j
    public class HealthSimulator {
        
        // 1분마다 약 10% 확률로 상태 변경
        @Scheduled(fixedRate = 60000)
        public void simulateHealthChange() {
            if (random.nextInt(10) == 0) {
                serviceUp = !serviceUp;
                log.info("건강 상태가 자동으로 {}(으)로 변경되었습니다", serviceUp ? "UP" : "DOWN");
            }
        }
    }
} 