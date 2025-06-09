# 스프링 부트 외부 설정

## 외부 설정이란?

외부 설정은 애플리케이션 코드 외부에서 설정을 관리하는 방법입니다. 이를 통해 환경별로 다른 설정을 쉽게 적용하고, 민감한 정보를 코드에서 분리할 수 있습니다.

## 외부 설정 소스 우선순위

스프링 부트는 다양한 외부 설정 소스를 지원하며, 다음과 같은 우선순위를 가집니다 (높은 것부터):

1. 명령행 인수
2. 자바 시스템 속성
3. 운영체제 환경 변수
4. 애플리케이션 외부의 application.properties/yml
5. 애플리케이션 내부의 application.properties/yml
6. @PropertySource로 추가된 속성 파일

## 외부 설정 방법

### 1. 명령행 인수

```bash
java -jar myapp.jar --server.port=8080 --spring.profiles.active=dev
```

### 2. 시스템 속성

```bash
java -Dserver.port=8080 -Dspring.profiles.active=dev -jar myapp.jar
```

### 3. 환경 변수

```bash
export SERVER_PORT=8080
export SPRING_PROFILES_ACTIVE=dev
java -jar myapp.jar
```

### 4. 외부 application.properties/yml

애플리케이션 JAR와 동일한 디렉터리에 application.properties 또는 application.yml 파일을 배치하거나, config 하위 디렉터리에 배치하여 사용할 수 있습니다.

```
/path/to/app/
  ├── myapp.jar
  └── application.properties
```

또는

```
/path/to/app/
  ├── myapp.jar
  └── config/
      └── application.properties
```

### 5. 속성 파일 직접 지정

```bash
java -jar myapp.jar --spring.config.location=file:///path/to/custom-config.yml
```

## 환경 변수 및 비밀 정보 관리

### 환경 변수 명명 규칙

스프링 부트는 다음과 같은 규칙으로 속성 이름을 환경 변수로 변환합니다:

- 점(`.`)을 언더스코어(`_`)로 변환
- 케밥 케이스를 대문자 스네이크 케이스로 변환

```
spring.datasource.url → SPRING_DATASOURCE_URL
app.feature-flags.enabled → APP_FEATURE_FLAGS_ENABLED
```

### 비밀 정보 관리 방법

#### 1. 환경 변수 사용

민감한 정보는 환경 변수를 통해 주입하는 것이 좋습니다:

```bash
export DB_PASSWORD=secretpassword
export API_KEY=abcd1234
```

#### 2. 외부 설정 서버 사용

Spring Cloud Config Server와 같은 설정 서버를 사용하여 중앙에서 설정을 관리할 수 있습니다.

#### 3. 보안 저장소 활용

Vault, AWS Secrets Manager, Azure Key Vault 등의 보안 저장소를 활용하여 비밀 정보를 안전하게 관리할 수 있습니다.

## 클라우드 환경에서의 외부 설정

### 1. 클라우드 플랫폼의 환경 변수

대부분의 클라우드 플랫폼(Heroku, AWS, Azure, GCP 등)은 환경 변수를 통해 애플리케이션 설정을 제공합니다.

### 2. 컨테이너 환경

Docker나 Kubernetes에서는 환경 변수나 ConfigMap/Secret을 통해 설정을 주입할 수 있습니다.

**Docker 예시:**
```bash
docker run -e SPRING_DATASOURCE_URL=jdbc:mysql://db:3306/mydb -e SPRING_DATASOURCE_USERNAME=user -e SPRING_DATASOURCE_PASSWORD=pass myapp
```

**Kubernetes ConfigMap 예시:**
```yaml
apiVersion: v1
kind: ConfigMap
metadata:
  name: myapp-config
data:
  application.yml: |
    spring:
      datasource:
        url: jdbc:mysql://db:3306/mydb
        username: user
```

**Kubernetes Secret 예시:**
```yaml
apiVersion: v1
kind: Secret
metadata:
  name: myapp-secrets
type: Opaque
data:
  spring.datasource.password: cGFzc3dvcmQ=  # base64 인코딩된 "password"
```

### 3. 클라우드 네이티브 설정 관리

Spring Cloud를 활용하여 클라우드 네이티브 환경에서 설정을 관리할 수 있습니다:

- **Spring Cloud Config**: 중앙 집중식 설정 서버
- **Spring Cloud Vault**: HashiCorp Vault 통합
- **Spring Cloud AWS**: AWS Parameter Store, Secrets Manager 통합
- **Spring Cloud Azure**: Azure Key Vault 통합 