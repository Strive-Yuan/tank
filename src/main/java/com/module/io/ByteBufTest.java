package com.module.io;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.buffer.UnpooledByteBufAllocator;

public class ByteBufTest {
    public static void main(String[] args) {
//        ByteBuf buf = ByteBufAllocator.DEFAULT.buffer(8, 20);
//        ByteBuf buf = UnpooledByteBufAllocator.DEFAULT.buffer(8,20);
        ByteBuf buf = PooledByteBufAllocator.DEFAULT.buffer(8,20);
        print(buf);
        buf.writeBytes(new byte[]{1,2,3,4});
        print(buf);
        buf.writeBytes(new byte[]{1,2,3,4});
        print(buf);
        buf.writeBytes(new byte[]{1,2,3,4});
        print(buf);
        buf.writeBytes(new byte[]{1,2,3,4});
        print(buf);
        buf.writeBytes(new byte[]{1,2,3,4});
        print(buf);
    }

    private static void print(ByteBuf buf) {
        System.out.println("buf.isReadable():" + buf.isReadable());
        System.out.println("buf.readerIndex():" + buf.readerIndex());
        System.out.println("buf.readableBytes():" + buf.readableBytes());
        System.out.println("buf.isWritable():" + buf.isWritable());
        System.out.println("buf.writerIndex():" + buf.writerIndex());
        System.out.println("buf.writableBytes():" + buf.writableBytes());
        System.out.println("buf.capacity():" + buf.capacity());
        System.out.println("buf.maxCapacity():" + buf.maxCapacity());
        System.out.println("buf.isDirect():" + buf.isDirect());
        System.out.println("----------------------------------------------------");
    }
}
