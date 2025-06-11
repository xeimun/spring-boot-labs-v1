package com.example.myfirstspringbootapp.component_scan;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloComponentController {

    private final HelloComponentBean component;

    public HelloComponentController(HelloComponentBean component) {
        this.component = component;
    }

    @GetMapping("/hello-component")
    public String hello() {
        return component.sayHello();
    }

}
