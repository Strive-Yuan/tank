package com.module.advancedAlgorithm.linked;

import java.util.Stack;

/**
 * 实现一个特殊的栈，在实现返回栈中最小元素
 */
public class MyStack01 {
    public Stack<Integer> stack = new Stack<>();
    public Stack<Integer> minStack = new Stack<>();

    public void push(Integer value) {
        if (minStack.isEmpty()) {
            minStack.push(value);
        } else {
            Integer min = getMin();
            if (value <= min) {
                minStack.push(value);
            } else {
                minStack.push(min);
            }
        }
        stack.push(value);
    }

    public Integer poll() {
        if (stack.isEmpty()) {
            throw new RuntimeException("没有,滚！");
        }
        minStack.pop();
        return stack.pop();
    }

    private Integer getMin() {
        if (minStack.isEmpty()) {
            throw new RuntimeException("没有,滚！");
        }
        return minStack.peek();
    }

    public static void main(String[] args) {
        MyStack01 stack01 = new MyStack01();
        stack01.push(5);
        stack01.push(7);
        stack01.push(4);
        stack01.push(6);
        System.out.println("当前最小值：" + stack01.getMin() + "   " + "当前弹出值：" + stack01.poll());
        System.out.println("当前最小值：" + stack01.getMin() + "   " + "当前弹出值：" + stack01.poll());
        System.out.println("当前最小值：" + stack01.getMin() + "   " + "当前弹出值：" + stack01.poll());
        System.out.println("当前最小值：" + stack01.getMin() + "   " + "当前弹出值：" + stack01.poll());
    }

}
