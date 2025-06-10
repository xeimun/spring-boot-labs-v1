# 스프링 부트 웹 계층 예제

이 프로젝트는 스프링 부트 웹 계층의 주요 개념을 학습하기 위한 예제 코드를 포함하고 있습니다.

## 주요 학습 내용

### 1. 컨트롤러와 매핑 기본
- `@Controller`와 `@RestController` 차이
- 다양한 HTTP 메서드 매핑 (`@GetMapping`, `@PostMapping`, `@PutMapping`, `@DeleteMapping`)
- 경로 변수(`@PathVariable`)와 요청 파라미터(`@RequestParam`) 사용

### 2. JSON 요청/응답 처리
- `@RequestBody`를 사용한 JSON 요청 처리
- 객체를 JSON으로 변환하여 응답
- Jackson 라이브러리 어노테이션 활용 (`@JsonProperty`, `@JsonFormat`, `@JsonIgnore`)

### 3. DTO 설계와 계층 분리
- 도메인 엔티티와 API 계층 분리
- 요청/응답 DTO 설계
- 계층 간 데이터 변환 (Entity ↔ DTO)

### 4. HTTP 상태코드/응답 처리
- `ResponseEntity`를 활용한 응답 제어
- 다양한 HTTP 상태 코드 활용
- 일관된 API 응답 형식 제공
- 글로벌 예외 처리

### 5. RESTful URI 설계
- 리소스 중심 URI 설계
- HTTP 메서드를 통한 행위 표현
- 계층 구조를 반영한 URI 설계
- 쿼리 파라미터를 통한 필터링, 정렬, 페이징

### 6. 입력값 검증 및 예외 처리
- Bean Validation을 활용한 입력값 검증
- `@Valid`와 `BindingResult`를 통한 검증
- 검증 실패 시 예외 처리
- 커스텀 검증 로직 구현

## 패키지 구조

- `_01_basic_controller`: 기본 컨트롤러와 매핑 예제
- `_02_json`: JSON 요청/응답 처리 예제
- `_03_dto`: DTO 설계와 계층 분리 예제
- `_04_http_status`: HTTP 상태코드와 응답 처리 예제
- `_05_restful_uri`: RESTful URI 설계 예제
- `_06_validation`: 입력값 검증 및 예외 처리 예제

## 실행 방법

1. 프로젝트 클론
   ```
   git clone <repository-url>
   ```

2. 프로젝트 빌드
   ```
   ./gradlew build
   ```

3. 애플리케이션 실행
   ```
   ./gradlew bootRun
   ```

4. 브라우저나 API 테스트 도구로 접속
   ```
   http://localhost:8080
   ```

## API 테스트 예시

### 기본 컨트롤러 테스트
```
GET http://localhost:8080/basic/hello
GET http://localhost:8080/basic/users/123
GET http://localhost:8080/basic/params?name=홍길동&age=20
```

### JSON 요청/응답 테스트
```
GET http://localhost:8080/json/products
POST http://localhost:8080/json/products
{
    "name": "새 상품",
    "price": 50000
}
```

### 회원 API 테스트
```
GET http://localhost:8080/api/members
POST http://localhost:8080/api/members
{
    "email": "user@example.com",
    "password": "password123",
    "name": "홍길동",
    "phoneNumber": "010-1234-5678",
    "address": "서울시 강남구"
}
```

### HTTP 상태코드 테스트
```
GET http://localhost:8080/http/ok
POST http://localhost:8080/http/created
GET http://localhost:8080/http/items/1
```

### RESTful URI 테스트
```
GET http://localhost:8080/api/books?category=programming&sort=title&page=1&size=10
GET http://localhost:8080/api/books/1
GET http://localhost:8080/api/books/1/reviews
```

### 입력값 검증 테스트
```
POST http://localhost:8080/validation/users
{
    "id": 1,
    "name": "홍길동",
    "email": "user@example.com",
    "password": "Password1!",
    "age": 25,
    "phoneNumber": "010-1234-5678"
}
``` 