package com.DesignMode.Singleton;

/**
 * 懒汉式、
 * 缺点：在并发场景中可能会创建多个对象
 */
public class LazySingleton {

    private static LazySingleton lazySingleton;

    private LazySingleton() {
    }

    public static LazySingleton getInstance() {
        if (lazySingleton == null) {
            lazySingleton = new LazySingleton();
        }
        return lazySingleton;
    }


    /**
     * 测试：在并发场景中可能会创建多个对象
     */
    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(LazySingleton.getInstance());
            }).start();
        }
    }
}
