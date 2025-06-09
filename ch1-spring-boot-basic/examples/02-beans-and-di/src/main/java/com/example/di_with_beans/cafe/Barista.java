package com.example.di_with_beans.cafe;

import org.springframework.stereotype.Component;

@Component
public class Barista {
    private final CoffeeMachine machine;

    // 생성자 주입
    public Barista(CoffeeMachine machine) {
        this.machine = machine;
    }

    public String makeCoffee() {
        return machine.brew();
    }
}
