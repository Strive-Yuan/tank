package com.api.server;


import com.api.pojo.User;

import java.util.List;

public interface UserService {

    User selectByUserNameAndPassword(String username, String password);
}
