package com.module.advancedAlgorithm.sort.HeapSort;

import com.module.advancedAlgorithm.sort.ArrayUtils;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * 已知一个几乎有序的数组。几乎有序是指，如果吧数组排好顺序的话，每个元素移动的距离一定不超过k，并且k相对于数组长度来说是比较小的
 * 请选择一个合适的排序策略，对这个数组进行排序
 */
public class HeapSort02 {

    public static class MyHeap {
        public int[] heap;
        public int limit;
        public int heapSize;

        public MyHeap(int limit) {
            this.heap = new int[limit];
            this.limit = limit;
            this.heapSize = 0;
        }

        public void add(int value) {
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
            while (curIndex > 0 && heap[curIndex] < heap[parentIndex]) {
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
                int minIndex = leftChild + 1 < heapSize && heap[leftChild] > heap[leftChild + 1] ? leftChild + 1 : leftChild;
                //找到较大值的索引
                int targetIndex = heap[curIndex] < heap[minIndex] ? curIndex : minIndex;
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

        private static void sort(int[] arr, int k) {
        if (k == 0) {
            return;
        }
        MyHeap heap = new MyHeap(arr.length);
        int index = 0;
        int min = Math.min(arr.length , k );
        for (int i = 0; i < min; i++) {
            index++;
            heap.add(arr[i]);
        }
        int sortIndex = 0;
        for (int i = index; i < arr.length; i++) {
            heap.add(arr[i]);
            arr[sortIndex++] = heap.poll();
        }
        while (heap.heapSize > 0){
            arr[sortIndex++] = heap.poll();
        }
//            while (!heap.isEmpty()) {
//                arr[sortIndex++] = heap.poll();
//            }
    }
//    private static void sort(int[] arr, int k) {
//        if (k == 0) {
//            return;
//        }
//        // 默认小根堆
//        PriorityQueue<Integer> heap = new PriorityQueue<>();
//        int index = 0;
//        // 0...K-1
//        for (; index <= Math.min(arr.length - 1, k - 1); index++) {
//            heap.add(arr[index]);
//        }
//        int i = 0;
//        for (; index < arr.length; i++, index++) {
//            heap.add(arr[index]);
//            arr[i] = heap.poll();
//        }
////        while (heap.heapSize> 0) {
////            arr[i++] = heap.poll();
////        }
//        while (!heap.isEmpty()) {
//            arr[i++] = heap.poll();
//        }
//    }


    // for test
    public static void comparator(int[] arr, int k) {
        Arrays.sort(arr);
    }

    // for test
    public static int[] randomArrayNoMoveMoreK(int maxSize, int maxValue, int K) {
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) ((maxValue + 1) * Math.random()) - (int) (maxValue * Math.random());
        }
        // 先排个序
        Arrays.sort(arr);
        // 然后开始随意交换，但是保证每个数距离不超过K
        // swap[i] == true, 表示i位置已经参与过交换
        // swap[i] == false, 表示i位置没有参与过交换
        boolean[] isSwap = new boolean[arr.length];
        for (int i = 0; i < arr.length; i++) {
            int j = Math.min(i + (int) (Math.random() * (K + 1)), arr.length - 1);
            if (!isSwap[i] && !isSwap[j]) {
                isSwap[i] = true;
                isSwap[j] = true;
                int tmp = arr[i];
                arr[i] = arr[j];
                arr[j] = tmp;
            }
        }
        return arr;
    }

    // for test
    public static int[] copyArray(int[] arr) {
        if (arr == null) {
            return null;
        }
        int[] res = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            res[i] = arr[i];
        }
        return res;
    }

    // for test
    public static boolean isEqual(int[] arr1, int[] arr2) {
        if ((arr1 == null && arr2 != null) || (arr1 != null && arr2 == null)) {
            return false;
        }
        if (arr1 == null && arr2 == null) {
            return true;
        }
        if (arr1.length != arr2.length) {
            return false;
        }
        for (int i = 0; i < arr1.length; i++) {
            if (arr1[i] != arr2[i]) {
                return false;
            }
        }
        return true;
    }

    // for test
    public static void printArray(int[] arr) {
        if (arr == null) {
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    // for test
    public static void main(String[] args) {
        System.out.println("test begin");
        int testTime = 500000;
        int maxSize = 100;
        int maxValue = 100;
        boolean succeed = true;
        for (int i = 0; i < testTime; i++) {
            System.out.println(i);
            int k = (int) (Math.random() * maxSize) + 1;
            int[] arr = randomArrayNoMoveMoreK(maxSize, maxValue, k);
            int[] arr1 = copyArray(arr);
            int[] arr2 = copyArray(arr);
            sort(arr1, k);
            comparator(arr2, k);
            if (!isEqual(arr1, arr2)) {
                succeed = false;
                System.out.println("K : " + k);
                printArray(arr);
                printArray(arr1);
                printArray(arr2);
                break;
            }
        }
        System.out.println(succeed ? "Nice!" : "Fucking fucked!");


//        int[] arr = {-46, -46, -34, -22, -27, -20, -18, 0, 8, 6, 13, 12, 14, 13, 22, 21, 37, 29, 45, 45, 53, 49, 69, 65};
//        MyHeap heap = new MyHeap(arr.length);
//        for (int i : arr) {
//            heap.push(i);
//        }
//        while (heap.heapSize > 0) {
//            System.out.println(heap.poll());
//        }
    }
}
