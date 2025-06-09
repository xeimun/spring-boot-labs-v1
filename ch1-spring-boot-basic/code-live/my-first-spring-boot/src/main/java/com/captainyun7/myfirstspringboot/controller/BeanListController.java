package com.captainyun7.myfirstspringboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class BeanListController {

    @Autowired
    private ApplicationContext context;

    @GetMapping("/my-beans")
    public List<String> myBeans() {
        return Arrays.stream(context.getBeanDefinitionNames())
                .filter(name -> {
                    Object bean = context.getBean(name);
                    return bean.getClass().getPackageName().startsWith("com.captainyun7");
                })
                .sorted()
                .toList();
    }

}
