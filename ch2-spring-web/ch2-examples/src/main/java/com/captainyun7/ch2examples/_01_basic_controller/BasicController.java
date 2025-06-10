package com.captainyun7.ch2examples._01_basic_controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * 기본 컨트롤러와 매핑 예제
 * - 다양한 매핑 방식 (GET, POST, PUT, DELETE)
 * - 경로 변수(PathVariable)와 요청 파라미터(RequestParam) 사용
 */
@Controller
public class BasicController {

    /**
     * 문자열을 직접 반환하는 컨트롤러
     */
    @ResponseBody
    @GetMapping("/basic/hello")
    public String hello() {
        return "Hello, Spring Web!";
    }

    /**
     * 경로 변수(PathVariable) 사용 예제
     */
    @ResponseBody
    @GetMapping("/basic/users/{userId}")
    public String pathVariableExample(@PathVariable Long userId) {
        return "User ID: " + userId;
    }

    /**
     * 다중 경로 변수(PathVariable) 사용 예제
     */
    @ResponseBody
    @GetMapping("/basic/users/{userId}/orders/{orderId}")
    public String pathVariablesExample(@PathVariable Long userId,
                                       @PathVariable Long orderId) {
        return "User ID: " + userId + "Order ID: " + orderId;
    }

    /**
     * 요청 파라미터(RequestParam) 사용 예제
     * /basic/params?name=윤유저&age=20
     */
    @ResponseBody
    @GetMapping("/basic/params")
    public String requestParamExample(
            @RequestParam String name,
            @RequestParam(defaultValue = "0") int age) {
        return "Name: " + name + ", Age: " + age;
    }

    @ResponseBody
    @GetMapping("/basic/filter")
    public String filter(@RequestParam Map<String, String> params) {
        return "전체 파라미터: " + params.toString();
    }

    /**
     * 다양한 HTTP 메서드 매핑 예제
     */
    @ResponseBody
    @PostMapping("/basic/users")
    public String createUser() {
        return "사용자 생성 요청";
    }

    @ResponseBody
    @PutMapping("/basic/users/{userId}")
    public String updateUser(@PathVariable Long userId) {
        return "사용자 " + userId + " 수정 요청";
    }

    @ResponseBody
    @DeleteMapping("/basic/users/{userId}")
    public String deleteUser(@PathVariable Long userId) {
        return "사용자 " + userId + " 삭제 요청";
    }
} 