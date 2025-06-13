package com.example.ch2labs.labs07.exeption;

public class TodoNotFoundException extends RuntimeException {
    public TodoNotFoundException(Long id) {
        super("해당 Todo를 찾을 수 없습니다. id = " + id);
    }
}
