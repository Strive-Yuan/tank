package com.module.advancedAlgorithm.linked;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * 两个队列实现栈
 */
public class DoubleQueueImplementsStack {
    Queue<Integer> queue = new LinkedList<>();
    Queue<Integer> helpQueue = new LinkedList<>();

    public void push(int value) {
        queue.offer(value);
    }

    public int poll() {
        while (queue.size() > 1) {
            helpQueue.offer(queue.poll());
        }
        if (queue.isEmpty()) {
            throw new RuntimeException("没有,滚！");
        }
        int ans = queue.poll();
        Queue<Integer> temp;
        temp = queue;
        queue = helpQueue;
        helpQueue = temp;
        return ans;
    }

    public boolean isEmpty() {
        return queue.isEmpty();
    }

    public static void main(String[] args) {
        System.out.println("test begin");
        DoubleQueueImplementsStack myStack = new DoubleQueueImplementsStack();
        Stack<Integer> test = new Stack<>();
        int testTime = 1000000;
        int max = 1000000;
        for (int i = 0; i < testTime; i++) {
            if (myStack.isEmpty()) {
                if (!test.isEmpty()) {
                    System.out.println("Oops");
                }
                int num = (int) (Math.random() * max);
                myStack.push(num);
                test.push(num);
            } else {
                if (Math.random() < 0.25) {
                    int num = (int) (Math.random() * max);
                    myStack.push(num);
                    test.push(num);
                } else if (Math.random() < 0.5) {
//                    if (!myStack.peek().equals(test.peek())) {
//                        System.out.println("Oops");
//                    }
                } else if (Math.random() < 0.75) {
                    if (myStack.poll() != test.pop()) {
                        System.out.println("Oops");
                    }
                } else {
                    if (myStack.isEmpty() != test.isEmpty()) {
                        System.out.println("Oops");
                    }
                }
            }
        }

        System.out.println("test finish!");
    }
}
