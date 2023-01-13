package com;

import com.redis.RedisDemo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class MyAlgorithmApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext cxt = SpringApplication.run(MyAlgorithmApplication.class, args);
    }
}
