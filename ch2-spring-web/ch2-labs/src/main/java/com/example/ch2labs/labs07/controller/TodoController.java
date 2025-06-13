package com.example.ch2labs.labs07.controller;

import com.example.ch2labs.labs07.dto.RequestTodoDto;
import com.example.ch2labs.labs07.dto.ResponseTodoDto;
import com.example.ch2labs.labs07.service.TodoService;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Repository
@RequestMapping("/todo")
public class TodoController {
    private final TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseTodoDto> getTodo(@PathVariable long id) {
        ResponseTodoDto todoDto = todoService.getTodoById(id);
        return ResponseEntity.ok(todoDto);
    }

    @GetMapping("/todos")
    public ResponseEntity<List<ResponseTodoDto>> getAllTodos() {
        List<ResponseTodoDto> todoTodos = todoService.getAllTodos();
        return ResponseEntity.ok(todoTodos);
    }

    // 생성 결과를 클라이언트에 전달하기 위해 반환값을 아래와 같이 설정
    @PostMapping("/todos")
    public ResponseEntity<ResponseTodoDto> addTodo(@RequestBody RequestTodoDto request) {
        ResponseTodoDto savedTodo = todoService.addTodo(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                             .body(savedTodo);
    }

    @PutMapping("/todos/{id}")
    public ResponseEntity<ResponseTodoDto> updateTodo(@PathVariable long id, @RequestBody RequestTodoDto request) {
        ResponseTodoDto updatedTodo = todoService.updateTodo(id, request);
        return ResponseEntity.ok(updatedTodo);
    }

    @DeleteMapping("/todos/{id}")
    public ResponseEntity<ResponseTodoDto> deleteTodo(@PathVariable long id) {
        ResponseTodoDto deleteTodo = todoService.deleteTodo(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT)
                             .body(deleteTodo);
    }

}
