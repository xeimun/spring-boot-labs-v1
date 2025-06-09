# 스프링 부트 로깅

## 스프링 부트의 로깅 시스템

스프링 부트는 기본적으로 [Commons Logging](https://commons.apache.org/proper/commons-logging/)을 통해 로깅 추상화를 제공하며, 기본 구현체로 [Logback](https://logback.qos.ch/)을 사용합니다. 이를 통해 로그 시스템 설정 없이도 로깅을 바로 사용할 수 있습니다.

스프링 부트의 스타터는 Logback을 기본 로깅 구현체로 사용하지만, 필요에 따라 Log4j2나 Java Util Logging과 같은 다른 로깅 구현체로 변경할 수도 있습니다.

## 로그 레벨

로깅 시스템은 다양한 로그 레벨을 제공하여 로그의 중요도를 구분합니다:

| 레벨 | 설명 |
|------|------|
| TRACE | 가장 상세한 정보를 제공하는 레벨 (디버깅 용도) |
| DEBUG | 개발 중에 디버깅을 위한 자세한 정보 |
| INFO | 일반적인 정보 메시지, 애플리케이션의 상태 변화 등 |
| WARN | 잠재적인 문제를 경고하지만 애플리케이션은 계속 실행 가능 |
| ERROR | 오류가 발생했지만 애플리케이션은 계속 실행 가능 |
| FATAL | 심각한 오류로 애플리케이션 종료를 초래할 수 있음 |

기본적으로 스프링 부트는 INFO 레벨 이상의 로그만 출력합니다.

## 로그 설정 방법

### application.properties/yml을 통한 설정

```yaml
# 루트 로거의 레벨 설정
logging.level.root=WARN

# 특정 패키지의 로그 레벨 설정
logging.level.org.springframework.web=DEBUG
logging.level.com.example.myapp=INFO

# 로그 파일 설정
logging.file.name=myapp.log
logging.file.path=/var/log

# 로그 패턴 설정
logging.pattern.console=%d{yyyy-MM-dd HH:mm:ss} - %msg%n
logging.pattern.file=%d{yyyy-MM-dd HH:mm:ss} [%thread] %-5level %logger{36} - %msg%n
```

### logback.xml 또는 logback-spring.xml 설정

더 복잡한 로그 설정이 필요한 경우, `logback.xml` 또는 `logback-spring.xml` 파일을 사용할 수 있습니다. `logback-spring.xml`을 사용하면 스프링 부트의 추가 기능(프로파일별 설정 등)을 활용할 수 있습니다.

```xml
<?xml version="1.0" encoding="UTF-8"?>
<configuration>
    <!-- 콘솔 출력 설정 -->
    <appender name="CONSOLE" class="ch.qos.logback.core.ConsoleAppender">
        <encoder>
            <pattern>%d{HH:mm:ss.SSS} [%thread] %-5level %logger{36} - %msg%n</pattern>
        </encoder>
    </appender>
    
    <!-- 파일 출력 설정 -->
    <appender name="FILE" class="ch.qos.logback.core.rolling.RollingFileAppender">
        <file>logs/myapp.log</file>
        <rollingPolicy class="ch.qos.logback.core.rolling.TimeBasedRollingPolicy">
            <fileNamePattern>logs/myapp-%d{yyyy-MM-dd}.log</fileNamePattern>
            <maxHistory>30</maxHistory>
        </rollingPolicy>
        <encoder>
            <pattern>%d{yyyy-MM-dd HH:mm:ss.SSS} [%thread] %-5level %logger{36} - %msg%n</pattern>
        </encoder>
    </appender>
    
    <!-- 패키지별 로그 레벨 설정 -->
    <logger name="org.springframework" level="INFO" />
    <logger name="com.example.myapp" level="DEBUG" />
    
    <!-- 루트 로거 설정 -->
    <root level="WARN">
        <appender-ref ref="CONSOLE" />
        <appender-ref ref="FILE" />
    </root>
</configuration>
```

### 프로파일별 로그 설정

`logback-spring.xml`을 사용하면 스프링 프로파일에 따라 다른 로그 설정을 적용할 수 있습니다:

```xml
<springProfile name="dev">
    <logger name="com.example.myapp" level="DEBUG" />
</springProfile>

<springProfile name="prod">
    <logger name="com.example.myapp" level="INFO" />
</springProfile>
```

## 자바 코드에서 로그 사용하기

### SLF4J 직접 사용

```java
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyService {
    private static final Logger logger = LoggerFactory.getLogger(MyService.class);
    
    public void doSomething() {
        logger.trace("TRACE 로그 메시지");
        logger.debug("DEBUG 로그 메시지");
        logger.info("INFO 로그 메시지");
        logger.warn("WARN 로그 메시지");
        logger.error("ERROR 로그 메시지");
    }
}
```

### Lombok의 @Slf4j 사용

```java
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MyService {
    
    public void doSomething() {
        log.trace("TRACE 로그 메시지");
        log.debug("DEBUG 로그 메시지");
        log.info("INFO 로그 메시지");
        log.warn("WARN 로그 메시지");
        log.error("ERROR 로그 메시지");
    }
}
```

### 매개변수화된 로그 메시지

로그 메시지에 변수를 포함시킬 때는 문자열 연결 대신 SLF4J의 매개변수화된 메시지를 사용하는 것이 성능상 좋습니다:

```java
// 권장하지 않음 (문자열 연결)
log.debug("User " + username + " accessed " + resource);

// 권장함 (매개변수화된 메시지)
log.debug("User {} accessed {}", username, resource);
```

## MDC(Mapped Diagnostic Context) 활용

MDC는 로그 메시지에 추가 컨텍스트 정보를 포함시키는 데 유용합니다. 특히 요청 ID나 사용자 ID와 같은 정보를 로그에 일관되게 포함시키고 싶을 때 사용합니다:

```java
import org.slf4j.MDC;

// MDC에 값 설정
MDC.put("requestId", UUID.randomUUID().toString());
MDC.put("userId", user.getId());

// 로그 출력 (MDC 값이 자동으로 포함됨)
log.info("사용자 요청 처리 중");

// 작업 완료 후 MDC 정리
MDC.clear();
```

로그 패턴에 MDC 값을 포함시키려면:

```xml
<pattern>%d{HH:mm:ss.SSS} [%thread] [requestId=%X{requestId}] %-5level %logger{36} - %msg%n</pattern>
``` 