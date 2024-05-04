package com.atguigu.securitydemo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/security")
public class IndexController {
    @GetMapping("/index")
    public String hello() {
        return "index2";
    }
}
