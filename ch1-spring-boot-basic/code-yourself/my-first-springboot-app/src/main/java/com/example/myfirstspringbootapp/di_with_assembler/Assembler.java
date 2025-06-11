package com.example.myfirstspringbootapp.di_with_assembler;

import com.example.myfirstspringbootapp.di_with_assembler.cafe.Barista;
import com.example.myfirstspringbootapp.di_with_assembler.cafe.CoffeeMachine;
import com.example.myfirstspringbootapp.di_with_assembler.cafe.PremiumCoffeeMachine;

public class Assembler {
    public static String assemble() {
        CoffeeMachine machine = new PremiumCoffeeMachine();

        Barista barista = new Barista(machine);

        return barista.makeCoffee();
    }
}
