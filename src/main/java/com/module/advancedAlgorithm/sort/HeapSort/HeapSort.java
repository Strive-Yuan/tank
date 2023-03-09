package com.module.advancedAlgorithm.sort.HeapSort;

import com.module.advancedAlgorithm.sort.ArrayUtils;

import java.util.Arrays;

/**
 * 堆排序
 */
public class HeapSort {
    public static void main(String[] args) {
        MyMaxHeap myMaxHeap = new MyMaxHeap(1);
        int[] arr = {56, 27, -94, 37, 26, -11};
        myMaxHeap.heapSort(arr);
        System.out.println("----------------排序后----------------");
        Arrays.stream(arr).forEach(num -> System.out.print(" " + num));
        System.out.println();
        int testTime = 500000;
        int maxSize = 100;
        int maxValue = 100;
        boolean succeed = true;
        for (int i = 0; i < testTime; i++) {
            int[] arr1 = generateRandomArray(maxSize, maxValue);
            int[] arr2 = copyArray(arr1);
//            System.out.println("----------------原数组----------------");
//            Arrays.stream(arr1).forEach(num -> System.out.print(" " + num));
//            System.out.println();
            myMaxHeap.heapSort(arr1);
//            System.out.println("----------------堆排后----------------");
//            Arrays.stream(arr1).forEach(num -> System.out.print(" " + num));
//            System.out.println();
            comparator(arr2);
//            System.out.println("----------------排序后----------------");
//            Arrays.stream(arr2).forEach(num -> System.out.print(" " + num));
//            System.out.println();
            if (!isEqual(arr1, arr2)) {
                succeed = false;
                break;
            }
        }
        System.out.println(succeed ? "Nice!" : "Fucking fucked!");

    }



    // for test
    public static void comparator(int[] arr) {
        Arrays.parallelSort(arr);
    }

    // for test
    public static int[] generateRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) ((maxValue + 1) * Math.random()) - (int) (maxValue * Math.random());
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

}
