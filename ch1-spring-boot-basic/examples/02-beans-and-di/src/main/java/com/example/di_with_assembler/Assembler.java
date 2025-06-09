package com.example.di_with_assembler;

import com.example.di_with_assembler.cafe.Barista;
import com.example.di_with_assembler.cafe.CoffeeMachine;
import com.example.di_with_assembler.cafe.PremiumCoffeeMachine;

public class Assembler {
    public static String assemble() {
        CoffeeMachine machine = new PremiumCoffeeMachine();

        // 생성자 주입
        Barista barista = new Barista(machine);

        // 세터 주입
//        Barista barista = new Barista();
//        barista.setMachine(machine);

        return barista.makeCoffee();
    }
}
