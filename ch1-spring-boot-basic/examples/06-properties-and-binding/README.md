# 스프링 부트 속성 바인딩

## 속성 설정 파일

스프링 부트에서는 애플리케이션 설정을 위해 다양한 방법으로 속성을 관리할 수 있습니다. 주로 다음과 같은 파일들을 사용합니다:

### application.properties

```properties
# 서버 포트 설정
server.port=8080

# 데이터베이스 설정
spring.datasource.url=jdbc:mysql://localhost:3306/mydb
spring.datasource.username=user
spring.datasource.password=pass
```

### application.yml

```yaml
server:
  port: 8080
  
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/mydb
    username: user
    password: pass
```

## application.yml vs application.properties

| 특징 | application.properties | application.yml |
|------|------------------------|-----------------|
| 문법 | 키-값 형태의 평면적 구조 | 계층적 구조 |
| 가독성 | 단순하지만 복잡한 설정에는 부적합 | 계층 구조로 가독성이 높음 |
| 중복 제거 | 키의 중복이 많아질 수 있음 | 계층 구조로 중복 감소 |
| 지원 | 모든 스프링 환경에서 지원 | 대부분의 스프링 부트 환경에서 지원 |

## 속성 바인딩 방법

### 1. @Value 어노테이션

개별 속성을 주입할 때 사용합니다.

```java
@Component
public class MyComponent {
    @Value("${server.port}")
    private int serverPort;
    
    @Value("${app.name:기본값}")  // 기본값 지정
    private String appName;
}
```

### 2. @ConfigurationProperties 어노테이션

관련 있는 속성들을 객체로 그룹화할 때 사용합니다.

```java
@Component
@ConfigurationProperties(prefix = "app")
public class AppProperties {
    private String name;
    private String description;
    private Map<String, String> settings;
    
    // Getter와 Setter 메서드
}
```

```yaml
app:
  name: 내 애플리케이션
  description: 이것은 테스트 애플리케이션입니다
  settings:
    timeout: 30s
    retries: 5
```

## @Value vs @ConfigurationProperties

| 특징 | @Value | @ConfigurationProperties |
|------|--------|--------------------------|
| 타입 안전성 | 제한적 | 강력함 (타입 변환, 유효성 검사) |
| 메타데이터 지원 | 없음 | 있음 (properties 메타데이터) |
| SpEL 지원 | 있음 | 없음 |
| 복잡한 타입 바인딩 | 제한적 | 강력함 (Map, List, 객체 등) |
| 사용 용도 | 단일 속성 주입 | 관련 속성 그룹화 |

## 속성 값 타입 변환

스프링 부트는 다양한 타입으로 자동 변환을 지원합니다:

- 문자열 → 숫자 (Integer, Long 등)
- 문자열 → 불리언
- 문자열 → 열거형(Enum)
- 문자열 → 기간(Duration)
- 문자열 → 파일 크기(DataSize)

```yaml
app:
  timeout: 10s  # Duration으로 변환
  max-size: 10MB  # DataSize로 변환
  features: 
    - FEATURE_A  # 열거형으로 변환
    - FEATURE_B
```

## 유효성 검사

`@ConfigurationProperties`와 Bean Validation을 함께 사용하여 속성 값의 유효성을 검사할 수 있습니다.

```java
@Component
@ConfigurationProperties(prefix = "app")
@Validated
public class AppProperties {
    @NotEmpty
    private String name;
    
    @Min(1)
    @Max(10)
    private int threadCount;
    
    // Getter와 Setter 메서드
}
```

## 속성 소스 우선순위

스프링 부트는 다양한 위치에서 속성을 로드하며, 다음과 같은 우선순위를 가집니다 (높은 것부터):

1. 명령행 인수
2. JNDI 속성
3. Java 시스템 속성
4. OS 환경 변수
5. 특정 프로필의 설정 파일 (application-{profile}.properties/yml)
6. 기본 설정 파일 (application.properties/yml) 