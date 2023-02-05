package com;

import com.api.demo.redis.RedisDemo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.api.*")
public class HubApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(HubApplication.class, args);
        RedisDemo bean = run.getBean(RedisDemo.class);
        bean.setString();;
    }
}
