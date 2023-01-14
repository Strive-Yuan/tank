package com.module.io.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.ByteToMessageDecoder;
import org.junit.Test;

import java.io.*;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.InetSocketAddress;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 1.假设一个需求，写一个RPC
 * 2.来回通信，连接数量，拆包？
 * 3.动态代理，序列化，协议封装
 * 4.连接池
 * 5.简单解释：就像调用本地方法一样去调用远程方法，面向java中就是所谓的，面向interface开发
 */
public class MyRPC {

    @Test
    public void startServer() {
        NioEventLoopGroup boss = new NioEventLoopGroup(50);
        NioEventLoopGroup worker = boss;

        ServerBootstrap sbs = new ServerBootstrap();
        ChannelFuture bind = sbs.group(boss, worker)
                .channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel channel) throws Exception {
                        System.out.println("server accept client port :" + channel.remoteAddress().getPort());
                        ChannelPipeline pipeline = channel.pipeline();
                        pipeline.addLast(new ServerDecode());
                        pipeline.addLast(new ServerRequestHandler());
                    }
                }).bind(new InetSocketAddress("localhost", 9090));
        try {
            bind.sync().channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void get() {
        new Thread(this::startServer).start();
        System.out.println("server started.....");

        AtomicInteger num = new AtomicInteger(0);
        Thread[] threads = new Thread[20];
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(() -> {
                Car car = proxyGet(Car.class); //动态代理
                String arg = "hello" + num.incrementAndGet();
                String res = car.msg(arg);
                System.out.println("client over msg" + res + " src arg :" + arg);
            });
        }
        for (Thread thread : threads) {
            thread.start();
        }
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }

//        Fly fly = proxyGet(Fly.class); //动态代理
//        fly.msg("hello fly");

    }

    public static <T> T proxyGet(Class<T> interfaceInfo) {
        //实现各个版本的动态代理
        ClassLoader loader = interfaceInfo.getClassLoader();
        Class<?>[] methodInfo = {interfaceInfo};
        return (T) Proxy.newProxyInstance(loader, methodInfo, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                //如何涉及我们的consumer对于provider的调用过程

                //1.调用服务，方法，参数 -> 封装能够传递的message [content]
                String name = interfaceInfo.getName();
                String methodName = method.getName();
                Class<?>[] parameterTypes = method.getParameterTypes();
                MyContent content = new MyContent();
                content.setArgs(args);
                content.setName(name);
                content.setMethodName(methodName);
                content.setParameterTypes(parameterTypes);

                ByteArrayOutputStream out = new ByteArrayOutputStream();
                ObjectOutputStream oout = new ObjectOutputStream(out);
                oout.writeObject(content);
                byte[] msgBody = out.toByteArray();

                //2.requestID + message, 本地要缓存
                //协议：【header<>】 【msgBody】
                MyHeader header = createHeader(msgBody);
                out.reset();
                oout = new ObjectOutputStream(out);
                oout.writeObject(header);
                byte[] msgHeader = out.toByteArray();
//                System.out.println("msgHeader:" + msgHeader.length);  System.out.println("msgHeader:" + msgHeader.length);

                //3.连接池：取得连接
                ClientFactory factory = ClientFactory.getFactory();
                NioSocketChannel clientChannel = factory.getClient(new InetSocketAddress("localhost", 9090));
                //获取连接过程中： 开始->创建。过程-直接取


                //4.发送 --> 走io   out-->走netty（event 驱动）
                ByteBuf byteBuf = PooledByteBufAllocator.DEFAULT.directBuffer(msgHeader.length + msgBody.length);
//                CountDownLatch countDownLatch = new CountDownLatch(1);
                long id = header.getRequestId();
                CompletableFuture<String> res = new CompletableFuture<>();
                ResponseMappingCallback.addCallBack(id, res);
//                System.out.println("header:"+msgHeader.length);
//                System.out.println("body:"+msgBody.length);

                byteBuf.writeBytes(msgHeader);
                byteBuf.writeBytes(msgBody);
                ChannelFuture channelFuture = clientChannel.writeAndFlush(byteBuf);
                channelFuture.sync(); //io是双向的,看似有个sync,她仅代表out

//                countDownLatch.await();
                //5. ？ 如果从io回来，未来回来了，怎么将代码执行到这里
                // (睡眠/回调，如何让线程停下来)

                String s = res.get();
                System.out.println("res.get():" + s);
                return res.get();//阻塞的
            }
        });
    }

    public static MyHeader createHeader(byte[] msg) {
        MyHeader header = new MyHeader();
        int size = msg.length;
        long requestId = Math.abs(UUID.randomUUID().getLeastSignificantBits());
        int f = 0x14141414;
        //0x14   0001 0100
        header.setFlag(f);
        header.setDateLen(size);
        header.setRequestId(requestId);
        return header;
    }
}

class ServerDecode extends ByteToMessageDecoder {
    //父类里一定有channelRead -> byteBuf
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf buf, List<Object> out) throws Exception {
//        System.out.println("可读:" + buf.readableBytes());
        System.out.println("开始:" + buf.readableBytes());
        while (buf.readableBytes() >= 94) {
            byte[] bytes = new byte[94];
            buf.getBytes(buf.readerIndex(), bytes); //从哪里读取，都多少, 但是readIndex不变
            ByteArrayInputStream in = new ByteArrayInputStream(bytes);
            ObjectInputStream oin = new ObjectInputStream(in);
            MyHeader header = (MyHeader) oin.readObject();

            if (buf.readableBytes() >= header.getDateLen()) {
                //处理指针
                buf.readBytes(94); //移动指针到body开始的位置
                byte[] data = new byte[(int) header.getDateLen()];
                buf.readBytes(data);
                ByteArrayInputStream din = new ByteArrayInputStream(data);
                ObjectInputStream doin = new ObjectInputStream(din);

                if (header.getFlag() == 0x14141414) {
                    System.out.println( "0x14:" + header.getFlag());
                    MyContent content = (MyContent) doin.readObject();
                    out.add(new PackMsg(header, content));
                } else if (header.getFlag() == 0x14141424) {
                    System.out.println("0x24:" +header.getFlag());
                    MyContent content = (MyContent) doin.readObject();
                    out.add(new PackMsg(header, content));
                }
            } else {
                break;
            }
        }
    }
}

class ServerRequestHandler extends ChannelInboundHandlerAdapter {
    //provider:
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        PackMsg buf = (PackMsg) msg;
        System.out.println("server headler:" + buf.content.getArgs()[0]);


        String ioThreadName = Thread.currentThread().getName();
        //1,直接在当前方法 处理io和业务和返回
        //2,使用netty自己的eventloop来处理业务及返回
//        ctx.executor().execute(new Runnable() {
        ctx.executor().parent().next().execute(new Runnable() {
            @Override
            public void run() {
                String execThreadName = Thread.currentThread().getName();
                MyContent content = new MyContent();
                String s = "io thread :" + ioThreadName + " exec thread:" + execThreadName + " from args:" + buf.content.getArgs()[0];
                content.setRes(s);
                byte[] contextByte = SerDerUtil.ser(content);
                MyHeader resHeader = new MyHeader();
                resHeader.setRequestId(resHeader.requestId);
                resHeader.setFlag(0x14141424);
                resHeader.setDateLen(contextByte.length);
                byte[] headerByte = SerDerUtil.ser(resHeader);
                ByteBuf byteBuf = PooledByteBufAllocator.DEFAULT.directBuffer(headerByte.length + headerByte.length);
                byteBuf.writeBytes(headerByte);
                byteBuf.writeBytes(contextByte);
//            ctx.writeAndFlush(byteBuf);
            }
        });


    }
}

class ClientFactory {
    NioEventLoopGroup clientWorker;

    int poolSize = 1;

    Random random = new Random();

    private ClientFactory() {
    }

    private static final ClientFactory factory;

    static {
        factory = new ClientFactory();
    }

    public static ClientFactory getFactory() {
        return factory;
    }

    //一个consumer 可以连接很多的provider,每一个provider都有自己的pool  K,V
    ConcurrentHashMap<InetSocketAddress, ClientPool> outBoxes = new ConcurrentHashMap<>();

    public synchronized NioSocketChannel getClient(InetSocketAddress address) {
        ClientPool clientPool = outBoxes.get(address);
        if (clientPool == null) {
            outBoxes.putIfAbsent(address, new ClientPool(poolSize));
            clientPool = outBoxes.get(address);
        }
        int i = random.nextInt(poolSize);
        if (clientPool.clients[i] != null && clientPool.clients[i].isActive()) {
            return clientPool.clients[i];
        }

        synchronized (clientPool.lock[i]) {
            return clientPool.clients[i] = create(address);
        }

    }

    private NioSocketChannel create(InetSocketAddress address) {
        //基于netty的客户端创建方式
        clientWorker = new NioEventLoopGroup(1);
        Bootstrap bs = new Bootstrap();
        ChannelFuture connect = bs.group(clientWorker)
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel nioSocketChannel) throws Exception {
                        ChannelPipeline pipeline = nioSocketChannel.pipeline();
                        pipeline.addLast(new ServerDecode());
                        pipeline.addLast(new ClientResponses()); //解决给谁的？
                    }
                }).connect(address);
        NioSocketChannel client = null;
        try {
            client = (NioSocketChannel) connect.sync().channel();
            return client;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

}

class ResponseMappingCallback {
    static ConcurrentHashMap<Long, CompletableFuture> mapping = new ConcurrentHashMap<>();

    public static void addCallBack(long requestId, CompletableFuture cb) {
        mapping.putIfAbsent(requestId, cb);
    }

    public static void runCallBack(PackMsg msg) {
        System.out.println("执行");
        CompletableFuture cf = mapping.get(msg.header.getRequestId());
        cf.complete(msg.getContent().getRes());
        removeCB(msg.header.getRequestId());
    }

    private static void removeCB(long requestId) {
        mapping.remove(requestId);
    }
}

class ClientResponses extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        System.out.println("ClientResponses");
        PackMsg responsePackMsg = (PackMsg) msg;
        //曾经没考虑返回得事情
        ResponseMappingCallback.runCallBack(responsePackMsg);
    }
}

class ClientPool {
    NioSocketChannel[] clients;
    Object[] lock;

    ClientPool(int size) {
        clients = new NioSocketChannel[size]; //init 连接是空的
        lock = new Object[size]; //锁是可以初始化的
        for (int i = 0; i < lock.length; i++) {
            lock[i] = new Object();
        }
    }
}

class MyHeader implements Serializable {
    /**
     * 通讯上的协议
     * 1.ooxx值
     * 2.uuid: requestId
     * 3.date_len
     */
    int flag; //32bit 可以设置很多信息
    long requestId;
    long dateLen;

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public long getRequestId() {
        return requestId;
    }

    public void setRequestId(long requestId) {
        this.requestId = requestId;
    }

    public long getDateLen() {
        return dateLen;
    }

    public void setDateLen(long dateLen) {
        this.dateLen = dateLen;
    }
}

class MyContent implements Serializable {
    String name;
    String methodName;
    Class<?>[] parameterTypes;
    Object[] args;
    String res;

    public String getRes() {
        return res;
    }

    public void setRes(String res) {
        this.res = res;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public Class<?>[] getParameterTypes() {
        return parameterTypes;
    }

    public void setParameterTypes(Class<?>[] parameterTypes) {
        this.parameterTypes = parameterTypes;
    }

    public Object[] getArgs() {
        return args;
    }

    public void setArgs(Object[] args) {
        this.args = args;
    }
}

interface Car {
    String msg(String str);
}

interface Fly {
    void msg(String str);
}