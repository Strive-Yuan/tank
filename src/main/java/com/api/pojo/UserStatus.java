package com.api.pojo;

public enum UserStatus {
    /**
     * 不可登录 (特殊用户, 如: 临时工)
     */
    NoLogin,
    /**
     * 在职状态: On the job
     */
    Enabled,

    /**
     * 标记首次登录未修改密码的员工
     */
    ResetRequired,

    /**
     * 已离职状态: Resigned 不可登录
     */
    Disabled;


}
