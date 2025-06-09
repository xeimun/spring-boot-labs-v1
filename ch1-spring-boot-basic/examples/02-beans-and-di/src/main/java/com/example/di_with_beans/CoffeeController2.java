package com.example.di_with_beans;

import com.example.di_with_beans.cafe.Barista;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CoffeeController2 {

//    //    1. 필드 주입
//    @Autowired
//    Barista barista;

    //    2. 생성자 주입
    final Barista barista;
//    @Autowired
//    public CoffeeController2(Barista barista) {
//        this.barista = barista;
//    }

    //    3. setter 주입
//    @Autowired
//    public void setBarista(Barista barista) {
//        this.barista = barista;
//    }

    @GetMapping("/coffee-bean")
    public String coffee() {
        return barista.makeCoffee();
    }
}
