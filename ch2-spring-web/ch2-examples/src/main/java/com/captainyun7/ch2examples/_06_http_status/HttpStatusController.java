package com.captainyun7.ch2examples._06_http_status;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * HTTP 상태 코드와 응답 처리 예제
 * - 다양한 HTTP 상태 코드 사용
 * - ResponseEntity를 활용한 응답 제어
 * - 일관된 응답 형식 제공
 */
@RestController
@RequestMapping("/http")
public class HttpStatusController {

    /**
     * 200 OK - 기본 성공 응답
     */
    @GetMapping("/ok")
    public ResponseEntity<ApiResponse<String>> ok() {
        ApiResponse<String> response = ApiResponse.ok("요청이 성공적으로 처리되었습니다.");
        return ResponseEntity.ok(response);
    }

    /**
     * 201 Created - 리소스 생성 성공
     * Location 헤더에 생성된 리소스의 URI 포함
     */
    @PostMapping("/created")
    public ResponseEntity<ApiResponse<Map<String, Object>>> created() {
        Map<String, Object> data = new HashMap<>();
        data.put("id", 123);
        data.put("name", "새 리소스");
        
        ApiResponse<Map<String, Object>> response = ApiResponse.created(data);
        
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(URI.create("/resources/123"));
        
        return new ResponseEntity<>(response, headers, HttpStatus.CREATED);
    }

    /**
     * 204 No Content - 성공했지만 응답 본문 없음
     */
    @DeleteMapping("/no-content/{id}")
    public ResponseEntity<Void> noContent(@PathVariable Long id) {
        // 리소스 삭제 로직 (생략)
        return ResponseEntity.noContent().build();
    }

    /**
     * 400 Bad Request - 잘못된 요청
     */
    @GetMapping("/bad-request")
    public ResponseEntity<ApiResponse<Void>> badRequest() {
        ApiResponse<Void> response = ApiResponse.badRequest("잘못된 요청 파라미터가 포함되었습니다.");
        return ResponseEntity.badRequest().body(response);
    }

    /**
     * 404 Not Found - 리소스를 찾을 수 없음
     */
    @GetMapping("/not-found/{id}")
    public ResponseEntity<ApiResponse<Void>> notFound(@PathVariable Long id) {
        ApiResponse<Void> response = ApiResponse.notFound("ID가 " + id + "인 리소스를 찾을 수 없습니다.");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    /**
     * 409 Conflict - 리소스 충돌
     */
    @PostMapping("/conflict")
    public ResponseEntity<ApiResponse<Void>> conflict() {
        ApiResponse<Void> response = new ApiResponse<>(409, "이미 존재하는 리소스입니다.", null);
        return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
    }

    /**
     * 500 Internal Server Error - 서버 오류
     */
    @GetMapping("/server-error")
    public ResponseEntity<ApiResponse<Void>> serverError() {
        ApiResponse<Void> response = ApiResponse.serverError("서버에서 오류가 발생했습니다.");
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }

    /**
     * 여러 상태 코드 반환 가능 - 조건에 따라 다른 상태 코드 반환
     */
    @GetMapping("/items/{id}")
    public ResponseEntity<?> getItem(@PathVariable Long id) {
        if (id <= 0) {
            ApiResponse<Void> errorResponse = ApiResponse.badRequest("ID는 양수여야 합니다.");
            return ResponseEntity.badRequest().body(errorResponse);
        }
        
        if (id > 100) {
            ApiResponse<Void> errorResponse = ApiResponse.notFound("ID가 " + id + "인 아이템을 찾을 수 없습니다.");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        }
        
        // 성공 응답
        Map<String, Object> item = new HashMap<>();
        item.put("id", id);
        item.put("name", "아이템 " + id);
        item.put("price", 1000 * id);
        
        ApiResponse<Map<String, Object>> response = ApiResponse.ok(item);
        return ResponseEntity.ok(response);
    }
    
    /**
     * 커스텀 헤더 추가 예제
     */
    @GetMapping("/custom-headers")
    public ResponseEntity<ApiResponse<List<String>>> customHeaders() {
        List<String> data = Arrays.asList("데이터1", "데이터2", "데이터3");
        ApiResponse<List<String>> response = ApiResponse.ok(data);
        
        return ResponseEntity.ok()
                .header("Custom-Header", "custom-value")
                .header("X-API-Version", "1.0")
                .body(response);
    }
} 