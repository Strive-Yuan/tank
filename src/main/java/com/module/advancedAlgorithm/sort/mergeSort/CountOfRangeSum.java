package com.module.advancedAlgorithm.sort.mergeSort;

import java.util.Arrays;

/**
 * 给定一个数组arr,两个数lower和upper
 * 返回arr中有多少个子数组的累加和在[lower,upper]范围伤
 * // https://leetcode.com/problems/count-of-range-sum/
 */
public class CountOfRangeSum {

    public static void main(String[] args) {
        int[] arr = {-2147483647,0,-2147483647,2147483647};
        int lower = -564;
        int upper = 3864;
        System.out.println(countOfRangeSum(arr, lower, upper));
    }

    private static int countOfRangeSum(int[] nums, int lower, int upper) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        //前缀和数组
        long[] sumArr = new long[nums.length];
        sumArr[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            sumArr[i] = sumArr[i - 1] + nums[i];
        }
        System.out.println("-------------前缀和-------------");
        Arrays.stream(sumArr).forEach(num -> System.out.print(" " + num));
        System.out.println();
        int process = process(sumArr, 0, nums.length - 1, lower, upper);
        System.out.println("-------------排序-------------");
        Arrays.stream(sumArr).forEach(num -> System.out.print(" " + num));
        System.out.println();
        return process;
    }

    private static int process(long[] arr, int start, int end, int lower, int upper) {
        if (start == end) {
            return arr[start] >= lower && arr[end] <= upper ? 1 : 0;
        }
        int middle = start + ((end - start) >> 1);
        int leftGroupNum = process(arr, start, middle, lower, upper);
        int rightGroupNum = process(arr, middle + 1, end, lower, upper);
        int merge = merge(arr, start, middle, end, lower, upper);
        return leftGroupNum + rightGroupNum + merge;
    }

    private static int merge(long[] arr, int start, int middle, int end, int lower, int upper) {
        int ans = 0;
        int leftGroup = start;
        int rightGroup = start;
        for (int i = middle + 1; i <= end; i++) {
            long min = arr[i] - upper;
            long max = arr[i] - lower;
            while (rightGroup <= middle && arr[rightGroup] <= max) {
                rightGroup++;
            }
            while (leftGroup <= middle && arr[leftGroup] < min) {
                leftGroup++;
            }
            ans += rightGroup - leftGroup;
        }


        //合并
        long[] help = new long[end - start + 1];
        int leftStartIndex = start;
        int rightStartIndex = middle + 1;
        int helpIndex = 0;
        while (leftStartIndex <= middle && rightStartIndex <= end) {
            help[helpIndex++] = arr[leftStartIndex] <= arr[rightStartIndex] ? arr[leftStartIndex++] : arr[rightStartIndex++];
        }
        while (leftStartIndex <= middle) {
            help[helpIndex++] = arr[leftStartIndex++];
        }
        while (rightStartIndex <= end) {
            help[helpIndex++] = arr[rightStartIndex++];
        }
        System.arraycopy(help, 0, arr, start, help.length);
        return ans;
    }
}
