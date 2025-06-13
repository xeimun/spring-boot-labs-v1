package com.example.ch2labs.labs07.dto;

import lombok.Getter;

@Getter
public class RequestTodoDto {
    String title;
    Boolean completed;

    public RequestTodoDto(String title, Boolean completed) {
        this.title = title;
        this.completed = completed;
    }
}
