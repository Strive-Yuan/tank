package testClone.test;

import java.util.concurrent.*;

public class ThreadPoolTest {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        MyThreadPool executor = new MyThreadPool(2);
        for (int i = 0; i < 20; i++) {
            executor.execute(new Task());
        }
    }

    static class Task  implements Runnable {
        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + "线程执行我分配的任务!");
        }
    }
}
