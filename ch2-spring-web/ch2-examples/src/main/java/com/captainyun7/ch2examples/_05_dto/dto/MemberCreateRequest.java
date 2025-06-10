package com.captainyun7.ch2examples._05_dto.dto;

import com.captainyun7.ch2examples._05_dto.domain.Member;

import java.time.LocalDateTime;

/**
 * 회원 생성 요청 DTO
 * - API 요청에서 회원 생성에 필요한 데이터만 포함
 * - 요청 데이터를 도메인 엔티티로 변환하는 메서드 제공
 */
public class MemberCreateRequest {
    private String email;
    private String password;
    private String name;
    private String phoneNumber;
    private String address;
    
    // 기본 생성자
    public MemberCreateRequest() {
    }
    
    // 생성자
    public MemberCreateRequest(String email, String password, String name, String phoneNumber, String address) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }
    
    // DTO를 도메인 엔티티로 변환하는 메서드
    public Member toEntity() {
        Member member = new Member();
        member.setEmail(this.email);
        member.setPassword(this.password);  // 실제로는 암호화 필요
        member.setName(this.name);
        member.setPhoneNumber(this.phoneNumber);
        member.setAddress(this.address);
        member.setCreatedAt(LocalDateTime.now());
        member.setUpdatedAt(LocalDateTime.now());
        member.setActive(true);
        return member;
    }
    
    // Getter와 Setter
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
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
    
    public String getAddress() {
        return address;
    }
    
    public void setAddress(String address) {
        this.address = address;
    }
} 