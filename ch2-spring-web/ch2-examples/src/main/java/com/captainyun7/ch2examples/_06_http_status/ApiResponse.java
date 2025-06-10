package com.captainyun7.ch2examples._06_http_status;

import java.time.LocalDateTime;

/**
 * API 응답 래퍼 클래스
 * - 일관된 응답 형식 제공
 * - 상태 코드, 메시지, 데이터 포함
 */
public class ApiResponse<T> {
    
    private int status;          // HTTP 상태 코드
    private String message;      // 응답 메시지
    private T data;              // 응답 데이터
    private LocalDateTime timestamp;  // 응답 시간
    
    // 기본 생성자
    public ApiResponse() {
        this.timestamp = LocalDateTime.now();
    }
    
    // 생성자
    public ApiResponse(int status, String message, T data) {
        this.status = status;
        this.message = message;
        this.data = data;
        this.timestamp = LocalDateTime.now();
    }
    
    // 성공 응답 생성 (200 OK)
    public static <T> ApiResponse<T> ok(T data) {
        return new ApiResponse<>(200, "Success", data);
    }
    
    // 성공 응답 생성 (201 Created)
    public static <T> ApiResponse<T> created(T data) {
        return new ApiResponse<>(201, "Created", data);
    }
    
    // 에러 응답 생성 (400 Bad Request)
    public static <T> ApiResponse<T> badRequest(String message) {
        return new ApiResponse<>(400, message, null);
    }
    
    // 에러 응답 생성 (404 Not Found)
    public static <T> ApiResponse<T> notFound(String message) {
        return new ApiResponse<>(404, message, null);
    }
    
    // 에러 응답 생성 (500 Internal Server Error)
    public static <T> ApiResponse<T> serverError(String message) {
        return new ApiResponse<>(500, message, null);
    }
    
    // Getter와 Setter
    public int getStatus() {
        return status;
    }
    
    public void setStatus(int status) {
        this.status = status;
    }
    
    public String getMessage() {
        return message;
    }
    
    public void setMessage(String message) {
        this.message = message;
    }
    
    public T getData() {
        return data;
    }
    
    public void setData(T data) {
        this.data = data;
    }
    
    public LocalDateTime getTimestamp() {
        return timestamp;
    }
    
    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
} 