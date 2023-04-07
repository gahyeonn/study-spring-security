package com.gahyeonn.ssiach10ex4.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.logging.Logger;

@Controller
public class MainController {
    private Logger logger = Logger.getLogger(MainController.class.getName());

    @GetMapping("/")
    public String main() {
        return "main";
    }

    //cors 작동을 확인하기 위해 다른 출처에서 호출할 엔드포인트 정의
    @PostMapping("/test")
    @ResponseBody //@Controller 클래스이기 때문에 명시적 추가 필요
    public String test() {
        logger.info("test method call");
        return "Hello";
    }
}
