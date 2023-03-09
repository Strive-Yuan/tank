package com.module.advancedAlgorithm.sort.quick;

import com.module.advancedAlgorithm.sort.ArrayUtils;

import java.util.Arrays;

/**
 * 快速排序
 * 荷兰国旗问题
 * arr[L...R]玩荷兰国旗的划分，以arr[R]做划分值
 * [<arr[R], ==arr[R], >arr[R]]
 */
public class QuickSort01 {
    // for test
    public static void main(String[] args) {
        int[] arr = {1, 82, 35, 46, 5, 6, 19, -50, 30};
        quickSort(arr);
        Arrays.stream(arr).forEach(num -> System.out.print(" " + num));
    }

    public static void quickSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        partition(arr, arr[arr.length - 1]);
    }

    private static void partition(int[] arr, int targetValue) {
        int leftGroupIndex = -1;
        int rightGroupIndex = arr.length - 1;
        int curValueIndex = 0;
        while (curValueIndex < rightGroupIndex) {
            if (arr[curValueIndex] < targetValue) {
                curValueIndex++;
                leftGroupIndex++;
            } else if (arr[curValueIndex] == targetValue) {
                curValueIndex++;
            } else if (arr[curValueIndex] > targetValue) {
                swap(arr, curValueIndex, --rightGroupIndex);
            }
        }
        swap(arr, curValueIndex, arr.length - 1);
    }

    private static void swap(int[] arr, int num1, int num2) {
        int temp = arr[num1];
        arr[num1] = arr[num2];
        arr[num2] = temp;
    }
}
