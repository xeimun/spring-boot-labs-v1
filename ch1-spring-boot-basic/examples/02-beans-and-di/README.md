# Spring Initializr 사용법 및 프로젝트 구조

## Spring Initializr란?
Spring Initializr는 스프링 부트 프로젝트를 빠르게 생성할 수 있는 웹 기반 도구입니다. 이를 통해 필요한 의존성과 메타데이터를 선택하여 프로젝트 구조를 자동으로 생성할 수 있습니다.

## Spring Initializr 사용 방법

### 1. 웹 인터페이스 사용 (https://start.spring.io)
1. 웹 브라우저에서 https://start.spring.io 접속
2. 프로젝트 설정:
   - **Project**: Maven 또는 Gradle 선택
   - **Language**: Java, Kotlin, Groovy 중 선택
   - **Spring Boot 버전**: 원하는 버전 선택
   - **Project Metadata**: 그룹 ID, 아티팩트 ID, 패키징 방식 등 설정
   - **Dependencies**: 필요한 의존성 추가 (Web, JPA, Security 등)
3. "GENERATE" 버튼 클릭하여 프로젝트 다운로드

### 2. IDE에서 사용
- **IntelliJ IDEA**: File → New → Project → Spring Initializr
- **Eclipse**: File → New → Spring Starter Project
- **VS Code**: Spring Initializr 확장 프로그램 사용

### 3. Spring Boot CLI 사용
```bash
spring init --name=myproject --dependencies=web,data-jpa myproject
```

## 생성된 프로젝트 구조

```
my-spring-boot-app/
├── .mvn/wrapper/                    # Maven 래퍼 파일
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/example/demo/    # 소스 코드
│   │   │       └── DemoApplication.java  # 메인 애플리케이션 클래스
│   │   └── resources/
│   │       ├── application.properties    # 애플리케이션 설정 파일
│   │       ├── static/                   # 정적 리소스 (CSS, JS, 이미지)
│   │       └── templates/                # 템플릿 파일 (Thymeleaf 등)
│   └── test/
│       └── java/
│           └── com/example/demo/    # 테스트 코드
│               └── DemoApplicationTests.java
├── .gitignore                        # Git 무시 파일 목록
├── mvnw                              # Maven 래퍼 스크립트 (Unix)
├── mvnw.cmd                          # Maven 래퍼 스크립트 (Windows)
└── pom.xml                           # Maven 프로젝트 설정 파일
```

## 주요 파일 및 디렉토리 설명

1. **메인 애플리케이션 클래스** (`DemoApplication.java`):
   - `@SpringBootApplication` 어노테이션이 붙은 진입점
   - 애플리케이션을 실행하는 `main` 메소드 포함

2. **설정 파일** (`application.properties` 또는 `application.yml`):
   - 애플리케이션 설정 관리 (포트, 데이터베이스 연결 등)

3. **리소스 디렉토리**:
   - `static`: 정적 웹 리소스 (CSS, JavaScript, 이미지)
   - `templates`: 템플릿 엔진 파일 (Thymeleaf, FreeMarker 등)

4. **테스트 클래스** (`DemoApplicationTests.java`):
   - 기본 통합 테스트 클래스

5. **빌드 설정 파일** (`pom.xml` 또는 `build.gradle`):
   - 의존성 관리
   - 빌드 구성 