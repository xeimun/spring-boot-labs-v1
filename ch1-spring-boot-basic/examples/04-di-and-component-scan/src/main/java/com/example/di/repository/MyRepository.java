package com.example.di.repository;

import org.springframework.stereotype.Repository;

@Repository
public class MyRepository {
    
    public String findMessage() {
        return "안녕하세요";
    }
} 