package com.example.lifecycle;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class CoffeeApp {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(CoffeeApp.class, args);

        CoffeeMachine machine = context.getBean(CoffeeMachine.class);
        machine.makeCoffee("아메리카노");

        context.close(); // 빈 소멸 시도 (@PreDestroy 실행)
    }
}