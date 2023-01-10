package com.redis;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {


    @GetMapping("findAll")
    public String findAll(){
        return  "yjhui是真滴帅!";
    }
}
