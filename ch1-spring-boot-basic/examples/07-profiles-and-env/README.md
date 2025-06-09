# 스프링 부트 프로파일과 환경 변수

## 프로파일이란?

프로파일은 다양한 환경(개발, 테스트, 운영 등)에 따라 서로 다른 구성을 적용할 수 있게 해주는 기능입니다. 스프링 부트에서는 프로파일을 통해 환경별로 서로 다른 빈, 설정, 속성을 활성화할 수 있습니다.

## 프로파일 설정 파일

프로파일별 설정 파일은 다음과 같은 명명 규칙을 따릅니다:

- `application-{프로파일명}.properties`
- `application-{프로파일명}.yml`

예를 들어, 개발 환경을 위한 설정은 `application-dev.yml`, 운영 환경을 위한 설정은 `application-prod.yml`과 같이 구성할 수 있습니다.

### application.yml

```yaml
spring:
  profiles:
    active: dev  # 기본 활성화 프로파일
```

### application-dev.yml

```yaml
server:
  port: 8080

spring:
  datasource:
    url: jdbc:h2:mem:devdb
    username: sa
    password: 
    
app:
  cache:
    enabled: false
  log-level: debug
```

### application-prod.yml

```yaml
server:
  port: 80

spring:
  datasource:
    url: jdbc:mysql://prod-db:3306/proddb
    username: prod_user
    password: prod_pass
    
app:
  cache:
    enabled: true
  log-level: info
```

## 프로파일 활성화 방법

### 1. application.yml/properties에서 설정

```yaml
spring:
  profiles:
    active: dev
```

### 2. 명령행 인수로 설정

```bash
java -jar app.jar --spring.profiles.active=dev
```

### 3. 환경 변수로 설정

```bash
export SPRING_PROFILES_ACTIVE=dev
java -jar app.jar
```

### 4. 코드에서 설정

```java
SpringApplication app = new SpringApplication(MyApp.class);
app.setAdditionalProfiles("dev");
app.run(args);
```

## @Profile 어노테이션

특정 프로파일이 활성화된 경우에만 빈을 등록하거나 구성을 활성화할 수 있습니다.

```java
@Configuration
@Profile("dev")  // dev 프로파일에서만 활성화
public class DevConfig {
    
    @Bean
    public DataSource devDataSource() {
        // 개발용 데이터 소스 구성
    }
}
```

```java
@Service
@Profile("!prod")  // prod가 아닌 프로파일에서 활성화
public class DevServiceImpl implements MyService {
    // 개발 환경용 구현
}

@Service
@Profile("prod")  // prod 프로파일에서만 활성화
public class ProdServiceImpl implements MyService {
    // 운영 환경용 구현
}
```

## 프로파일 표현식

AND, OR, NOT 연산자를 사용하여 복잡한 프로파일 조건을 표현할 수 있습니다.

```java
@Profile("dev & !legacy")  // dev 프로파일이고 legacy 프로파일이 아닌 경우
@Profile("dev | test")     // dev 또는 test 프로파일인 경우
@Profile("!prod")          // prod 프로파일이 아닌 경우
```

## 환경 변수 바인딩

스프링 부트는 환경 변수를 속성으로 자동 변환합니다. 이 때 명명 규칙은 다음과 같습니다:

1. 점(`.`)을 언더스코어(`_`)로 변환
2. 케밥 케이스를 대문자 스네이크 케이스로 변환

```
server.port → SERVER_PORT
spring.datasource.url → SPRING_DATASOURCE_URL
app.cache.enabled → APP_CACHE_ENABLED
```

따라서 다음과 같이 환경 변수를 설정할 수 있습니다:

```bash
export SERVER_PORT=8081
export SPRING_DATASOURCE_URL=jdbc:mysql://localhost:3306/mydb
export APP_CACHE_ENABLED=true
```

## 클라우드 환경에서의 환경 변수 활용

클라우드 플랫폼(AWS, Azure, GCP 등)에서는 환경 변수를 통한 설정이 일반적입니다. 스프링 부트는 이러한 환경에 최적화되어 있어 환경 변수를 통해 쉽게 구성을 변경할 수 있습니다.

이는 12 Factor App의 원칙 중 하나인 "설정을 환경 변수에 저장" 원칙과도 일치합니다. 