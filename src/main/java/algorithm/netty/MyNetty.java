package algorithm.netty;


import io.netty.bootstrap.Bootstrap;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.buffer.UnpooledByteBufAllocator;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.ServerSocketChannel;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import org.junit.Test;

import java.io.IOException;
import java.net.InetSocketAddress;


public class MyNetty {

    @Test
    public void myByteBuffer() {
//        ByteBuf buffer = ByteBufAllocator.DEFAULT.buffer(8, 20);
//        ByteBuf buffer = UnpooledByteBufAllocator.DEFAULT.heapBuffer(8, 20);
        ByteBuf buffer = UnpooledByteBufAllocator.DEFAULT.directBuffer(8, 20);
        print(buffer);
        buffer.writeBytes(new byte[]{1, 2, 3, 4});
        print(buffer);
        buffer.writeBytes(new byte[]{1, 2, 3, 4});
        print(buffer);
        buffer.writeBytes(new byte[]{1, 2, 3, 4});
        print(buffer);
        buffer.writeBytes(new byte[]{1, 2, 3, 4});
        print(buffer);
        buffer.writeBytes(new byte[]{1, 2, 3, 4});
        print(buffer);
    }

    private static void print(ByteBuf buffer) {
        System.out.println("buffer.isReadable() :" + buffer.isReadable());
        System.out.println("buffer.readerIndex() :" + buffer.readerIndex()); //从哪开始读
        System.out.println("buffer.readableBytes() :" + buffer.readableBytes());//读多少字节出来

        System.out.println("buffer.isWritable() :" + buffer.isWritable());
        System.out.println("buffer.writerIndex() :" + buffer.writerIndex());
        System.out.println("buffer.writableBytes() :" + buffer.writableBytes());

        System.out.println("buffer.capacity() :" + buffer.capacity());
        System.out.println("buffer.maxCapacity() :" + buffer.maxCapacity());

        System.out.println("buffer.isDirect() :" + buffer.isDirect()); //是否是堆外内存   true就是对堆外

        System.out.println("-------------------------------");
    }


    /**
     * 客户端
     * 连接别人
     * 1.可以主动发送数据
     * 2.别人什么时候给我发？    even  selector
     */
    @Test
    public void loopExecute() throws IOException {
        //group  线程池
        NioEventLoopGroup selector = new NioEventLoopGroup(1);
        selector.execute(() -> {
            try {
                for (; ; ) {
                    System.out.println("001 hello word!!!");
                    Thread.sleep(1000);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        selector.execute(() -> {
            try {
                for (; ; ) {
                    System.out.println("002 hello word!!!");
                    Thread.sleep(1000);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        System.in.read();
    }


    @Test
    public void clientMode() throws Exception {
        //group  线程池
        NioEventLoopGroup selector = new NioEventLoopGroup(1);
        //客户端模式：
        NioSocketChannel clint = new NioSocketChannel();

        selector.register(clint); //epoll_ctl(5,ADD,3)

        //reactor 异步得特征
        ChannelFuture connect = clint.connect(new InetSocketAddress("192.168.80.132", 9090));
        //等待连接成功
        ChannelFuture sync = connect.sync();

        ByteBuf byteBuf = Unpooled.copiedBuffer("hello server".getBytes());
        ChannelFuture send = clint.writeAndFlush(byteBuf);
        //发送数据也是异步的
        send.sync();

        ChannelPipeline p = clint.pipeline();
        p.addLast(new MyInHandler());

        sync.channel().closeFuture().sync();
        System.out.println("client over ...");

    }

    @Test
    public void serverMode() throws Exception {
        NioEventLoopGroup thread = new NioEventLoopGroup(1);
        NioServerSocketChannel server = new NioServerSocketChannel();
        thread.register(server);
        ChannelFuture bind = server.bind(new InetSocketAddress("192.168.1.131", 9090));
        ChannelPipeline pipeline = server.pipeline();
        pipeline.addLast(new MyAcceptHandler(thread, new ChannelInit())); //accecpt 接受客户端 并且注册register
//        pipeline.addLast(new MyAcceptHandler(thread,new MyInHandler())); //accecpt 接受客户端 并且注册register
        bind.sync().channel().closeFuture().sync();
        System.out.println("server close....");
    }


    @Test
    public void nettyClient() throws InterruptedException {
        NioEventLoopGroup group = new NioEventLoopGroup(1);
        Bootstrap bs = new Bootstrap();
        ChannelFuture connect = bs.group(group)
                .channel(NioSocketChannel.class)
//                .handler(new ChannelInit())
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel socketChannel) {
                        ChannelPipeline pipeline = socketChannel.pipeline();
                        pipeline.addLast(new MyInHandler());
                    }
                })
                .connect(new InetSocketAddress("192.168.80.132", 9090));
        Channel client = connect.sync().channel();
        ByteBuf byteBuf = Unpooled.copiedBuffer("hello server".getBytes());
        ChannelFuture send = client.writeAndFlush(byteBuf);
        //发送数据也是异步的
        send.sync();
        client.closeFuture().sync();
    }

    @Test
    public void nettyServer() throws InterruptedException {
        NioEventLoopGroup group = new NioEventLoopGroup(1);
        ServerBootstrap bs = new ServerBootstrap();
        ChannelFuture bind = bs.group(group, group)
                .channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel socketChannel) throws Exception {
                        ChannelPipeline pipeline = socketChannel.pipeline();
                        pipeline.addLast(new MyInHandler());
                    }
                })
                .bind(new InetSocketAddress("192.168.1.131", 9090));
        bind.sync().channel().closeFuture().sync();
    }
}