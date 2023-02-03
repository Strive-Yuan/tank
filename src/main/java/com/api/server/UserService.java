package com.api.server;


import com.api.pojo.User;
import com.api.pojo.UserQuery;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.List;

public interface UserService {

    User selectByUserNameAndPassword(String username, String password);

    Page<User> queryPage(UserQuery query);

    List<User> queryList();

    void addUser(User user);

    void updateUser(User user);

    void deleteUser(User user);
}
