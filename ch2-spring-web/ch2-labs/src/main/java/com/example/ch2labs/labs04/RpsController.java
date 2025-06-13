package com.example.ch2labs.labs04;

import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@ResponseBody
public class RpsController {

    @GetMapping("/rps")
    public Map<String, String> rps(@RequestParam String user) {

        // 서버의 랜덤 선택
        int random = (int) (Math.random() * 3);
        String server = switch (random) {
            case 0 -> "rock";
            case 1 -> "paper";
            default -> "scissors";
        };

        // 결과 판단
        String result;
        if (user.equals(server)) {
            result = "Draw!";
        } else if (
                (user.equals("rock") && server.equals("scissors")) ||
                        (user.equals("paper") && server.equals("rock")) ||
                        (user.equals("scissors") && server.equals("paper"))
        ) {
            result = "You Win!";
        } else {
            result = "You Lose!";
        }

        // 응답 JSON 구성
        Map<String, String> response = new HashMap<>();
        response.put("user", user);
        response.put("server", server);
        response.put("result", result);

        return response;

    }
}
