package com.example.ch2labs.labs07.exeption;

import com.example.ch2labs.labs07.dto.RequestTodoDto;

public class NotFoundTitleOrCompleted extends RuntimeException {
    public NotFoundTitleOrCompleted(RequestTodoDto request) {
        super(makeMessage(request));
    }

    private static String makeMessage(RequestTodoDto request) {
        if (request.getTitle() == null) {
            return "Title이 없습니다.";
        } else if (request.getCompleted() == null) {
            return "Completed가 없습니다.";
        } else {
            return "유효하지 않은 요청입니다.";
        }
    }
}
