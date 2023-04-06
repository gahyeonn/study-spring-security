package com.gahyeonn.ssiach10ex1.contoller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloContoller {

    @GetMapping("/hello")
    public String getHello() {
        return "Get Hello";
    }

    @PostMapping("/hello")
    public String postHello() {
        return "Post Hello";
    }
}
