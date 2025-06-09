package com.example.environment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
public class EnvController {

    @Autowired
    private Environment environment;

    @Value("${app.name}")
    private String appName;

    @GetMapping("/env")
    public ArrayList<String> printEnv() {
        ArrayList<String> list = new ArrayList<>();
        list.add(environment.getProperty("spring.application.name"));
        list.add(appName);
        list.add(environment.getProperty("server.port"));
        list.add(environment.getProperty("app.name"));
        list.add(environment.getProperty("app.version"));
        list.add(environment.getProperty("app.message"));
        return list;
    }
}
