package com.example.ch2labs.labs07.repository;

import com.example.ch2labs.labs07.domain.Todo;
import com.example.ch2labs.labs07.exeption.TodoNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.stereotype.Repository;

@Repository
public class TodoRepository {
    private final Map<Long, Todo> store = new HashMap<>();
    private Long sequence = 0L;

    public Todo save(Todo todo) {
        todo.setId(++sequence);
        store.put(todo.getId(), todo);
        return todo;
    }

    public Todo findById(Long id) {
        Todo todo = store.get(id);
        return Optional.ofNullable(todo)
                       .orElseThrow(() -> new TodoNotFoundException(id)); // null 처리 여기서 바로 해보기 (예정)
    }

    public List<Todo> findAll() {
        return store.values()
                    .stream()
                    .toList();
    }

    public Todo deleteById(long id) {
        return store.remove(id);
    }
}
