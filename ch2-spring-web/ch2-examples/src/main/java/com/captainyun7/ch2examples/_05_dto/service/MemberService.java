package com.captainyun7.ch2examples._05_dto.service;

import com.captainyun7.ch2examples._05_dto.domain.Member;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

/**
 * 회원 서비스 클래스
 * - 비즈니스 로직 처리
 * - 데이터 접근 계층과 컨트롤러 계층 사이의 중간 계층
 */
@Service
public class MemberService {
    
    // 메모리에 데이터 저장 (실제로는 DB를 사용해야 함)
    private final Map<Long, Member> memberStore = new HashMap<>();
    private long sequence = 0L;
    
    // 생성자 - 초기 데이터 설정
    public MemberService() {
        // 샘플 데이터 추가
        Member member1 = new Member(
                ++sequence,
                "user1@example.com",
                "hashedPassword1",
                "홍길동",
                "010-1234-5678",
                "서울시 강남구",
                LocalDateTime.now(),
                LocalDateTime.now(),
                true
        );
        
        Member member2 = new Member(
                ++sequence,
                "user2@example.com",
                "hashedPassword2",
                "김철수",
                "010-9876-5432",
                "서울시 서초구",
                LocalDateTime.now(),
                LocalDateTime.now(),
                true
        );
        
        memberStore.put(member1.getId(), member1);
        memberStore.put(member2.getId(), member2);
    }
    
    /**
     * 모든 회원 조회
     */
    public List<Member> findAll() {
        return new ArrayList<>(memberStore.values());
    }
    
    /**
     * ID로 회원 조회
     */
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(memberStore.get(id));
    }
    
    /**
     * 이메일로 회원 조회
     */
    public Optional<Member> findByEmail(String email) {
        return memberStore.values().stream()
                .filter(member -> member.getEmail().equals(email))
                .findFirst();
    }
    
    /**
     * 회원 생성
     */
    public Member create(Member member) {
        // 이메일 중복 검사
        if (findByEmail(member.getEmail()).isPresent()) {
            throw new IllegalArgumentException("이미 존재하는 이메일입니다: " + member.getEmail());
        }
        
        // ID 할당
        member.setId(++sequence);
        memberStore.put(member.getId(), member);
        return member;
    }
    
    /**
     * 회원 수정
     */
    public Optional<Member> update(Long id, Member updatedMember) {
        if (memberStore.containsKey(id)) {
            Member member = memberStore.get(id);
            
            // 변경 불가능한 필드는 유지
            updatedMember.setId(id);
            updatedMember.setEmail(member.getEmail());
            updatedMember.setCreatedAt(member.getCreatedAt());
            
            memberStore.put(id, updatedMember);
            return Optional.of(updatedMember);
        }
        return Optional.empty();
    }
    
    /**
     * 회원 삭제
     */
    public boolean delete(Long id) {
        if (memberStore.containsKey(id)) {
            memberStore.remove(id);
            return true;
        }
        return false;
    }
} 