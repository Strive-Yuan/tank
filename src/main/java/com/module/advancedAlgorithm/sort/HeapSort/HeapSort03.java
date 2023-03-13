package com.module.advancedAlgorithm.sort.HeapSort;

import com.module.advancedAlgorithm.sort.ArrayUtils;

import java.util.Arrays;

/**
 * 最大限度an重合问题(用堆的实现)
 * 给定很多线段，每个线段都有两个数组[start,end]
 * 表示线段开始位置和结束位置，左右都是闭区间
 * 规定:
 * 1）线段的开始和结束位置一定都是整数值
 * 2）线段重合区域的长度必须>=1
 * 返回线段最多重合区域中，包含了几条线段
 */
public class HeapSort03 {
    public static class MyHeap {
        public int[] heap;
        public int limit;
        public int heapSize;

        public MyHeap(int limit) {
            this.heap = new int[limit];
            this.limit = limit;
            this.heapSize = 0;
        }

        public void push(int value) {
            if (heapSize == limit) {
                System.out.println("满了，扔了");
                return;
            }
            //将元素放入数组
            heap[heapSize++] = value;
            //调整堆
            heapInsert();
        }

        private void heapInsert() {
            //当前位置
            int curIndex = heapSize - 1;
            int parentIndex = (curIndex - 1) / 2;
            while (curIndex > 0 && heap[curIndex] > heap[parentIndex]) {
                ArrayUtils.swap(heap, curIndex, parentIndex);
                curIndex = parentIndex;
                parentIndex = (curIndex - 1) / 2;
            }
        }

        public int poll() {
            if (heapSize <= 0) {
                throw new RuntimeException("空了，无了");
            }
            int temp = heap[0];
            ArrayUtils.swap(heap, 0, --heapSize);
            heapIfy();
            return temp;
        }

        private void heapIfy() {
            int curIndex = 0;
            int leftChild = 1;
            while (leftChild < heapSize) { //右子树可能有可能没有
                //如果存在右子树，并且右子树的值大则返回右子树索引，否则返回左子树
                int maxIndex = leftChild + 1 < heapSize && heap[leftChild] < heap[leftChild + 1] ? leftChild + 1 : leftChild;
                //找到较大值的索引
                int targetIndex = heap[curIndex] > heap[maxIndex] ? curIndex : maxIndex;
                //如果是自己则不动
                if (curIndex == targetIndex) {
                    return;
                }
                ArrayUtils.swap(heap, curIndex, targetIndex);
                curIndex = targetIndex;
                leftChild = curIndex * 2 + 1;
            }
        }

    }

    public static void main(String[] args) {
        MyHeap myHeap = new MyHeap(6);
        myHeap.push(2);
        myHeap.push(6);
        myHeap.push(3);
        myHeap.push(9);
        myHeap.push(13);
        System.out.println("----------------堆排后----------------");
        Arrays.stream(myHeap.heap).forEach(num -> System.out.print(" " + num));
        System.out.println();

        while (myHeap.heapSize > 0) {
            System.out.println(myHeap.poll());
        }
    }
}
