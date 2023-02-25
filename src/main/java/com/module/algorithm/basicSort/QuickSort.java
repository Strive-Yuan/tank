package com.module.algorithm.basicSort;

import java.util.Stack;

public class QuickSort {

    public static void main(String[] args) {
//        int[] arr = {3, 6, 7, 9, 8};
//        int[] arr = {3, 6, 7, 2, 9, 1, 8, 5, 7, 6};
        int[] arr = {3, 5, 2, 1, 6, 6, 9, 7, 7, 8};
        //递归版本
        quickSort(arr);
        System.out.println("-------------------递归版本排序完成-------------------");
        for (int i : arr) {
            System.out.print(" " + i);
        }
        System.out.println();
        System.out.println("********************************************************");
        int[] arr1 = {3, 5, 2, 1, 6, 6, 9, 7, 7, 8};
        quickSort2(arr1);
        System.out.println("-------------------非递归版本排序完成-------------------");
        for (int i : arr1) {
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


    /**
     * 快速排序(非递归版本)
     */
    public static void quickSort2(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        Stack<Job> stack = new Stack<>();
        stack.push(new Job(0, arr.length - 1));
        while (!stack.isEmpty()) {
            Job job = stack.pop();
            int[] equalsRegion = partition(arr, job.left, job.right);
            //有左区间
            if (equalsRegion[0] > job.left) {
                stack.push(new Job(job.left, equalsRegion[0] - 1));
            }
            //有右区间
            if (equalsRegion[0] < job.left) {
                stack.push(new Job(equalsRegion[1] + 1, job.right));
            }
        }

    }

    public static class Job {
        int left;
        int right;

        public Job(int left, int right) {
            this.left = left;
            this.right = right;
        }
    }


}
