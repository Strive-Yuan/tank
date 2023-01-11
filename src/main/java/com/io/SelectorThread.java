package com.io;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.LinkedBlockingQueue;

public class SelectorThread implements Runnable {

    //每一个线程对应一个selector
    //对线程情况下吗，该主机，该程序的并发客户端被分配到多个selector上
    //注意，每个客户端，只绑定到其中一个selector
    //其实不会有交互问题

    Selector selector = null;

    LinkedBlockingQueue<Channel> lbq = new LinkedBlockingQueue<>();

    SelectorThreadGroup stg;

    SelectorThread(SelectorThreadGroup selectorThreadGroup) {
        try {
            this.stg = selectorThreadGroup;
            selector = Selector.open();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        //loop
        while (true) {
            try {
                //1.select
                System.out.println("蚌住了!!!");
                int nums = selector.select(); //阻塞
                System.out.println(Thread.currentThread().getName() + " : " + nums);
                //2.处理selectKeys
                if (nums > 0) {
                    Set<SelectionKey> keys = selector.selectedKeys();
                    Iterator<SelectionKey> iterator = keys.iterator();
                    while (iterator.hasNext()) { //线程处理过程
                        SelectionKey key = iterator.next();
                        iterator.remove();
                        if (key.isAcceptable()) { //是否准备好连接了     接收客户端的过程（接收之后，要注册，多线程情况下，新的客户端注册到哪一个selector中呢）
                            acceptHandler(key);
                        } else if (key.isReadable()) {//是否准备好读了
                            readHandler(key);
                        } else if (key.isWritable()) {//是否准备好写了
                            writeHandler(key);
                        }


                    }
                }
                //3.处理一些task
                if (!lbq.isEmpty()) {
                    Channel c = lbq.take();
                    if (c instanceof ServerSocketChannel) {
                        ServerSocketChannel server = (ServerSocketChannel) c;
                        server.register(selector, SelectionKey.OP_ACCEPT);
                    } else if (c instanceof SocketChannel) {
                        SocketChannel client = (SocketChannel) c;
                        ByteBuffer buffer = ByteBuffer.allocate(4098);
                        client.register(selector, SelectionKey.OP_READ, buffer);
                    }
                }
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void writeHandler(SelectionKey key) {
        System.out.println("writeHandler!");
    }

    private void readHandler(SelectionKey key) {
        System.out.println("readHandler!");
        ByteBuffer buffer = (ByteBuffer) key.attachment();
        SocketChannel client = (SocketChannel) key.channel();
        buffer.clear();
        while (true) {
            try {
                int num = client.read(buffer);
                if (num > 0) {
                    buffer.flip();
                    while (buffer.hasRemaining()) {
                        client.write(buffer);
                    }
                    buffer.clear();
                } else if (num == 0) {
                    break;
                } else {
                    //客户端断开了
                    System.out.println("client:" + client.getRemoteAddress() + " closed...");
                    key.cancel();
                    break;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void acceptHandler(SelectionKey key) {
        System.out.println("acceptHandler!");
        ServerSocketChannel server = (ServerSocketChannel) key.channel();

        try {
            SocketChannel client = server.accept();
            client.configureBlocking(false); //非阻塞
            //choose a selector and register
            stg.nextSelector3(client);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setWorker(SelectorThreadGroup stgWorker) {
        this.stg = stgWorker;
    }
}
