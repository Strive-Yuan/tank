package com.module.advancedAlgorithm.basicDataStructure;

import java.util.Stack;

/**
 * 两个栈实现队列
 */
public class DoubleStackImplementsQueue {
    public Stack<Integer> pushStack = new Stack<>();
    public Stack<Integer> popStack = new Stack<>();

    public void push(int value) {
        pushStack.push(value);
        pushToPop();
    }

    public int pop() {
        if (popStack.isEmpty() && pushStack.isEmpty()) {
            throw new RuntimeException("没有,滚！");
        }
        pushToPop();
        return popStack.pop();

    }

    private void pushToPop() {
        if (popStack.isEmpty()) {
            while (!pushStack.isEmpty()) {
                popStack.push(pushStack.pop());
            }
        }
    }

    public static void main(String[] args) {
        DoubleStackImplementsQueue doubleStackImplementsQueue = new DoubleStackImplementsQueue();
        doubleStackImplementsQueue.push(1);
        doubleStackImplementsQueue.push(5);
        doubleStackImplementsQueue.push(3);
        System.out.println(doubleStackImplementsQueue.pop());
        doubleStackImplementsQueue.push(7);
        System.out.println(doubleStackImplementsQueue.pop());
        doubleStackImplementsQueue.push(6);
        System.out.println(doubleStackImplementsQueue.pop());
    }
}
