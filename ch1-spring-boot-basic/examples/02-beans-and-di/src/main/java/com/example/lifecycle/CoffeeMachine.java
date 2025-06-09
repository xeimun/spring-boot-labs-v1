package com.example.lifecycle;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.stereotype.Component;

@Component
public class CoffeeMachine {

    public CoffeeMachine() {
        System.out.println("1. 커피머신이 플러그에 연결되었습니다. (생성자)");
    }

    @PostConstruct
    public void heatUp() {
        System.out.println("2. 커피머신이 물을 데우고 준비 중입니다. (@PostConstruct - 초기화)");
    }

    public void makeCoffee(String name) {
        System.out.println("3. 커피머신이 " + name + " 커피를 내립니다. ️");
    }

    @PreDestroy
    public void shutdown() {
        System.out.println("4. 커피머신이 전원을 끄고 정리합니다. (@PreDestroy - 소멸)");
    }
}
