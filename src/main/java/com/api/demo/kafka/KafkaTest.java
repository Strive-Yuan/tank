package com.api.demo.kafka;

import java.util.concurrent.CountDownLatch;

public class KafkaTest {

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(1);
        new Thread(() -> {
            try {
                System.out.println(111);
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(222);
            countDownLatch.countDown();
        }).start();
        countDownLatch.await();
        System.out.println(333);
    }
}
