package com.api.pojo;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import java.time.LocalDateTime;
import java.util.Set;


public class User {
    @TableId(value = "user_id", type = IdType.AUTO)//指定自增策略
    public Integer userId;
    public String username;// 登录名
    public String realName;// 姓名
    public String alias; //English Name
    public String email;// 邮箱
    public String phoneNumber;// 手机号
    public String avatarUrl;// 用户头像URL
    public UserStatus status;
    @JsonProperty(access = Access.WRITE_ONLY)
    public String password;// 密码 (用户初次登录需要修改密码)
    public LocalDateTime createdAt;
    public LocalDateTime lastLoginAt;
}
