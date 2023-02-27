package com.module.advancedAlgorithm.sort;

import java.util.Arrays;

/**
 * 插入排序
 * 0 ~ 0位置上有序
 * 0 ~ 1位置上有序
 * 0 ~ 2位置上有序
 * ......
 * 0 ~ n-1位置上有序
 */
public class InsertSort {

    public static void main(String[] args) {
        int[] arr = {1, 3, 2, 4, 8, 2, 8, 0, 4, 7, 6, 8, 9, 2, 5};
        insertSort(arr);
        Arrays.stream(arr).forEach(num -> System.out.print(" " + num));
    }

    public static void insertSort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int temp = arr[i];
            for (int j = i - 1; j >= 0; j--) {
                if (temp < arr[j]) {
                    ArrayUtils.swap(arr, j, j + 1);
                }
            }
        }
    }

}
