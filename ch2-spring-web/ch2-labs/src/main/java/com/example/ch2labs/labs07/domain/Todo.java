package com.example.ch2labs.labs07.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Todo {
    Long id;
    String title;
    Boolean completed;

    public Todo(Long id, String title, Boolean completed) {
        this.id = id;
        this.title = title;
        this.completed = completed;
    }
}
