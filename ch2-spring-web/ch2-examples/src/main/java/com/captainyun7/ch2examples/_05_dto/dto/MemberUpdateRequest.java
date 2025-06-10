package com.captainyun7.ch2examples._05_dto.dto;

import com.captainyun7.ch2examples._05_dto.domain.Member;

import java.time.LocalDateTime;

/**
 * 회원 수정 요청 DTO
 * - API 요청에서 회원 수정에 필요한 데이터만 포함
 * - 수정 가능한 필드만 포함 (이메일 등 식별자는 제외)
 */
public class MemberUpdateRequest {
    private String name;
    private String phoneNumber;
    private String address;
    
    // 기본 생성자
    public MemberUpdateRequest() {
    }
    
    // 생성자
    public MemberUpdateRequest(String name, String phoneNumber, String address) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }
    
    // 기존 엔티티에 업데이트 내용을 적용하는 메서드
    public void applyTo(Member member) {
        member.setName(this.name);
        member.setPhoneNumber(this.phoneNumber);
        member.setAddress(this.address);
        member.setUpdatedAt(LocalDateTime.now());
    }
    
    // Getter와 Setter
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