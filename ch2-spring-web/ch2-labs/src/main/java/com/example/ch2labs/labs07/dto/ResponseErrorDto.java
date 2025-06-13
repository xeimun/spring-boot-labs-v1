package com.example.ch2labs.labs07.dto;

import lombok.Getter;

@Getter
public class ResponseErrorDto {
    private String code;
    private String message;

    public ResponseErrorDto(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
