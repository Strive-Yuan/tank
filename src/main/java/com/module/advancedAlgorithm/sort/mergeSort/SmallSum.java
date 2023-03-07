package com.module.advancedAlgorithm.sort.mergeSort;

/**
 * 小和
 * 每个元素左边比它本身小的元素之和 的汇总和
 */
public class SmallSum {

    // for test
    public static void main(String[] args) {
        int testTime = 500000;
        int maxSize = 100;
        int maxValue = 100;
        boolean succeed = true;
        for (int i = 0; i < testTime; i++) {
            int[] arr1 = generateRandomArray(maxSize, maxValue);
            int[] arr2 = copyArray(arr1);
            if (smallSum(arr1) != comparator(arr2)) {
                succeed = false;
                printArray(arr1);
                printArray(arr2);
                break;
            }
        }
        System.out.println(succeed ? "Nice!" : "Fucking fucked!");
    }
    public static int smallSum(int[] arr) {
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
        int leftSmallSum = process(arr, start, middle);
        int rightSmallSum = process(arr, middle + 1, end);
        int merge = merge(arr, start, middle, end);
        return leftSmallSum + rightSmallSum + merge;
    }

    private static int merge(int[] arr, int start, int middle, int end) {
        int smallSum = 0;
        int[] help = new int[end - start + 1];
        int leftStartIndex = start;
        int rightStartIndex = middle + 1;
        int helpIndex = 0;
        while (leftStartIndex <= middle && rightStartIndex <= end) {
            smallSum += arr[leftStartIndex] < arr[rightStartIndex] ?  (end - rightStartIndex + 1) * arr[leftStartIndex]:0 ;
            help[helpIndex++] = arr[leftStartIndex] < arr[rightStartIndex] ? arr[leftStartIndex++] : arr[rightStartIndex++];
        }
        while (leftStartIndex <= middle) {
            help[helpIndex++] = arr[leftStartIndex++];
        }
        while (rightStartIndex <= end) {
            help[helpIndex++] = arr[rightStartIndex++];
        }
        System.arraycopy(help, 0, arr, start, help.length);
        return smallSum;
    }

    public static int comparator(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        int res = 0;
        for (int i = 1; i < arr.length; i++) {
            for (int j = 0; j < i; j++) {
                res += arr[j] < arr[i] ? arr[j] : 0;
            }
        }
        return res;
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
