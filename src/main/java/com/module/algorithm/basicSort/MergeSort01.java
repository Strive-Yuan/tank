package com.module.algorithm.basicSort;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 归并排序（非递归方式）
 */
public class MergeSort01 {
    public static void main(String[] args) {
        int[] arr = {3, 6, 7, 9, 8};
        process(arr);
        Arrays.stream(arr).forEach(System.out::println);
    }

    private static void process(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        int step = 1;
        int N = arr.length;
        while (step < N) {

            //防止step超出整数最大值
            if (step > (N / 2)) {
                break;
            } else {
                step *= 2;
            }
            Map<String, String> map = new HashMap<>();
//            map.put()


        }

    }
}
