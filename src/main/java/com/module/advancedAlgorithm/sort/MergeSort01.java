package com.module.advancedAlgorithm.sort;


public class MergeSort01 {

    public static void main(String[] args) {
        int testTime = 500000;
        int maxSize = 100;
        int maxValue = 100;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int[] arr1 = generateRandomArray(maxSize, maxValue);
            int[] arr2 = copyArray(arr1);
            BubbleSort.bubbleSort(arr1);
            MergeSort02.mergeSort(arr2);
            if (!isEqual(arr1, arr2)) {
                System.out.println("出错了！");
                printArray(arr1);
                printArray(arr2);
                break;
            }
        }
        System.out.println("测试结束");

//        int[] arr = {};
//        process(arr, 0, arr.length - 1);
    }

    public static void mergeSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        process(arr, 0, arr.length - 1);
    }

    public static void process(int[] arr, int start, int end) {
        if (start == end) {
            return;
        }
        int middle = start + ((end - start) >> 1);
        process(arr, start, middle);
        process(arr, middle + 1, end);
        merge(arr, start, middle, end);

    }

    private static void merge(int[] arr, int start, int middle, int end) {
        int[] help = new int[end - start + 1];
        int leftStartIndex = start;
        int rightStartIndex = middle + 1;
        int index = 0;
        while (leftStartIndex <= middle && rightStartIndex <= end) {
            help[index++] = arr[leftStartIndex] <= arr[rightStartIndex] ? arr[leftStartIndex++] : arr[rightStartIndex++];
        }
        while (leftStartIndex <= middle) {
            help[index++] = arr[leftStartIndex++];
        }
        while (rightStartIndex <= end) {
            help[index++] = arr[rightStartIndex++];
        }
        System.arraycopy(help, 0, arr, start, help.length);
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
