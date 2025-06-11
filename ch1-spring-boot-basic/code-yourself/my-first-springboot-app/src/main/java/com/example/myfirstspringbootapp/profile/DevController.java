package com.example.myfirstspringbootapp.profile;

import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Profile("dev")
public class DevController {
    @GetMapping("/dev")
    public String dev() {
        return "개발환경";
    }
}
