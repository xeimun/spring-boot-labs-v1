package com.example.beans.di;

public class Main {
    public static void main(String[] args) {
        CoffeeMachine machine = new PremiumCoffeeMachine();
        Barista barista = new Barista(machine); // DI

        barista.makeCoffee();
    }
}
