package com.example.beans.di;

public class BasicCoffeeMachine implements CoffeeMachine{
    @Override
    public void brew() {
        System.out.println("일반 커피 머신으로 커피를 내립니다...");
    }
}
