package com.module.advancedAlgorithm.sort.quick;

/**
 * 快速排序
 * 荷兰国旗问题
 * arr[L...R]玩荷兰国旗的划分，以arr[R]做划分值
 * [<arr[R], ==arr[R], >arr[R]]
 */
public class QuickSort01 {
    public static void QuickSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        process(arr, arr.length - 1);
    }

    private static void process(int[] arr, int i) {

    }
}
