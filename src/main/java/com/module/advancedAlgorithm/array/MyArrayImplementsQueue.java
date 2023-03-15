package com.module.advancedAlgorithm.array;

/**
 * 数组实现栈,数组实现队列
 */
public class MyArrayImplementsQueue {
    int[] arr;
    int headIndex;
    int tailIndex;
    int size;
    int limit;

    public MyArrayImplementsQueue(int limit) {
        this.arr = new int[limit];
        this.headIndex = 0;
        this.tailIndex = 0;
        this.size = 0;
        this.limit = limit;
    }

    public void push(Integer value) {
        if (size == limit) {
            throw new RuntimeException("队列满了，不能再放了");
        }
        size++;
        arr[headIndex] = value;
        headIndex = nextIndex(headIndex);
    }

    private int nextIndex(int index) {
        return index >= limit-1 ? 0 : index + 1;
    }

    public int poll() {
        if (size <= 0) {
            throw new RuntimeException("队列空了，不能再拿了");
        }
        size--;
        int ans = arr[tailIndex];
        tailIndex = nextIndex(tailIndex);
        return ans;
    }


    public static void main(String[] args) {
        MyArrayImplementsQueue queue = new MyArrayImplementsQueue(5);
        queue.push(2);
        queue.push(5);
        queue.push(9);
        queue.push(4);
        queue.push(7);
        System.out.println(queue.poll());
        queue.push(33);
    }
}
