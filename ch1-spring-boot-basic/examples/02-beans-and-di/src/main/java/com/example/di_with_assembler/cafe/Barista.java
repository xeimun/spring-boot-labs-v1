package com.example.di_with_assembler.cafe;


public class Barista {
    private CoffeeMachine machine;

    public Barista() {
    }

    // 생성자 주입
    public Barista(CoffeeMachine machine) {
        this.machine = machine;
    }

    // setter 주입
    public void setMachine(CoffeeMachine machine) {
        this.machine = machine;
    }

    public String makeCoffee() {
        return machine.brew();
    }
}
