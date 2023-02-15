package testClone.zookeeper;

import org.apache.zookeeper.ZooKeeper;

import java.util.concurrent.CountDownLatch;

public class ZkUtils {
    private static ZooKeeper zk;
    private static final String address = "101.43.98.156:2181/testLock";
    private static final DefaultWatch defaultWatch = new DefaultWatch();
    private static final CountDownLatch countDownLatch = new CountDownLatch(1);

    public static ZooKeeper getZk() {

        try {
            zk = new ZooKeeper(address, 1000, defaultWatch);
            defaultWatch.setCountDownLatch(countDownLatch);
            countDownLatch.await();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return zk;
    }
}
