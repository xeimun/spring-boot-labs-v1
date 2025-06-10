package com.captainyun7.ch2examples._06_http_status;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;

/**
 * 글로벌 예외 처리기
 * - 애플리케이션 전체에서 발생하는 예외를 일관되게 처리
 * - 클라이언트에게 적절한 HTTP 상태 코드와 오류 메시지 제공
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 일반적인 예외 처리 (500 Internal Server Error)
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Void>> handleException(Exception ex, WebRequest request) {
        ApiResponse<Void> response = ApiResponse.serverError("서버 오류가 발생했습니다: " + ex.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }

    /**
     * 잘못된 인자 예외 처리 (400 Bad Request)
     */
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ApiResponse<Void>> handleIllegalArgumentException(IllegalArgumentException ex, WebRequest request) {
        ApiResponse<Void> response = ApiResponse.badRequest(ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    /**
     * 리소스를 찾을 수 없는 예외 처리 (404 Not Found)
     */
    @ExceptionHandler({NoHandlerFoundException.class, ResourceNotFoundException.class})
    public ResponseEntity<ApiResponse<Void>> handleNotFoundException(Exception ex, WebRequest request) {
        ApiResponse<Void> response = ApiResponse.notFound(ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    /**
     * 비즈니스 로직 예외 처리 (409 Conflict)
     */
    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ApiResponse<Void>> handleBusinessException(BusinessException ex, WebRequest request) {
        ApiResponse<Void> response = new ApiResponse<>(ex.getStatus(), ex.getMessage(), null);
        return ResponseEntity.status(ex.getStatus()).body(response);
    }
    
    /**
     * 커스텀 예외 클래스 - 리소스를 찾을 수 없음
     */
    public static class ResourceNotFoundException extends RuntimeException {
        public ResourceNotFoundException(String message) {
            super(message);
        }
    }
    
    /**
     * 커스텀 예외 클래스 - 비즈니스 로직 예외
     */
    public static class BusinessException extends RuntimeException {
        private final int status;
        
        public BusinessException(String message, int status) {
            super(message);
            this.status = status;
        }
        
        public int getStatus() {
            return status;
        }
    }
} 