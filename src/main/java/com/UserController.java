package com;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class UserController {

    @Resource
    RedisTemplate redisTemplate;
    @Resource
    ObjectMapper objectMapper;

    @GetMapping("findAll")
    public String findAll() {
        System.out.println("请求进来了!");
        return "yjhui";
    }
}
