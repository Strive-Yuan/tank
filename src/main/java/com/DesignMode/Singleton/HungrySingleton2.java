package com.DesignMode.Singleton;


/**
 * 饿汉式
 * 第二种写法，和第一种没啥区别
 */
public class HungrySingleton2 {
    private static final HungrySingleton2 hungrySingleton;

    static {
        hungrySingleton = new HungrySingleton2();
    }

    private HungrySingleton2() {
    }

    public static HungrySingleton2 getInstance() {
        return hungrySingleton;
    }
}
