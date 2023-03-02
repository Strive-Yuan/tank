package com.module.advancedAlgorithm.basicDataStructure;

/**
 * 数组实现队列
 */
public class MyArrayImplementsStack {
    int[] arr = new int[5];
    int index = -1;

    public void push(Integer num) {
        if (index >= 4) {
            throw new RuntimeException("栈满了，不能再放了");
        }

        arr[++index] = num;
    }

    public int poll() {
        if (index < 0) {
            throw new RuntimeException("栈空了，不能再拿了");
        }
        return arr[index--];
    }

    public static void main(String[] args) {
        MyArrayImplementsStack stack = new MyArrayImplementsStack();
        stack.push(2);
        stack.push(5);
        stack.push(9);
        stack.push(9);
        stack.push(9);
        System.out.println(stack.poll());
        System.out.println(stack.poll());
        System.out.println(stack.poll());
        System.out.println(stack.poll());
        System.out.println(stack.poll());
    }
}
