package com.example.ch2labs.labs07.exeption;

import com.example.ch2labs.labs07.dto.ResponseErrorDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(TodoNotFoundException.class)
    public ResponseEntity<ResponseErrorDto> handleTodoNotFoundException(TodoNotFoundException e) {
        ResponseErrorDto error = new ResponseErrorDto(HttpStatus.NOT_FOUND.toString(), e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                             .body(error);
    }

    @ExceptionHandler(NotFoundTitleOrCompleted.class)
    public ResponseEntity<ResponseErrorDto> handleNotFoundTitleOrCompletedException(NotFoundTitleOrCompleted e) {
        ResponseErrorDto error = new ResponseErrorDto(HttpStatus.BAD_REQUEST.toString(), e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                             .body(error);
    }

}
