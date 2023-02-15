package testClone.zookeeper;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class WatchCallBack01 implements Watcher, AsyncCallback.StringCallback, AsyncCallback.Children2Callback, AsyncCallback.StatCallback {
    ZooKeeper zk;
    String threadName;
    CountDownLatch countDownLatch = new CountDownLatch(1);
    String pathName;

    public void setPathName(String pathName) {
        this.pathName = pathName;
    }

    public String getPathName() {
        return pathName;
    }

    public ZooKeeper getZk() {
        return zk;
    }

    public void setThreadName(String threadName) {
        this.threadName = threadName;
    }

    public String getThreadName() {
        return threadName;
    }

    public void setZk(ZooKeeper zk) {
        this.zk = zk;
    }

    public void tryLock() {
        try {
            zk.create("/lock", threadName.getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL, this, "abc");
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void unLock() {
        try {
            zk.delete(pathName, -1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (KeeperException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void process(WatchedEvent event) {

        switch (event.getType()) {
            case None -> {
            }
            case NodeCreated -> {
            }
            case NodeDeleted -> {
                zk.getChildren("/", false, this, "sdf");
            }
            case NodeDataChanged -> {
            }
            case NodeChildrenChanged -> {
            }
        }
    }

    @Override
    public void processResult(int rc, String path, Object ctx, String name) {

        if (name != null) {
            System.out.println(threadName + " create node:" + name);
            pathName = name;
            zk.getChildren("/", false, this, "sdf");
        }
    }

    //get children call back
    @Override
    public void processResult(int rc, String path, Object ctx, List<String> children, Stat stat) {
        //一定能看到自己前面创建的节点
//        System.out.println(threadName + " look locks");
//        for (String child : children) {
//            System.out.println(child);
//
//        }
        Collections.sort(children);
        int i = children.indexOf(pathName.substring(1));
        //是不是第一个
        if (i == 0) {
            //yes
            System.out.println(threadName + " i am first....");
            countDownLatch.countDown();
        } else {
            //no
            System.out.println(threadName + " i am not first....");
            zk.exists("/" + children.get(i - 1), this, this, "sdf");
        }


    }

    @Override
    public void processResult(int rc, String path, Object ctx, Stat stat) {
        //偷懒
    }
}
