package testClone.test;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class MyThreadPool {

    private BlockingQueue<Runnable> queue;
    private Thread[] threads;
    private final AtomicInteger taskCount = new AtomicInteger(0);
    public MyThreadPool(int threadCount) {
        queue = new LinkedBlockingQueue<>();
        threads = new Thread[threadCount];

        for (int i = 0; i < threadCount; i++) {
            threads[i] = new Thread(new Worker());
            threads[i].start();
        }
    }

    public void execute(Runnable task) {
        queue.offer(task);
    }

    public void shutdown() {
        for (Thread thread : threads) {
            thread.interrupt();
        }
    }


    public <T> Future<T> submit(Callable<T> task) {
        FutureTask<T> futureTask = new FutureTask<>(task);
        taskCount.incrementAndGet();
        try {
            queue.put(futureTask);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        return futureTask;
    }


    private class Worker implements Runnable {
        @Override
        public void run() {
            try {
                while (!Thread.currentThread().isInterrupted()) {
                    Runnable task = queue.take();
                    task.run();
                }
            } catch (InterruptedException e) {
                // do nothing
            }
        }
    }
}

