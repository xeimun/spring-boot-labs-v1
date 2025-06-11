package com.example.myfirstspringbootapp.di_with_beans.cafe;

import org.springframework.stereotype.Component;

@Component
public class Barista {
    private CoffeeMachine machine;

    public Barista(CoffeeMachine machine) {
        this.machine = machine;
    }

    public String makeCoffee() {
        return machine.brew();
    }
}
