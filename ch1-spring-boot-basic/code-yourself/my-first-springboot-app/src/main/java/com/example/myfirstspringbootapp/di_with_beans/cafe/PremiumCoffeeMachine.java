package com.example.myfirstspringbootapp.di_with_beans.cafe;

import org.springframework.stereotype.Component;

@Component
public class PremiumCoffeeMachine implements CoffeeMachine {
    @Override
    public String brew() {
        return "프리미엄 커피 머신으로 커피를 내립니다...";
    }
}
