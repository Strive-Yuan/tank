package com.module.io;

import java.nio.ByteBuffer;

public class ByteBufferTest {

    public static void main(String[] args) {
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        System.out.println("-----------------------------------------------------");
        System.out.println("position:" + byteBuffer.position());
        System.out.println("limit:" + byteBuffer.limit());
        System.out.println("capacity:" + byteBuffer.capacity());
        System.out.println("byteBuffer:" + byteBuffer);
        byteBuffer.put("123".getBytes());
        System.out.println("---------------------put:123-------------------------");
        System.out.println("byteBuffer:" + byteBuffer);

        System.out.println("-----------------------get--------------------------");
        byteBuffer.get();
        System.out.println("byteBuffer:" + byteBuffer);

        System.out.println("-----------------------flip--------------------------");
        byteBuffer.flip();
        System.out.println("byteBuffer:" + byteBuffer);

        System.out.println("-----------------------get--------------------------");
        byteBuffer.get();
        System.out.println("byteBuffer:" + byteBuffer);


        System.out.println("---------------------compact------------------------");
        byteBuffer.compact();
        System.out.println("byteBuffer:" + byteBuffer);

        byteBuffer.put("456".getBytes());
        System.out.println("---------------------put:456-------------------------");
        System.out.println("byteBuffer:" + byteBuffer);


        System.out.println("---------------------clear  ------------------------");
        byteBuffer.clear();
        System.out.println("byteBuffer:" + byteBuffer);

    }
}
