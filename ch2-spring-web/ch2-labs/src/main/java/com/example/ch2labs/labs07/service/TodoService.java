package com.example.ch2labs.labs07.service;

import com.example.ch2labs.labs07.domain.Todo;
import com.example.ch2labs.labs07.dto.RequestTodoDto;
import com.example.ch2labs.labs07.dto.ResponseTodoDto;
import com.example.ch2labs.labs07.exeption.NotFoundTitleOrCompleted;
import com.example.ch2labs.labs07.repository.TodoRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class TodoService {

    private final TodoRepository todoRepository;

    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    public ResponseTodoDto getTodoById(Long id) {
        Todo todo = todoRepository.findById(id);
        ;

        return convertDto(todo);
    }

    public List<ResponseTodoDto> getAllTodos() {
        return todoRepository.findAll()
                             .stream()
                             .map(todo -> convertDto(todo))
                             .collect(Collectors.toList());
    }

    public ResponseTodoDto addTodo(RequestTodoDto request) {
        Todo todo = new Todo(0L, request.getTitle(), request.getCompleted());
        Todo savedTodo = todoRepository.save(todo);
        return convertDto(savedTodo);
    }

    public ResponseTodoDto updateTodo(long id, RequestTodoDto request) {
        if (request.getCompleted() == null || request.getTitle() == null) {
            throw new NotFoundTitleOrCompleted(request);
        }

        Todo todo = todoRepository.findById(id);
        todo.setTitle(request.getTitle());
        todo.setCompleted(request.getCompleted());
        return convertDto(todo);
    }

    public ResponseTodoDto deleteTodo(long id) {
        Todo todo = todoRepository.findById(id);
        Todo deletedTodo = todoRepository.deleteById(todo.getId());
        return convertDto(deletedTodo);
    }

    private ResponseTodoDto convertDto(Todo todo) {
        return new ResponseTodoDto(todo.getTitle(), todo.getCompleted());
    }
}
