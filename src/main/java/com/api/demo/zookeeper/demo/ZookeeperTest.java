package com.api.demo.zookeeper.demo;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

public class ZookeeperTest {
    public static void main(String[] args) throws IOException, InterruptedException, KeeperException {
        //zk有session的概念，没有连接池的概念
        //watch:观察回调
        //watch的注册只发生在读类型调用，get,exits
        //第一类：在new zk的时候传入的watch 这个watch是session级别的，和path,node没有关系
        CountDownLatch countDownLatch = new CountDownLatch(1);
        ZooKeeper zooKeeper = new ZooKeeper("101.43.98.156:2181", 3000, new Watcher() {
            //watch的回调方法
            @Override
            public void process(WatchedEvent event) {
                Event.KeeperState state = event.getState();
                Event.EventType type = event.getType();
                String path = event.getPath();
                System.out.println("new zk Watch:" + event);
                switch (state) {
                    case Unknown -> {
                    }
                    case Disconnected -> {
                    }
                    case NoSyncConnected -> {
                    }
                    case SyncConnected -> {
                        System.out.println("connected");
                        countDownLatch.countDown();
                    }
                    case AuthFailed -> {
                    }
                    case ConnectedReadOnly -> {
                    }
                    case SaslAuthenticated -> {
                    }
                    case Expired -> {
                    }
                }
                switch (type) {
                    case None -> {
                    }
                    case NodeCreated -> {
                    }
                    case NodeDeleted -> {
                    }
                    case NodeDataChanged -> {
                    }
                    case NodeChildrenChanged -> {
                    }
                }
            }
        });
        countDownLatch.await();
        ZooKeeper.States state = zooKeeper.getState();
        switch (state) {
            case CONNECTING -> {
                System.out.println("ing.....");
            }
            case ASSOCIATING -> {
            }
            case CONNECTED -> {
                System.out.println("ed.....");
            }
            case CONNECTEDREADONLY -> {
            }
            case CLOSED -> {
            }
            case AUTH_FAILED -> {
            }
            case NOT_CONNECTED -> {
            }
        }


        String pathName = zooKeeper.create("/ooxx", "olddata".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
        System.out.println(pathName);
        Stat stat = new Stat();
        byte[] data = zooKeeper.getData(pathName, new Watcher() {
            @Override
            public void process(WatchedEvent event) {
                try {
                    System.out.println("getData watch" + event);
                    //为true的时候是default Watch 被重新注册  就是new zk的那个watch
                    zooKeeper.getData("/ooxx", this, stat);
                } catch (KeeperException | InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, stat);
        System.out.println("oldData:" + new String(data));

        //触发上面的回调
        Stat stat1 = zooKeeper.setData("/ooxx", "newData".getBytes(), 0);
        Stat stat12 = zooKeeper.setData("/ooxx", "newData01".getBytes(), stat1.getVersion());

        System.out.println("-------------async  start-------------");
        zooKeeper.getData("/ooxx", false, new AsyncCallback.DataCallback() {
            @Override
            public void processResult(int rc, String path, Object ctx, byte[] data, Stat stat) {
                System.out.println("-------------async call back------------");
                System.out.println(ctx.toString());
                System.out.println(new String(data));
            }
        }, "abc");
        System.out.println("-------------async  over-------------");

        Thread.sleep(2222222);
    }
}
