package com.captainyun7.ch2examples._05_validation;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/validation/signup")
public class UserController {

    @PostMapping
    public ResponseEntity<String> signup(@RequestBody @Valid SignupRequest request) {
        return ResponseEntity.ok("회원가입 성공!");
    }
}
