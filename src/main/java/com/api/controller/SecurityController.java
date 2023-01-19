package com.api.controller;

import com.api.exception.ApiException;
import com.api.exception.ErrorCode;
import com.api.exception.GlobalExceptionHandler;
import com.api.pojo.User;
import com.api.pojo.UserStatus;
import com.api.server.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;
import java.util.UUID;


@RestController
public class SecurityController {
    private final static Logger logger = LoggerFactory.getLogger(SecurityController.class);

    @Resource
    UserService userService;

    @PostMapping("api/login")
    public Token login(@RequestBody User user) throws ApiException {
        logger.info("进入登录接口!");
        User oldUser = userService.selectByUserNameAndPassword(user.username, user.password);
        if (Objects.isNull(oldUser)) {
            throw new ApiException(ErrorCode.NOT_FOUNT, "用户不存在！");
        }
        // 生成token
        String token = UUID.randomUUID().toString();
        return new Token("登录成功", token, 1, UserStatus.Enabled, true);
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
