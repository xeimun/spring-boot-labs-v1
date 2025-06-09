# 스프링 부트 의존성 관리

## 스프링 부트 스타터(Spring Boot Starter)

스프링 부트 스타터는 특정 기능을 사용하기 위해 필요한 의존성을 모아둔 편리한 패키지입니다. 이를 통해 개발자는 복잡한 의존성 관리를 하지 않고도 필요한 기능을 쉽게 추가할 수 있습니다.

### 주요 스타터 목록

| 스타터 | 설명 |
|--------|------|
| spring-boot-starter-web | 웹 애플리케이션 개발을 위한 스타터 (Spring MVC, REST, Tomcat) |
| spring-boot-starter-data-jpa | JPA를 사용한 데이터 접근을 위한 스타터 |
| spring-boot-starter-security | 보안 기능을 위한 스타터 |
| spring-boot-starter-test | 테스트 기능을 위한 스타터 (JUnit, Mockito, AssertJ) |
| spring-boot-starter-thymeleaf | Thymeleaf 템플릿 엔진을 위한 스타터 |
| spring-boot-starter-actuator | 애플리케이션 모니터링을 위한 스타터 |

## Maven과 Gradle 비교

스프링 부트 프로젝트는 Maven 또는 Gradle을 사용하여 빌드할 수 있습니다. 두 도구의 주요 차이점은 다음과 같습니다:

### Maven

```xml
<dependencies>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>
</dependencies>
```

- XML 기반 구성
- 안정적이고 널리 사용됨
- 엄격한 라이프사이클 관리
- 플러그인 설정이 복잡할 수 있음

### Gradle

```gradle
dependencies {
    implementation 'org.springframework.boot:spring-boot-starter-web'
}
```

- Groovy 또는 Kotlin DSL 기반 구성
- 간결한 문법
- 유연한 빌드 스크립트
- 빌드 성능이 더 빠름
- 다중 프로젝트 빌드에 더 효율적

## 의존성 버전 관리 (BOM)

스프링 부트는 BOM(Bill of Materials)을 통해 의존성 버전을 자동으로 관리합니다. 이는 `spring-boot-dependencies`에 정의되어 있으며, 호환성 문제를 방지하고 일관된 버전 관리를 가능하게 합니다.

### Maven BOM 설정

```xml
<parent>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-parent</artifactId>
    <version>3.2.0</version>
</parent>
```

### Gradle BOM 설정

```gradle
plugins {
    id 'org.springframework.boot' version '3.2.0'
    id 'io.spring.dependency-management' version '1.1.4'
}
```

## 의존성 제외하기

특정 의존성을 제외해야 할 경우:

### Maven에서 제외

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
    <exclusions>
        <exclusion>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-tomcat</artifactId>
        </exclusion>
    </exclusions>
</dependency>
```

### Gradle에서 제외

```gradle
implementation('org.springframework.boot:spring-boot-starter-web') {
    exclude group: 'org.springframework.boot', module: 'spring-boot-starter-tomcat'
}
```

## 결론

스프링 부트의 의존성 관리 시스템은 개발자가 버전 충돌 걱정 없이 필요한 라이브러리를 쉽게 추가할 수 있도록 도와줍니다. 스타터와 BOM을 통해 호환성 문제를 최소화하고 빠른 개발을 가능하게 합니다. 