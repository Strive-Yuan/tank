package testClone;

import jdk.nashorn.internal.ir.CallNode;

import java.util.concurrent.Exchanger;
import java.util.concurrent.Phaser;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class MyTest {
    public static void main(String[] args) {
        ReentrantLock reentrantLock = new ReentrantLock();
        Phaser phaser = new Phaser();
        ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();
        readWriteLock.readLock();
        Semaphore semaphore = new Semaphore(1,true);
        Exchanger<String> exchanger = new Exchanger<>();
        System.out.println(1<<16);
        Thread t1 = new Thread(() -> System.out.println(111));
        LockSupport.park();
    }
}
