package com.api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.module.redis.Person;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.hash.Jackson2HashMapper;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;

@RestController
public class RedisController {

    @Resource
    @Qualifier("ooxx")
    StringRedisTemplate stringRedisTemplate;
    @Resource
    RedisTemplate redisTemplate;
    @Resource
    ObjectMapper objectMapper;

    public void setString() {
        System.out.println(1111);
//        System.out.println(stringRedisTemplate);
//        stringRedisTemplate.opsForValue().set("hello", "帅啊！");
//        stringRedisTemplate.opsForValue().set("hello1", "12");
//        System.out.println(Objects.requireNonNull(stringRedisTemplate.opsForValue().get("hello")).toString());

//        RedisConnection connection = redisTemplate.getConnectionFactory().getConnection();
//        connection.set("hello2".getBytes(),"666".getBytes());
//        System.out.println(new String(connection.get("hello2".getBytes())));
//
//
//        HashOperations<String, Object, Object> hash = stringRedisTemplate.opsForHash();
//        hash.put("sean","name","zhangsan");
//        hash.put("sean","age","22");
//
//        System.out.println(hash.entries("sean"));

        Jackson2HashMapper jm = new Jackson2HashMapper(objectMapper, false);

        Person person = new Person();
        person.setName("马超");
        person.setAge(18);

        redisTemplate.opsForHash().putAll("sean01", jm.toHash(person));
        Map map = redisTemplate.opsForHash().entries("sean01");
        Person person1 = objectMapper.convertValue(map, Person.class);
        System.out.println(person1.getName());
        System.out.println(person1.getAge());
    }
}
