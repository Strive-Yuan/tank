package testClone.zookeeper;

import org.apache.zookeeper.ZooKeeper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestLock {
    ZooKeeper zk;

    @Before
    public void conn() {
        zk = ZkUtils.getZk();

    }

    @After
    public void close() {
        try {
            zk.close();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void lock() {

        for (int i = 0; i < 10; i++) {

            new Thread(() -> {
                WatchCallBack01 watchCallBack01 = new WatchCallBack01();
                watchCallBack01.setZk(zk);
                String name = Thread.currentThread().getName();
                watchCallBack01.setThreadName(name);
                //每一个线程

                //抢锁
                watchCallBack01.tryLock();
                //干活

                System.out.println(name +" working....");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //释放锁
                watchCallBack01.unLock();
            }).start();
        }

        try {
            Thread.sleep(30000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }



}
