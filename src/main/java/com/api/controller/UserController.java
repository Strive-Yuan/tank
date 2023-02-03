package com.api.controller;

import com.api.pojo.User;
import com.api.pojo.UserQuery;
import com.api.response.ServerResponseEntity;
import com.api.server.UserService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final static Logger logger = LoggerFactory.getLogger(SecurityController.class);

    @Resource
    UserService userService;

    @PostMapping("/page")
    public ServerResponseEntity<Page<User>> getPage(@RequestBody UserQuery query) {
        Page<User> userPage = userService.queryPage(query);
        return ServerResponseEntity.success(userPage);
    }

    @GetMapping("/list")
    public ServerResponseEntity<List<User>> getList() {
        List<User> userList = userService.queryList();
        return ServerResponseEntity.success(userList);
    }

    @PostMapping("/add")
    public ServerResponseEntity<List<User>> addUser(@RequestBody User user) {
        userService.addUser(user);
        return ServerResponseEntity.success();
    }

    @PutMapping("/update")
    public ServerResponseEntity<List<User>> updateUser(@RequestBody User user) {
        userService.updateUser(user);
        return ServerResponseEntity.success();
    }

    @DeleteMapping("/delete")
    public ServerResponseEntity<List<User>> deleteUser(@RequestBody User user) {
        userService.deleteUser(user);
        return ServerResponseEntity.success();
    }

}
