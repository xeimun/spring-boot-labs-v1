# 의존성 주입(DI)과 컴포넌트 스캔

## 의존성 주입(Dependency Injection)이란?

의존성 주입은 객체가 필요로 하는 의존 객체를 직접 생성하는 대신, 외부에서 주입받는 디자인 패턴입니다. 이를 통해 객체 간의 결합도를 낮추고 테스트 용이성을 높일 수 있습니다.

## 스프링의 컴포넌트 어노테이션

스프링은 여러 스테레오타입 어노테이션을 제공하여 클래스의 역할을 명확히 구분합니다:

1. **@Component**: 일반적인 스프링 관리 컴포넌트
2. **@Service**: 비즈니스 로직을 처리하는 서비스 레이어 컴포넌트
3. **@Repository**: 데이터 액세스 레이어 컴포넌트, 데이터 예외 변환 제공
4. **@Controller/@RestController**: 웹 요청을 처리하는 컨트롤러 컴포넌트

## 컴포넌트 스캔(Component Scan)

스프링 부트는 `@SpringBootApplication` 어노테이션이 있는 클래스가 위치한 패키지와 그 하위 패키지에서 자동으로 `@Component`가 붙은 클래스를 찾아 스프링 컨테이너에 빈으로 등록합니다.

```java
@SpringBootApplication
public class MyApplication {
    public static void main(String[] args) {
        SpringApplication.run(MyApplication.class, args);
    }
}
```

### 컴포넌트 스캔 범위 지정

컴포넌트 스캔 범위를 명시적으로 지정할 수도 있습니다:

```java
@ComponentScan(basePackages = "com.example.myapp")
@Configuration
public class AppConfig {
    // ...
}
```

## 의존성 주입 방식

스프링에서는 세 가지 의존성 주입 방식을 제공합니다:

### 1. 생성자 주입 (Constructor Injection)

```java
@Service
public class UserService {
    private final UserRepository userRepository;
    
    @Autowired  // 스프링 4.3부터는 단일 생성자의 경우 @Autowired 생략 가능
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
}
```

**장점**:
- 필수 의존성을 명확히 표현
- 불변성 보장 (final 필드 사용 가능)
- 순환 참조 감지 가능
- 테스트 용이성

**단점**:
- 의존성이 많을 경우 생성자가 복잡해짐

### 2. 세터 주입 (Setter Injection)

```java
@Service
public class UserService {
    private UserRepository userRepository;
    
    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
}
```

**장점**:
- 선택적 의존성에 유용
- 런타임에 의존성 변경 가능

**단점**:
- 필수 의존성 보장 불가
- final 필드 사용 불가

### 3. 필드 주입 (Field Injection)

```java
@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
}
```

**장점**:
- 코드가 간결함

**단점**:
- 테스트 어려움
- 불변성 보장 불가
- 숨겨진 의존성

## 권장 주입 방식

스프링 팀은 **생성자 주입**을 권장합니다:
- 필수 의존성을 명확히 표현
- 테스트 용이성
- 불변성 보장
- 순환 참조 방지

## 주입 예제 비교

### 생성자 주입 예제

```java
@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final PriceCalculator priceCalculator;
    
    public ProductService(ProductRepository productRepository, 
                           PriceCalculator priceCalculator) {
        this.productRepository = productRepository;
        this.priceCalculator = priceCalculator;
    }
}
```

### 세터 주입 예제

```java
@Service
public class ProductService {
    private ProductRepository productRepository;
    private PriceCalculator priceCalculator;
    
    @Autowired
    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
    
    @Autowired
    public void setPriceCalculator(PriceCalculator priceCalculator) {
        this.priceCalculator = priceCalculator;
    }
}
```

### 필드 주입 예제

```java
@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;
    
    @Autowired
    private PriceCalculator priceCalculator;
}
``` 