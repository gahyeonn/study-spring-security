package com.gahyeonn.ssiach8ex2.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @PostMapping("/a")
    public String postEndpoint() {
        return "works";
    }

    @GetMapping("/a")
    public String getEndpointA() {
        return "works";
    }

    @GetMapping("/a/b")
    public String getEndpointB() {
        return "works!";
    }

    @GetMapping("/a/b/c")
    public String getEndpointC() {
        return "works";
    }
}
