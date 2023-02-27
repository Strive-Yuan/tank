package com.module.advancedAlgorithm.xor;

import java.util.HashMap;
import java.util.HashSet;

/**
 * 一个数组中有一种数出现K次，其他数都出现了M次
 * M > 1 ,k < M
 * 找到出现了K次的数，要求额外空间复杂度O(1),时间复杂度O(N)
 */
public class XOROperation03 {

    public static void main(String[] args) {
        int kinds = 5;
        int range = 30;
        int testTime = 100000;
        int max = 9;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int a = (int) (Math.random() * max) + 1; // a 1 ~ 9
            int b = (int) (Math.random() * max) + 1; // b 1 ~ 9
            int k = Math.min(a, b);
            int m = Math.max(a, b);
            // k < m
            if (k == m) {
                m++;
            }
            int[] arr = randomArray(kinds, range, k, m);
            int ans1 = test(arr, k);
            int ans2 = process(arr, m);
            if (ans1 != ans2) {
                System.out.println(ans1);
                System.out.println("出错了！");
            }
        }
        System.out.println("测试结束");
    }

    private static int process(int[] arr, int m) {
        int[] tempArr = new int[32];
        for (int num : arr) {
            for (int i = 0; i <= 31; i++) {
                tempArr[i] += (num >> i) & 1;
            }
        }
        int temp = 0;
        for (int i = 0; i < tempArr.length; i++) {
            if (tempArr[i] % m != 0) {
                temp |= 1 << i;
            }
        }
        return temp;
    }

    public static int test(int[] arr, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int num : arr) {
            if (map.containsKey(num)) {
                map.put(num, map.get(num) + 1);
            } else {
                map.put(num, 1);
            }
        }
        int ans = 0;
        for (int num : map.keySet()) {
            if (map.get(num) == k) {
                ans = num;
                break;
            }
        }
        return ans;
    }

    // 为了测试
    public static int[] randomArray(int maxKinds, int range, int k, int m) {
        int timeNum = randomNumber(range);
        // 真命天子出现的次数
        // 2
        int numKinds = (int) (Math.random() * maxKinds) + 2;
        // k * 1 + (numKinds - 1) * m
        int[] arr = new int[k + (numKinds - 1) * m];
        int index = 0;
        for (; index < k; index++) {
            arr[index] = timeNum;
        }
        numKinds--;
        HashSet<Integer> set = new HashSet<>();
        set.add(timeNum);
        while (numKinds != 0) {
            int curNum;
            do {
                curNum = randomNumber(range);
            } while (set.contains(curNum));
            set.add(curNum);
            numKinds--;
            for (int i = 0; i < m; i++) {
                arr[index++] = curNum;
            }
        }
        // arr 填好了
        for (int i = 0; i < arr.length; i++) {
            // i 位置的数，我想随机和j位置的数做交换
            int j = (int) (Math.random() * arr.length);// 0 ~ N-1
            int tmp = arr[i];
            arr[i] = arr[j];
            arr[j] = tmp;
        }
        return arr;
    }

    // 为了测试    // [-range, +range]
    public static int randomNumber(int range) {
        return (int) (Math.random() * (range + 1)) - (int) (Math.random() * (range + 1));
    }
}
