package com.api.server.impl;

import com.api.mapper.UserMapper;
import com.api.pojo.User;
import com.api.pojo.UserQuery;
import com.api.server.UserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
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

    @Override
    public Page<User> queryPage(UserQuery query) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", query.userName);
        return userMapper.selectPage(Page.of(1, 10), queryWrapper);
    }

    @Override
    public List<User> queryList() {
        return userMapper.selectList(null);
    }

    @Override
    public void addUser(User user) {
        userMapper.insert(user);
    }

    @Override
    public void updateUser(User user) {
        userMapper.update(user, null);
    }

    @Override
    public void deleteUser(User user) {
        userMapper.deleteById(user.userId);
    }
}
