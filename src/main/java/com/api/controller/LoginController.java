package com.api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @GetMapping("findAll")
    public String findAll() {
        System.out.println("请求进来了!");
        return "yjhui";
    }
}
