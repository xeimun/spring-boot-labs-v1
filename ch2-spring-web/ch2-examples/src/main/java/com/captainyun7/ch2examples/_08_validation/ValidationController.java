package com.captainyun7.ch2examples._08_validation;

import com.captainyun7.ch2examples._08_validation.dto.UserDto;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * 입력값 검증 컨트롤러
 * - @Valid 어노테이션을 사용한 입력값 검증
 * - BindingResult를 통한 검증 오류 처리
 */
@RestController
@RequestMapping("/validation")
public class ValidationController {

    /**
     * @Valid를 사용한 기본 검증
     * - 검증 실패 시 MethodArgumentNotValidException 발생 (400 Bad Request)
     */
    @PostMapping("/users")
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto) {
        // 검증 통과 시에만 여기로 진입
        return ResponseEntity.status(HttpStatus.CREATED).body(userDto);
    }
    
    /**
     * BindingResult를 사용한 검증 오류 처리
     * - 검증 실패 시 직접 오류 처리
     */
    @PostMapping("/users/custom-error")
    public ResponseEntity<?> createUserWithCustomError(@Valid @RequestBody UserDto userDto, BindingResult bindingResult) {
        // 검증 오류가 있는 경우
        if (bindingResult.hasErrors()) {
            Map<String, String> errors = new HashMap<>();
            
            // 각 필드 오류 메시지 수집
            for (FieldError error : bindingResult.getFieldErrors()) {
                errors.put(error.getField(), error.getDefaultMessage());
            }
            
            return ResponseEntity.badRequest().body(errors);
        }
        
        // 검증 통과
        return ResponseEntity.status(HttpStatus.CREATED).body(userDto);
    }
    
    /**
     * 수동 검증 예제
     */
    @PostMapping("/manual-validation")
    public ResponseEntity<?> manualValidation(@RequestBody UserDto userDto) {
        Map<String, String> errors = new HashMap<>();
        
        // 수동 검증 로직
        if (userDto.getName() == null || userDto.getName().isBlank()) {
            errors.put("name", "이름은 필수 입력값입니다.");
        } else if (userDto.getName().length() < 2 || userDto.getName().length() > 20) {
            errors.put("name", "이름은 2자 이상 20자 이하여야 합니다.");
        }
        
        if (userDto.getEmail() == null || userDto.getEmail().isBlank()) {
            errors.put("email", "이메일은 필수 입력값입니다.");
        } else if (!userDto.getEmail().matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
            errors.put("email", "이메일 형식이 올바르지 않습니다.");
        }
        
        // 오류가 있는 경우
        if (!errors.isEmpty()) {
            return ResponseEntity.badRequest().body(errors);
        }
        
        // 검증 통과
        return ResponseEntity.status(HttpStatus.CREATED).body(userDto);
    }
    
    /**
     * 경로 변수 검증 예제
     */
    @GetMapping("/users/{id}")
    public ResponseEntity<String> getUserById(@PathVariable Long id) {
        if (id <= 0) {
            return ResponseEntity.badRequest().body("ID는 양수여야 합니다.");
        }
        
        return ResponseEntity.ok("ID: " + id + "인 사용자를 조회했습니다.");
    }
    
    /**
     * 요청 파라미터 검증 예제
     */
    @GetMapping("/search")
    public ResponseEntity<String> searchUsers(
            @RequestParam(required = false) String keyword,
            @RequestParam(defaultValue = "1") int page) {
        
        if (keyword != null && keyword.length() < 2) {
            return ResponseEntity.badRequest().body("검색어는 2자 이상이어야 합니다.");
        }
        
        if (page <= 0) {
            return ResponseEntity.badRequest().body("페이지 번호는 양수여야 합니다.");
        }
        
        return ResponseEntity.ok("검색어: " + keyword + ", 페이지: " + page);
    }
} 