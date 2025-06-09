package com.example.beans.di;

public class PremiumCoffeeMachine implements CoffeeMachine{
    @Override
    public void brew() {
        System.out.println("프리미엄 커피 머신으로 커피를 내립니다...");
    }
}
