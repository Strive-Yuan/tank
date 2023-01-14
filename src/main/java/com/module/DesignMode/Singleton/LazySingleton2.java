package com.module.DesignMode.Singleton;

/**
 * 懒汉式
 * 在第一种写法的基础上进行优化
 * 缺点: 锁粒度太大，效率降低
 */
public class LazySingleton2 {
    private static LazySingleton2 lazySingleton;

    private LazySingleton2() {
    }

    public static synchronized LazySingleton2 getInstance() {
        if (lazySingleton == null) {
            lazySingleton = new LazySingleton2();
        }
        return lazySingleton;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(LazySingleton2.getInstance());
            }).start();
        }
    }
}
