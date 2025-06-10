package com.captainyun7.ch2examples._08_validation;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

/**
 * 검증 예외 처리기
 * - 검증 실패 시 발생하는 예외를 일관되게 처리
 * - 클라이언트에게 적절한 오류 메시지 제공
 */
@RestControllerAdvice
public class ValidationExceptionHandler {

    /**
     * @Valid 검증 실패 시 발생하는 예외 처리
     * - 각 필드별 오류 메시지 반환
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, Object> response = new HashMap<>();
        Map<String, String> errors = new HashMap<>();
        
        // 각 필드 오류 메시지 수집
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            errors.put(error.getField(), error.getDefaultMessage());
        }
        
        response.put("status", HttpStatus.BAD_REQUEST.value());
        response.put("message", "입력값 검증에 실패했습니다.");
        response.put("errors", errors);
        
        return ResponseEntity.badRequest().body(response);
    }
    
    /**
     * 타입 변환 실패 예외 처리
     * - 요청 파라미터나 경로 변수의 타입 변환 실패 시
     */
    @ExceptionHandler(NumberFormatException.class)
    public ResponseEntity<Map<String, Object>> handleNumberFormatException(NumberFormatException ex) {
        Map<String, Object> response = new HashMap<>();
        
        response.put("status", HttpStatus.BAD_REQUEST.value());
        response.put("message", "숫자 형식이 올바르지 않습니다.");
        response.put("error", ex.getMessage());
        
        return ResponseEntity.badRequest().body(response);
    }
    
    /**
     * 커스텀 검증 예외 처리
     */
    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<Map<String, Object>> handleValidationException(ValidationException ex) {
        Map<String, Object> response = new HashMap<>();
        
        response.put("status", HttpStatus.BAD_REQUEST.value());
        response.put("message", ex.getMessage());
        
        return ResponseEntity.badRequest().body(response);
    }
    
    /**
     * 커스텀 검증 예외 클래스
     */
    public static class ValidationException extends RuntimeException {
        public ValidationException(String message) {
            super(message);
        }
    }
} 