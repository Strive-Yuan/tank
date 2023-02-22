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
    /**
     * ack:
     *  0:数据发送过去即可，不要求持久化
     *  1:数据发送过去要求持久化
     * -1:最严苛，所有的副本都要同步（一致）
     *    例：当有3个broker，1为leader，2和3为follower,当ack=-1时，要求所有的副本同步，当2同步成功3同步失败时，1和2为ISR
     *       3为OSR，
     */

}
