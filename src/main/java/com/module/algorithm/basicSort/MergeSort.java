package com.module.algorithm.basicSort;

import java.util.Arrays;

/**
 * 归并排序
 */
public class MergeSort {

    public static void main(String[] args) {
        int[] arr = {12, 85, 3, 8, 2, 6, 7, 4, 48, 4, 61, 6, 8468, 14, 68, 498, 684};
        mergeSort(arr);
        Arrays.stream(arr).forEach(System.out::println);
    }

    private static void mergeSort(int[] arr) {


    }
}
