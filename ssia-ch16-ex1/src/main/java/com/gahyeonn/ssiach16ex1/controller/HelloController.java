package com.gahyeonn.ssiach16ex1.controller;

import com.gahyeonn.ssiach16ex1.service.NameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    private final NameService nameService;

    public HelloController(NameService nameService) {
        this.nameService = nameService;
    }


    @GetMapping("/hello")
    public String hello() {
        return "Hello, " + nameService.getName(); //사전 권한 부여 규칙 적용한 메서드 호출
    }
}
