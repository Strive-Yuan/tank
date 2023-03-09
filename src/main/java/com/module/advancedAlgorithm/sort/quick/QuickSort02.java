package com.module.advancedAlgorithm.sort.quick;

import java.util.Arrays;

/**
 * 快速排序
 */
public class QuickSort02 {
    public static void main(String[] args) {
        int[] arr = {1, 82, 35, 46, 5, 6, 19, -50, 30};
        quickSort(arr);
        Arrays.stream(arr).forEach(num -> System.out.print(" " + num));
    }

    public static void quickSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        process(arr, 0, arr.length - 1);
    }

    private static void process(int[] arr, int start, int end) {
        if (start >= end) {
            return;
        }
        int[] partition = partition(arr, start, end);
        process(arr, start, partition[0] - 1);
        process(arr, partition[1] + 1, end);
    }

    private static int[] partition(int[] arr, int start, int end) {
        if (start > end) {
            return new int[] { -1, -1 };
        }
        if (start == end) {
            return new int[] { start, end };
        }
        int leftGroupIndex = start - 1;
        int targetValue = arr[end];
        int rightGroupIndex = end;
        int curIndex = start;
        while (curIndex < rightGroupIndex) {
            if (arr[curIndex] < targetValue) {
                swap(arr, curIndex++, ++leftGroupIndex);
            } else if (arr[curIndex] == targetValue) {
                curIndex++;
            } else if (arr[curIndex] > targetValue) {
                swap(arr, curIndex, --rightGroupIndex);
            }
        }
        swap(arr, rightGroupIndex, end);
        return new int[]{leftGroupIndex + 1, rightGroupIndex};
    }

    private static void swap(int[] arr, int num1, int num2) {
        int temp = arr[num1];
        arr[num1] = arr[num2];
        arr[num2] = temp;
    }
}
