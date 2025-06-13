package com.example.ch2labs.labs01;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@ResponseBody
public class CalcController {

    @GetMapping("/calc")
    public String calc(@RequestParam int x, @RequestParam int y, @RequestParam String op) {

        switch (op) {
            case "add":
                return x + " + " + y + " = " + (x + y);
            case "sub":
                return x + " - " + y + " = " + (x - y);
            case "mul":
                return x + " * " + y + " = " + (x * y);
            case "div":
                return x + " / " + y + " = " + (x / y);
            default:
                return "지원하지 않는 연산자입니다. (add, sub, mul, div)";
        }

    }
}
