package com.api.thread;

public class MyThread implements Runnable {

    @Override
    public void run() {
        for (int i = 0; i < 5000; i++) {
            if (Thread.interrupted()) {
                System.out.println("已经是停止状态了!我要退出了!");
                return;
            }
            System.out.println("i=" + (i + 1));
        }
        System.out.println("线程结束");

    }
}
