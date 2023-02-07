package com.module.algorithm.myTree;

import com.api.pojo.User;

/**
 * 收集所有累加和路径
 */
public class MyTree08 {
    public static void main(String[] args) {
        int num = 6;
        sum(num);
        System.out.println(num);

        System.out.println("------------------------");
        User user = new User();
        user.userId = 1;
        changeUser(user);
        System.out.println(user.userId);
    }

    private static void changeUser(User user) {
        user = new User();
        user.userId = 2;
        System.out.println(user.userId);
    }

    private static void sum(int num) {
        num = num + 1;
        System.out.println(num);
    }
}
