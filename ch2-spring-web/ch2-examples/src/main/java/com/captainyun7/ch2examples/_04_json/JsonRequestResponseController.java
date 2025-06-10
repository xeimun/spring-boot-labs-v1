package com.captainyun7.ch2examples._04_json;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * JSON 요청/응답 처리 예제
 * - @RequestBody: JSON 요청 본문을 자바 객체로 변환
 * - 객체 반환: 자바 객체를 JSON 응답으로 변환
 */
@RestController
@RequestMapping("/json")
public class JsonRequestResponseController {

    // 메모리에 데이터 저장 (실제로는 DB를 사용해야 함)
    private final List<Product> products = new ArrayList<>();
    
    /**
     * 초기 데이터 설정
     */
    public JsonRequestResponseController() {
        products.add(new Product(1L, "노트북", 1200000, LocalDateTime.now()));
        products.add(new Product(2L, "스마트폰", 800000, LocalDateTime.now()));
    }

    /**
     * 모든 상품 조회
     */
    @GetMapping("/products")
    public List<Product> getAllProducts() {
        return products;
    }

    /**
     * 특정 상품 조회
     */
    @GetMapping("/products/{id}")
    public Product getProduct(@PathVariable Long id) {
        return products.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst()
                .orElse(null);  // 실제로는 예외 처리 필요
    }

    /**
     * 상품 생성 - JSON 요청 본문을 Product 객체로 변환
     */
    @PostMapping("/products")
    public Product createProduct(@RequestBody Product product) {
        // 새 ID 할당 및 생성 시간 설정
        product.setId((long) (products.size() + 1));
        product.setCreatedAt(LocalDateTime.now());
        products.add(product);
        return product;
    }

    /**
     * 상품 수정 - JSON 요청 본문을 Product 객체로 변환
     */
    @PutMapping("/products/{id}")
    public Product updateProduct(@PathVariable Long id, @RequestBody Product updatedProduct) {
        for (int i = 0; i < products.size(); i++) {
            Product product = products.get(i);
            if (product.getId().equals(id)) {
                updatedProduct.setId(id);
                updatedProduct.setCreatedAt(product.getCreatedAt());
                products.set(i, updatedProduct);
                return updatedProduct;
            }
        }
        return null;  // 실제로는 예외 처리 필요
    }

    /**
     * 상품 삭제
     */
    @DeleteMapping("/products/{id}")
    public boolean deleteProduct(@PathVariable Long id) {
        return products.removeIf(p -> p.getId().equals(id));
    }

    /**
     * Map을 사용한 JSON 요청 처리
     * - 유연한 JSON 구조 처리 가능
     */
    @PostMapping("/flexible")
    public Map<String, Object> handleFlexibleJson(@RequestBody Map<String, Object> payload) {
        // 요청 데이터 처리 후 응답
        payload.put("receivedAt", LocalDateTime.now().toString());
        payload.put("processed", true);
        return payload;
    }
    
    /**
     * 특정 미디어 타입 지정 예제
     * - produces: 응답 컨텐츠 타입 지정
     * - consumes: 요청 컨텐츠 타입 지정
     */
    @PostMapping(
            path = "/specific-format",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Product handleSpecificFormat(@RequestBody Product product) {
        product.setId(999L);
        product.setCreatedAt(LocalDateTime.now());
        return product;
    }
} 