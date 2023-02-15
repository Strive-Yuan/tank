package testClone.zookeeper;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooKeeper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestConfig {
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
    public void getConf() throws InterruptedException, KeeperException {
        WatchCallBack watchCallBack = new WatchCallBack();
        MyConf myConf = new MyConf();
        watchCallBack.setZk(zk);
        watchCallBack.setConf(myConf);
        watchCallBack.aWait();
        while (true){
            if (myConf.getConf().equals("")){
                System.out.println("conf diu le ......");
                watchCallBack.aWait();
            }else {
                System.out.println(myConf.getConf());
            }
            Thread.sleep(200);
        }

    }

}
