package com.module.advancedAlgorithm.sort.mergeSort;


import com.module.advancedAlgorithm.sort.ArrayUtils;

/**
 * 逆序对
 * 求某数组中有多少对逆序对
 * 例如 [2 3 7 4 3 5 4]
 * 74 73 75 74 43 54 6对
 */
public class InversePair {

    // for test
    public static void main(String[] args) {
        int testTime = 500000;
        int maxSize = 100;
        int maxValue = 100;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int[] arr1 = ArrayUtils.generateRandomArray(maxSize, maxValue);
            int[] arr2 = ArrayUtils.copyArray(arr1);
            if (inversePair(arr1) != ArrayUtils.comparator(arr2)) {
                System.out.println("Oops!");
                ArrayUtils.printArray(arr1);
                ArrayUtils.printArray(arr2);
                break;
            }
        }
        System.out.println("测试结束");
    }

    public static int inversePair(int[] arr) {
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
        int InversePair = 0;
        int[] help = new int[end - start + 1];
        int leftStartIndex = start;
        int rightStartIndex = middle + 1;
        int helpIndex = 0;
        while (leftStartIndex <= middle && rightStartIndex <= end) {
            InversePair += arr[leftStartIndex] > arr[rightStartIndex] ? (end - rightStartIndex + 1) : 0;
            help[helpIndex++] = arr[leftStartIndex] > arr[rightStartIndex] ? arr[leftStartIndex++] : arr[rightStartIndex++];
        }
        while (leftStartIndex <= middle) {
            help[helpIndex++] = arr[leftStartIndex++];
        }
        while (rightStartIndex <= end) {
            help[helpIndex++] = arr[rightStartIndex++];
        }
        System.arraycopy(help, 0, arr, start, help.length);
        return InversePair;
    }
}
