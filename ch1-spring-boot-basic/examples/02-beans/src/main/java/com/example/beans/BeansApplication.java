package com.example.beans;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@SpringBootApplication
public class BeansApplication {

    public static void main(String[] args) {
        SpringApplication.run(BeansApplication.class, args);
    }
} 