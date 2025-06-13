package com.example.ch2labs.labs07.config;

import com.example.ch2labs.labs07.domain.Todo;
import com.example.ch2labs.labs07.repository.TodoRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

@Component
public class DataInit {

    private final TodoRepository todoRepository;

    public DataInit(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    @PostConstruct
    public void init() {
        todoRepository.save(new Todo(0L, "스프링부트 공부하기", false));
        todoRepository.save(new Todo(0L, "리액트 수업 듣기", true));
    }
}
