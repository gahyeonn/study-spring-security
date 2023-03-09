package com.gahyeonn.ssiach5ex4.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

//html 뷰 반환 위함
@Controller
public class HelloController {

    @GetMapping("/home")
    public String home() {
        return "home.html";
    }

}
