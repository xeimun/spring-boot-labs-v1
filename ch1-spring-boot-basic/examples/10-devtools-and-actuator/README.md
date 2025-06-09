# 스프링 부트 DevTools와 Actuator

## Spring Boot DevTools

Spring Boot DevTools는 개발 시 생산성을 향상시키기 위한 도구 모음으로, 다음과 같은 기능을 제공합니다:

### 1. 자동 재시작(Automatic Restart)

애플리케이션 클래스패스의 파일이 변경되면 자동으로 애플리케이션을 재시작합니다. 이는 전체 재시작보다 훨씬 빠르게 작동합니다.

```gradle
dependencies {
    developmentOnly 'org.springframework.boot:spring-boot-devtools'
}
```

#### 작동 방식
- 두 개의 클래스로더를 사용합니다:
  - 변경되지 않는 클래스(라이브러리)는 기본 클래스로더에 로드
  - 개발 중인 클래스는 재시작 클래스로더에 로드
- 코드가 변경되면 재시작 클래스로더만 다시 로드하므로 빠릅니다

#### 자동 재시작 제외하기

```yaml
spring:
  devtools:
    restart:
      exclude: static/**,public/**
```

### 2. 라이브 리로드(LiveReload)

DevTools는 내장된 LiveReload 서버를 포함하고 있어, 정적 자원(HTML, CSS, JavaScript)이 변경되면 브라우저를 자동으로 새로고침합니다.

### 3. 속성 기본값 재정의

DevTools는 개발 환경에 더 적합한 속성 기본값을 제공합니다:
- 캐싱 옵션 비활성화 (템플릿 엔진, 정적 자원 등)
- 로깅 수준 향상

### 4. 전역 설정

`~/.spring-boot-devtools.properties` 파일을 사용하여 모든 프로젝트에 적용될 DevTools 설정을 구성할 수 있습니다.

## Spring Boot Actuator

Spring Boot Actuator는 실행 중인 애플리케이션을 모니터링하고 관리하기 위한 기능을 제공합니다.

### 1. 주요 기능

- **엔드포인트**: 애플리케이션 상태 정보 제공 및 관리 기능 노출
- **메트릭**: 애플리케이션 성능 지표 수집
- **감사**: 애플리케이션 내에서 발생한 감사 이벤트 추적
- **헬스 체크**: 애플리케이션 상태 확인

### 2. 설정 방법

```gradle
dependencies {
    implementation 'org.springframework.boot:spring-boot-starter-actuator'
}
```

### 3. 주요 엔드포인트

| 엔드포인트 | 설명 |
|----------|------|
| `/actuator/health` | 애플리케이션 상태 정보 |
| `/actuator/info` | 애플리케이션 정보 |
| `/actuator/metrics` | 메트릭 정보 |
| `/actuator/env` | 환경 변수 정보 |
| `/actuator/mappings` | 모든 @RequestMapping 경로 정보 |
| `/actuator/beans` | 애플리케이션 컨텍스트의 모든 빈 정보 |
| `/actuator/loggers` | 로깅 설정 확인 및 변경 |
| `/actuator/shutdown` | 애플리케이션 종료(기본적으로 비활성화) |

### 4. 보안

Actuator 엔드포인트는 민감한 정보를 노출할 수 있으므로 보안 설정이 중요합니다. 
```yaml
management:
  endpoints:
    web:
      exposure:
        include: health,info  # 노출할 엔드포인트 지정
      base-path: /management  # 기본 경로 변경
  endpoint:
    health:
      show-details: when_authorized  # 권한 있는 사용자에게만 상세 정보 표시
```

### 5. 커스텀 헬스 인디케이터

특정 서비스나 컴포넌트의 상태를 확인하는 커스텀 헬스 인디케이터를 구현할 수 있습니다.

```java
@Component
public class CustomHealthIndicator implements HealthIndicator {
    
    @Override
    public Health health() {
        // 상태 확인 로직
        boolean isHealthy = checkHealth();
        
        if (isHealthy) {
            return Health.up()
                    .withDetail("message", "서비스가 정상 작동 중입니다")
                    .build();
        } else {
            return Health.down()
                    .withDetail("message", "서비스에 문제가 발생했습니다")
                    .build();
        }
    }
}
```

### 6. 프로메테우스 통합

Actuator는 Prometheus와 통합하여 메트릭을 수집하고 모니터링할 수 있습니다.

```gradle
dependencies {
    implementation 'io.micrometer:micrometer-registry-prometheus'
}
```

이후 `/actuator/prometheus` 엔드포인트를 통해 Prometheus 형식의 메트릭을 확인할 수 있습니다. 