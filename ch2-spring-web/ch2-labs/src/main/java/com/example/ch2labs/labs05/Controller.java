package com.example.ch2labs.labs05;

import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    @GetMapping("/sum-digits")
    public ResponseEntity<Map<String, String>> sumDigits(@RequestParam(required = false) String number) {
        Map<String, String> response = new HashMap<>();

        // null 또는 빈 값 검사
        if (number == null || number.isBlank()) {
            response.put("error", "number 파라미터는 필수입니다.");

            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(response);
        }

        // 정수가 아닌 값 검사 (숫자가 아닌 문자가 포함되면)
        if (!number.matches("\\d+")) {
            response.put("error", "정수만 입력 가능합니다. 예: /sum-digits?number=1234");

            return ResponseEntity
                    .status(HttpStatus.UNPROCESSABLE_ENTITY)
                    .body(response);
        }

        // 음수 값 검사
        int parsedNumber = Integer.parseInt(number);
        if (parsedNumber < 0) {
            response.put("error", "음수는 지원하지 않습니다. 양의 정수를 입력해주세요.");

            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(response);
        }

        int sum = 0;
        for (int i = 0; i < number.length(); i++) {
            sum += Character.getNumericValue(number.charAt(i));
        }
        response.put("message", "각 자리수 합: " + sum);
        return ResponseEntity.ok(response);
    }
}
