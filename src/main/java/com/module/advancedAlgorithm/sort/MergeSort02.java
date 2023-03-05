package com.module.advancedAlgorithm.sort;


public class MergeSort02 {

    public static void main(String[] args) {
        int[] arr = {-50, 62, -25};
        mergeSort(arr);
        ArrayUtils.printArray(arr);
    }

    public static void mergeSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        process(arr, arr.length - 1);
    }

    private static void process(int[] arr, int end) {
        int mergeSize = 1; //步长
        while (mergeSize <= end) {
            int leftGroupIndex = 0;
            while (leftGroupIndex < end) {
                //保证左组是有的，如果左组不满不必继续
                if (mergeSize > end - leftGroupIndex) {
                    break;
                }
                int middle = leftGroupIndex + mergeSize - 1;
                //防止右组不够，而导致越界
                int rightGroupIndex = middle + Math.min(mergeSize, end - middle);
                merge(arr, leftGroupIndex, middle, rightGroupIndex);
                leftGroupIndex = rightGroupIndex + 1;
            }
            mergeSize <<= 1;
        }
    }

    public static void merge(int[] arr, int start, int middle, int end) {
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


}
