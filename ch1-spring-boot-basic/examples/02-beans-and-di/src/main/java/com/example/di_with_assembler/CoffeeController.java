package com.example.di_with_assembler;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CoffeeController {
    @GetMapping("/coffee")
    public String coffee() {
        return Assembler.assemble();
    }
}
