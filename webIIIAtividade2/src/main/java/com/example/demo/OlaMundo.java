package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OlaMundo {

    @GetMapping("/")
    public String helloWorld() {
        return "Olá Mundo!";
    }
}
