package com.example.myfirstspringbootapp.configuration;

import java.util.concurrent.CopyOnWriteArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloConfigController {
    @Autowired
    private HelloConfigBean helloConfigBean;

    @GetMapping("/hello-config")
    public String helloConfig() {
        return helloConfigBean.sayHello();
    }

}
