package com.captainyun7.ch2examples._05_dto.dto;

import com.captainyun7.ch2examples._05_dto.domain.Member;

import java.time.LocalDateTime;

/**
 * 회원 DTO (Data Transfer Object)
 * - API 응답으로 사용되는 데이터 전송 객체
 * - 도메인 엔티티와 API 계층을 분리하는 역할
 * - 필요한 데이터만 포함하고 민감 정보는 제외
 */
public class MemberDto {
    private Long id;
    private String email;
    private String name;
    private String phoneNumber;
    private LocalDateTime createdAt;
    
    // 기본 생성자
    public MemberDto() {
    }
    
    // 생성자
    public MemberDto(Long id, String email, String name, String phoneNumber, LocalDateTime createdAt) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.createdAt = createdAt;
    }
    
    // 도메인 엔티티를 DTO로 변환하는 정적 팩토리 메서드
    public static MemberDto from(Member member) {
        return new MemberDto(
                member.getId(),
                member.getEmail(),
                member.getName(),
                member.getPhoneNumber(),
                member.getCreatedAt()
        );
    }
    
    // Getter와 Setter
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getPhoneNumber() {
        return phoneNumber;
    }
    
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
} 