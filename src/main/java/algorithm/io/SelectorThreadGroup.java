package algorithm.io;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.Channel;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.concurrent.atomic.AtomicInteger;

public class SelectorThreadGroup {

    SelectorThread[] sts;
    ServerSocketChannel server = null;
    AtomicInteger atomicInteger = new AtomicInteger();

    SelectorThreadGroup stg = this;

    public void setWorker(SelectorThreadGroup stg) {
        this.stg = stg;
    }

    SelectorThreadGroup(int num) {
        sts = new SelectorThread[num];
        for (int i = 0; i < num; i++) {
            sts[i] = new SelectorThread(this);
            new Thread(sts[i]).start();
        }
    }

    public void bind(int port) {
        try {
            server = ServerSocketChannel.open();
            server.configureBlocking(false);
            server.bind(new InetSocketAddress(port));
            //注册到哪个selector？
            nextSelector3(server);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //socket都可以复用这个方法
    public void nextSelector(Channel channel) {
        SelectorThread next = next();
        //通过队列传递消息
        next.lbq.add(channel);
        //通过打断阻塞，让对应的线程去自己在打断后完成注册selector
        next.selector.wakeup();
        System.out.println("         别蚌了!!!");

    }

    public void nextSelector2(Channel channel) {
        System.out.println("服务端绑定第一个selector");
        try {
            if (channel instanceof ServerSocketChannel) {
                sts[0].lbq.put(channel);
                sts[0].selector.wakeup();
            }
            if (channel instanceof SocketChannel) {
                SelectorThread next = nextV2();
                //通过队列传递消息
                next.lbq.add(channel);
                //通过打断阻塞，让对应的线程去自己在打断后完成注册selector
                next.selector.wakeup();
            }
        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    public void nextSelector3(Channel channel) {
        System.out.println("服务端绑定第一个selector");
        try {
            if (channel instanceof ServerSocketChannel) {
               SelectorThread st = next();
               st.lbq.put(channel);
               st.setWorker(stg);
               st.selector.wakeup();
            }
            if (channel instanceof SocketChannel) {
                SelectorThread next = nextV3();
                //通过队列传递消息
                next.lbq.add(channel);
                //通过打断阻塞，让对应的线程去自己在打断后完成注册selector
                next.selector.wakeup();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();

        }
    }

    private SelectorThread next() {
        int index = atomicInteger.incrementAndGet() % sts.length;
        return sts[index];
    }

    private SelectorThread nextV2() {
        int index = atomicInteger.incrementAndGet() % (sts.length - 1);
        return sts[index + 1];
    }

    private SelectorThread nextV3() {     //worker线程分配
        int index = atomicInteger.incrementAndGet() % (stg.sts.length - 1);
        return stg.sts[index + 1];
    }
}
