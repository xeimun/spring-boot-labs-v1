# 스프링 빈 생명주기

## 스프링 빈 생명주기란?

스프링 빈은 생성부터 소멸까지 여러 단계의 생명주기를 거칩니다. 각 단계에서 개발자는 특정 작업을 수행하도록 콜백 메서드를 정의할 수 있습니다.

## 빈 생명주기 단계

1. **빈 인스턴스 생성**: 스프링 컨테이너가 빈 인스턴스를 생성합니다.
2. **의존성 주입**: 빈의 의존성이 주입됩니다.
3. **초기화 콜백**: 빈이 사용 준비가 완료되었을 때 호출됩니다.
4. **사용**: 애플리케이션에서 빈을 사용합니다.
5. **소멸 콜백**: 컨테이너가 종료될 때 호출됩니다.
6. **소멸**: 빈이 소멸됩니다.

## 초기화 및 소멸 콜백 방법

### 1. @PostConstruct와 @PreDestroy 어노테이션 (권장)

```java
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Component
public class MyBean {
    
    @PostConstruct
    public void init() {
        // 초기화 로직
    }
    
    @PreDestroy
    public void cleanup() {
        // 정리 로직
    }
}
```

- **장점**: 간결하고 명확한 방식, 특정 인터페이스에 의존하지 않음
- **참고**: Java 9부터 `javax.annotation` 패키지가 Java EE에서 분리되어 별도 의존성 필요

### 2. InitializingBean과 DisposableBean 인터페이스

```java
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.DisposableBean;

@Component
public class MyBean implements InitializingBean, DisposableBean {
    
    @Override
    public void afterPropertiesSet() throws Exception {
        // 초기화 로직
    }
    
    @Override
    public void destroy() throws Exception {
        // 정리 로직
    }
}
```

- **단점**: 스프링 프레임워크에 코드가 강하게 결합됨

### 3. 커스텀 초기화 및 소멸 메서드

```java
@Configuration
public class AppConfig {
    
    @Bean(initMethod = "init", destroyMethod = "cleanup")
    public MyBean myBean() {
        return new MyBean();
    }
}

public class MyBean {
    
    public void init() {
        // 초기화 로직
    }
    
    public void cleanup() {
        // 정리 로직
    }
}
```

- **장점**: 외부 라이브러리 클래스에도 적용 가능
- **참고**: `destroyMethod`의 기본값은 `(inferred)`로, `close`나 `shutdown` 메서드를 자동 감지

## 실행 순서

여러 초기화 방법을 함께 사용할 경우 다음 순서로 실행됩니다:

1. `@PostConstruct` 어노테이션이 적용된 메서드
2. `InitializingBean`의 `afterPropertiesSet()` 메서드
3. `@Bean(initMethod="...")` 에 지정된 커스텀 초기화 메서드

소멸 메서드도 비슷한 순서로 실행됩니다:

1. `@PreDestroy` 어노테이션이 적용된 메서드
2. `DisposableBean`의 `destroy()` 메서드
3. `@Bean(destroyMethod="...")` 에 지정된 커스텀 소멸 메서드

## 빈 생명주기 이벤트 리스너

스프링은 빈 생명주기 이벤트를 더 세밀하게 제어할 수 있는 `ApplicationListener` 인터페이스를 제공합니다:

```java
@Component
public class MyEventListener implements ApplicationListener<ContextRefreshedEvent> {
    
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        // 컨텍스트가 초기화되거나 리프레시될 때 실행
    }
}
```

주요 이벤트 타입:
- `ContextRefreshedEvent`: 컨텍스트 초기화 또는 리프레시 시
- `ContextStartedEvent`: `start()` 메서드로 컨텍스트 시작 시
- `ContextStoppedEvent`: `stop()` 메서드로 컨텍스트 정지 시
- `ContextClosedEvent`: `close()` 메서드로 컨텍스트 종료 시

## 싱글톤 vs 프로토타입 빈의 생명주기 차이

- **싱글톤 빈**: 컨테이너 생성 시 함께 생성되고, 컨테이너 종료 시 소멸 콜백 호출
- **프로토타입 빈**: 요청 시마다 새로 생성되고, 소멸 콜백이 자동으로 호출되지 않음 (스프링이 빈 참조를 관리하지 않음) 