package com.api.controller;

import com.api.pojo.User;
import com.api.pojo.UserStatus;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@RestController
public class SecurityController {

    @GetMapping("api/login")
    public Token login(User user) {
        System.out.println("请求进来了!");

        // 生成token
        String token = UUID.randomUUID().toString();
        return  new Token("登录成功", token, user.userId, user.status, true);
    }

    public static class Token {
        public String message;
        public String token;
        public Integer userId;
        public UserStatus status;
        public Boolean admin;

        public Token(String message, String token, Integer userId, UserStatus status, Boolean admin) {
            this.message = message;
            this.token = token;
            this.userId = userId;
            this.status = status;
            this.admin = admin;
        }
    }
}
