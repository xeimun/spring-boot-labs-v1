package com.captainyun7.ch2examples._05_dto.controller;

import com.captainyun7.ch2examples._05_dto.domain.Member;
import com.captainyun7.ch2examples._05_dto.dto.MemberCreateRequest;
import com.captainyun7.ch2examples._05_dto.dto.MemberDto;
import com.captainyun7.ch2examples._05_dto.dto.MemberUpdateRequest;
import com.captainyun7.ch2examples._05_dto.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 회원 컨트롤러
 * - DTO 패턴을 활용한 계층 분리 예제
 * - 컨트롤러에서는 DTO만 사용하고, 서비스 계층에서는 도메인 엔티티 사용
 */
@RestController
@RequestMapping("/api/members")
public class MemberController {
    
    private final MemberService memberService;
    
    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }
    
    /**
     * 모든 회원 조회
     * - 도메인 엔티티를 DTO로 변환하여 반환
     */
    @GetMapping
    public List<MemberDto> getAllMembers() {
        return memberService.findAll().stream()
                .map(MemberDto::from)
                .collect(Collectors.toList());
    }
    
    /**
     * 특정 회원 조회
     * - 도메인 엔티티를 DTO로 변환하여 반환
     * - 존재하지 않는 경우 404 응답
     */
    @GetMapping("/{id}")
    public ResponseEntity<MemberDto> getMember(@PathVariable Long id) {
        return memberService.findById(id)
                .map(MemberDto::from)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    /**
     * 회원 생성
     * - 요청 DTO를 도메인 엔티티로 변환하여 서비스에 전달
     * - 생성된 엔티티를 다시 응답 DTO로 변환하여 반환
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MemberDto createMember(@RequestBody MemberCreateRequest request) {
        Member member = request.toEntity();
        Member createdMember = memberService.create(member);
        return MemberDto.from(createdMember);
    }
    
    /**
     * 회원 수정
     * - 요청 DTO를 사용하여 도메인 엔티티 업데이트
     * - 수정된 엔티티를 응답 DTO로 변환하여 반환
     * - 존재하지 않는 경우 404 응답
     */
    @PutMapping("/{id}")
    public ResponseEntity<MemberDto> updateMember(@PathVariable Long id, @RequestBody MemberUpdateRequest request) {
        return memberService.findById(id)
                .map(member -> {
                    request.applyTo(member);
                    return memberService.update(id, member)
                            .map(MemberDto::from)
                            .map(ResponseEntity::ok)
                            .orElse(ResponseEntity.notFound().build());
                })
                .orElse(ResponseEntity.notFound().build());
    }
    
    /**
     * 회원 삭제
     * - 성공 시 204 응답
     * - 존재하지 않는 경우 404 응답
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMember(@PathVariable Long id) {
        if (memberService.delete(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
} 