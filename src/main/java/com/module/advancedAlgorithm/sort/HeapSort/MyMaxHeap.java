package com.module.advancedAlgorithm.sort.HeapSort;

import com.module.advancedAlgorithm.sort.ArrayUtils;

import java.util.Arrays;
import java.util.PriorityQueue;

public class MyMaxHeap {
    public final int[] heap;
    public final int limit;
    public int heapSize;

    public MyMaxHeap(int limit) {
        heap = new int[limit];
        this.limit = limit;
        heapSize = 0;
    }

    public void push(int value) {
        if (limit == heapSize) {
            throw new RuntimeException("堆满了");
        }
        heapInsert(value);
    }


    public int poll() {
        if (heapSize < 0) {
            throw new RuntimeException("没有,滚");
        }
        int temp = heap[0];
        ArrayUtils.swap(heap, 0, --heapSize);
//        System.out.println("交换:" + 0 + "   " + heapSize);
        heapIfy(heap, 0, heapSize);
        return temp;
    }

    public void heapInsert(int value) {
        heap[heapSize] = value;
        int parentIndex = (heapSize - 1) / 2;
        int curIndex = heapSize;
        while (heap[parentIndex] < heap[curIndex]) {
            ArrayUtils.swap(heap, parentIndex, curIndex);
            curIndex = parentIndex;
            parentIndex = (curIndex - 1) / 2;
        }
        ++heapSize;
    }

    public void heapIfy(int[] arr, int index, int size) {
        int tempHeapSize = size;
        //当当前位置为不为0，或当前值小于左孩子 或当前值小于右孩子时继续，否则停止
        while (index <= tempHeapSize) {
            int leftChild = (index << 1) + 1;
            int rightChild = leftChild + 1;
            int maxChildIndex;
            if (rightChild < tempHeapSize) {
                maxChildIndex = arr[leftChild] < arr[rightChild] ? rightChild : leftChild;
            } else {
                maxChildIndex = leftChild;
            }
            if (maxChildIndex >= tempHeapSize) {
                break;
            }
            if (arr[index] < arr[maxChildIndex]) {
                ArrayUtils.swap(arr, index, maxChildIndex);
                index = maxChildIndex;
                --tempHeapSize;
            } else {
                break;
            }
        }
    }

    public void heapSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        for (int i = arr.length - 1; i >= 0; i--) {
            heapIfy(arr, i, arr.length);
        }
        int size = arr.length;
        ArrayUtils.swap(arr, 0, --size);
        while (size > 0) {
            heapIfy(arr, 0, size);
            ArrayUtils.swap(arr, 0, --size);
        }
    }
}
