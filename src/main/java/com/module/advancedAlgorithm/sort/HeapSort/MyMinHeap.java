package com.module.advancedAlgorithm.sort.HeapSort;

import com.module.advancedAlgorithm.sort.ArrayUtils;

import java.util.Arrays;

public class MyMinHeap {
    public final int[] heap;
    public final int limit;
    public int heapSize;

    public MyMinHeap(int limit) {
        this.heap = new int[limit];
        this.limit = limit;
        this.heapSize = 0;
    }

    public void push(int value) {
        if (heapSize == limit) {
            throw new RuntimeException("已经满了,快上一边去!");
        }
        heapInsert(value);

    }

    public void heapInsert(int value) {
        heap[heapSize] = value;
        int curIndex = heapSize;
        int parentIndex = (heapSize - 1) / 2;
        while (heap[curIndex] < heap[parentIndex]) {
            ArrayUtils.swap(heap, curIndex, parentIndex);
            curIndex = parentIndex;
            parentIndex = (curIndex - 1) / 2;
        }
        heapSize++;
    }

    public static void main(String[] args) {
        System.out.println((-1) / 2);
        System.out.println((-1) >>> 1);
        System.out.println((-1) >> 1);
        MyMinHeap myMinHeap = new MyMinHeap(6);
        int[] arr = {56, 27, -94, 37, 26, -11};
        for (int i : arr) {
            myMinHeap.push(i);
        }
        System.out.println("----------------小根堆----------------");
        Arrays.stream(myMinHeap.heap).forEach(num -> System.out.print(" " + num));
        System.out.println();

        System.out.println("-------------- " + "取出:" + myMinHeap.poll() + "----长度:" + myMinHeap.heapSize + "------------------");
        Arrays.stream(myMinHeap.heap).forEach(num -> System.out.print(" " + num));
        System.out.println();
        System.out.println("-------------- " + "取出:" + myMinHeap.poll() + "----长度:" + myMinHeap.heapSize + "------------------");
        Arrays.stream(myMinHeap.heap).forEach(num -> System.out.print(" " + num));
        System.out.println();
    }

    public void heapIfy() {
        int curIndex = 0;
        int tempHeapSize = heapSize;
        //左子树
        int leftTree = 1;
        while (curIndex < tempHeapSize && (heap[curIndex] > heap[leftTree] || heap[curIndex] > heap[leftTree + 1])) {
            if (leftTree + 1 > heapSize) {
                break;
            }
            int minChildIndex = heap[leftTree] < heap[leftTree + 1] ? leftTree : leftTree + 1;
            int targetIndex = heap[curIndex] < heap[minChildIndex] ? curIndex : minChildIndex;
            if (targetIndex == curIndex) {
                break;
            }
            ArrayUtils.swap(heap, curIndex, targetIndex);
            curIndex = targetIndex;
            leftTree = 2 * curIndex + 1;
            tempHeapSize--;
        }
    }

    public int poll() {
        if (heapSize < 0) {
            throw new RuntimeException("已经没了,快上一边去!");
        }
        int temp = heap[0];
        ArrayUtils.swap(heap, 0, --heapSize);
        heapIfy();
        return temp;
    }
}
