package com.example.beans.di;

import org.springframework.stereotype.Component;


public class Barista {
    private final CoffeeMachine machine;

    // 생성자 주입
    public Barista(CoffeeMachine machine) {
        this.machine = machine;
    }

    public void makeCoffee() {
        machine.brew();
    }
}
