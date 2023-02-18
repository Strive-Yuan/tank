package testClone.zookeeper;

import org.apache.zookeeper.AsyncCallback;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

import java.util.concurrent.CountDownLatch;

public class WatchCallBack implements Watcher, AsyncCallback.StatCallback, AsyncCallback.DataCallback {

    ZooKeeper zk;
    MyConf conf;
    CountDownLatch countDownLatch = new CountDownLatch(1);

    public void setZk(ZooKeeper zk) {
        this.zk = zk;
    }

    public void setConf(MyConf conf) {
        this.conf = conf;
    }

    @Override
    public void processResult(int rc, String path, Object ctx, byte[] data, Stat stat) {
        if (data != null) {
            String s = new String(data);
            conf.setConf(s);
            countDownLatch.countDown();
        }
    }

    @Override
    public void processResult(int rc, String path, Object ctx, Stat stat) {
        if (stat != null) {
            zk.getData("/AppConf", this, this, "sfps");
        }
    }

    @Override
    public void process(WatchedEvent event) {
        switch (event.getType()) {
            case None -> {
            } 
            case NodeCreated -> {
                zk.getData("/AppConf", this, this, "sfps");
            }
            case NodeDeleted -> {
                //容忍性
                conf.setConf("");
                countDownLatch = new CountDownLatch(1);
            }
            case NodeDataChanged -> {
                zk.getData("/AppConf", this, this, "sfps");
            }
            case NodeChildrenChanged -> {
            }
        }
    }

    public void aWait() throws InterruptedException {
        zk.exists("/AppConf", this, this, "abc");
        countDownLatch.await();
    }
}
