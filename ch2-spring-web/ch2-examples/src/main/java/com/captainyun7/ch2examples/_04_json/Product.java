package com.captainyun7.ch2examples._04_json;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

/**
 * 상품 모델 클래스
 * - JSON 변환을 위한 Jackson 어노테이션 사용 예제
 */
public class Product {
    
    private Long id;
    
    private String name;
    
    // JSON 필드명을 "price"가 아닌 "product_price"로 변경
    @JsonProperty("product_price")
    private int price;
    
    // 날짜/시간 포맷 지정
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdAt;
    
    // JSON 직렬화에서 제외
    @JsonIgnore
    private String internalNote;
    
    // 기본 생성자 (Jackson이 역직렬화 시 필요)
    public Product() {
    }
    
    public Product(Long id, String name, int price, LocalDateTime createdAt) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.createdAt = createdAt;
    }
    
    // Getter와 Setter
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public int getPrice() {
        return price;
    }
    
    public void setPrice(int price) {
        this.price = price;
    }
    
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
    
    public String getInternalNote() {
        return internalNote;
    }
    
    public void setInternalNote(String internalNote) {
        this.internalNote = internalNote;
    }
} 