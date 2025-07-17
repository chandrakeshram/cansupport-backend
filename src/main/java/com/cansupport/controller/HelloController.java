package com.cansupport.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
public class HelloController {
    @GetMapping("path")
    public String hello() {
        return "Hello! CAN Support Backend is Working ";
    }

}