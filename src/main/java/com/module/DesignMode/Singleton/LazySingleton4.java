package com.module.DesignMode.Singleton;


/**
 * 懒汉式
 * 在第三种写法的基础上进行优化
 * 缺点: 虽然减小了锁的粒度，但是多线程并发时还是会出现创建多个对象的问题
 */
public class LazySingleton4 {
    private static LazySingleton4 lazySingleton;

    private LazySingleton4() {
    }

    public static LazySingleton4 getInstance() {
        if (lazySingleton == null) {
            synchronized (LazySingleton3.class) {
                if (lazySingleton == null){
                    lazySingleton = new LazySingleton4();
                }
            }
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
                System.out.println(LazySingleton4.getInstance());
            }).start();
        }
    }
}
