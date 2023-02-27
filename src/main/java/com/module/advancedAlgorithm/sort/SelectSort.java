package com.module.advancedAlgorithm.sort;

import java.util.Arrays;

/**
 * 选择排序
 * 0 ~ N-1 找到最小值，放在0位置
 * 1 ~ N-1 找到最小值，放在1位置
 * 2 ~ N-1 找到最小值，放在2位置
 * ......
 */
public class SelectSort {
    public static void main(String[] args) {
        int[] arr = {1, 3};
        selectSort(arr);
        Arrays.stream(arr).forEach(num -> System.out.print(" " + num));
    }

    public static void selectSort(int[] arr) {
        if (arr.length <= 1) {
            return;
        }
        //第N个位置
        for (int i = 0; i < arr.length - 1; i++) {
            //寻找n~n-1的最小值
            int minIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                minIndex = arr[minIndex] > arr[j] ? j : minIndex;
            }
            ArrayUtils.swap(arr, i, minIndex);
        }
    }
}
