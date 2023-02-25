package com.module.algorithm.basicSort;

public class QuickSort {

    public static void main(String[] args) {
//        int[] arr = {3, 6, 7, 9, 8};
//        int[] arr = {3, 6, 7, 2, 9, 1, 8, 5, 7, 6};
        int[] arr = {3, 5, 2, 1, 6, 6, 9, 7, 7, 8};
        quickSort(arr);
        System.out.println("-------------------排序完成-------------------");
        for (int i : arr) {
            System.out.print(" " + i);
        }
    }


    public static void quickSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        process(arr, 0, arr.length - 1);
    }

    public static void process(int[] arr, int start, int end) {
        if (start >= end) {
            return;
        }
        int[] equalsRegion = partition(arr, start, end);
        //等于区第一个数：equalsRegion[0],  等于区最后一个数：equalsRegion[1]
        //左半区
        process(arr, start, equalsRegion[0] - 1);
        //右半区
        process(arr, equalsRegion[1] + 1, end);

    }

    public static int[] partition(int[] arr, int start, int end) {
        int lessRegionIndex = start - 1;
        int greaterRegionIndex = end;
        int currentValueIndex = start;
        while (currentValueIndex < greaterRegionIndex) {
            //如果当前值小于分区值，换值。小区区+1，当前值向前推一位，
            if (arr[currentValueIndex] < arr[end]) {
                swap(arr, ++lessRegionIndex, currentValueIndex++);
            } else if (arr[currentValueIndex] > arr[end]) {
                //如果当前值大于分区值，换值，大于区向前推一位，当前值不变
                swap(arr, --greaterRegionIndex, currentValueIndex);
            } else {
                //如果当前值 = 分区值，当前值向前推一位
                currentValueIndex++;
            }
        }
        swap(arr, greaterRegionIndex, end);
        return new int[]{lessRegionIndex + 1, greaterRegionIndex};
    }

    public static void swap(int[] arr, int i, int j) {
        int temp;
        temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
