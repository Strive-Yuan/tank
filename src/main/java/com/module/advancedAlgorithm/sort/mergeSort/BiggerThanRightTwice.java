package com.module.advancedAlgorithm.sort.mergeSort;

import java.util.Arrays;

/**
 * 在一个数组中某个数左边的数比他本身乘2还大
 * 本题测试链接 : https://leetcode.com/problems/reverse-pairs/
 */
public class BiggerThanRightTwice {

    public static void main(String[] args) {
        int[] arr = {18, 5, 6, 17, 2};
        System.out.println("BiggerThanRightTwice:" + biggerThanRightTwice(arr) + "个");
        Arrays.stream(arr).forEach(num -> System.out.print(" " + num));

        int testTime = 500000;
        int maxSize = 100;
        int maxValue = 100;
        System.out.println();
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int[] arr1 = generateRandomArray(maxSize, maxValue);
            int[] arr2 = copyArray(arr1);
            if (biggerThanRightTwice(arr1) != comparator(arr2)) {
                System.out.println("Oops!");
                printArray(arr1);
                printArray(arr2);
                break;
            }
        }
        System.out.println("测试结束");

    }

    private static int biggerThanRightTwice(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        return process(arr, 0, arr.length - 1);
    }

    private static int process(int[] arr, int start, int end) {
        if (start == end) {
            return 0;
        }
        int middle = start + ((end - start) >> 1);
        int leftGroupNum = process(arr, start, middle);
        int rightGroupNum = process(arr, middle + 1, end);
        int mergeNum = merge(arr, start, middle, end);
        return leftGroupNum + rightGroupNum + mergeNum;
    }

    private static int merge(int[] arr, int start, int middle, int end) {
        int rightTemp = middle + 1;
        int ans = 0;
        for (int i = start; i <= middle; i++) {
            while (rightTemp <= end && arr[i] <= arr[rightTemp] * 2) {
                rightTemp++;
            }
            ans += (end - rightTemp + 1);
        }
        int[] help = new int[end - start + 1];
        int leftGroupIndex = start;
        int rightGroupIndex = middle + 1;
        int helpIndex = 0;
        while (leftGroupIndex <= middle && rightGroupIndex <= end) {
            help[helpIndex++] = arr[leftGroupIndex] < arr[rightGroupIndex] ? arr[rightGroupIndex++] : arr[leftGroupIndex++];
        }
        while (leftGroupIndex <= middle) {
            help[helpIndex++] = arr[leftGroupIndex++];
        }
        while (rightGroupIndex <= end) {
            help[helpIndex++] = arr[rightGroupIndex++];
        }
        System.arraycopy(help, 0, arr, start, help.length);
        return ans;
    }

    // for test
    public static int comparator(int[] arr) {
        int ans = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] > (arr[j] << 1)) {
                    ans++;
                }
            }
        }
        return ans;
    }

    // for test
    public static int[] generateRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) ((maxValue + 1) * Math.random()) - (int) ((maxValue + 1) * Math.random());
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
