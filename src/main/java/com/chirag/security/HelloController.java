package com.chirag.security;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @GetMapping("/hello")
    public String sayHello() {
        return "Hello Ji";
    }

    @GetMapping("/contact")
    public String sayContact() {
        return "Contact";
    }

}