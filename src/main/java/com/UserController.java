package com;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.redis.Person;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.hash.Jackson2HashMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;

@RestController
public class UserController {

    @Resource
    RedisTemplate redisTemplate;
    @Resource
    ObjectMapper objectMapper;

    @GetMapping("findAll")
    public String findAll() {
        return "yjhui";
    }
}
