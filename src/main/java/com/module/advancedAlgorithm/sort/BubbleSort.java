package com.module.advancedAlgorithm.sort;

import java.util.Arrays;

/**
 * 冒泡排序
 * 一个元素一个元素的排序（左右相比，交换位置）
 * <p>
 * arr长度为N,每一步常数操作的数量，依然是等差数列一般
 * 所以，总的常数操作数量=a*(N^2)+B]+b*N+c(a,b,c都是常数)
 * 所以冒泡排序的时间复杂度为 O(N^2)
 */
public class BubbleSort {


    public static void main(String[] args) {
        int[] arr = {1, 3, 2, 4, 8, 2, 8, 0, 4, 7, 6, 8, 9, 2, 5};
        bubbleSort(arr);
        Arrays.stream(arr).forEach(num -> System.out.print(" " + num));
    }

    public static void bubbleSort(int[] arr) {
        if (arr.length <= 1) {
            return;
        }
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] > arr[j]) {
                    ArrayUtils.swap(arr, i, j);
                }
            }
        }
    }
}
