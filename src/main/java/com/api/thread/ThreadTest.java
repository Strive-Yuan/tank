package com.api.thread;

public class ThreadTest {

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new MyThread());
        thread.start();
        thread.join();
        System.out.println("主线程结束");
    }
}
