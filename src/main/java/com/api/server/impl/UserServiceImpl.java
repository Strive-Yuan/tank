package com.api.server.impl;

import com.api.mapper.UserMapper;
import com.api.pojo.User;
import com.api.server.UserService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    UserMapper userMapper;

    //查询全部
    public User selectByUserNameAndPassword(String userName, String password) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", userName);
        queryWrapper.eq("password", password);
        return userMapper.selectList(queryWrapper).stream().findFirst().orElse(null);
    }
}
