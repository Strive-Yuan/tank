package com.module.advancedAlgorithm.xor;

/**
 * 一个数组中有2种数出现了奇数次，其他的数都出现了偶数次，怎么找到并打印这种数
 */
public class XOROperation02 {
    public static void main(String[] args) {
        int[] arr = {1, 3, 2, 3, 2, 1, 2, 8};
        int temp = 0;
        for (int num : arr) {
            temp ^= num;
        }
        int rightOne = temp & (-temp);
        int tempOne = 0;
        for (int num : arr) {
            if ((num & rightOne) != 0) {
                tempOne ^= num;
            }
        }
        System.out.println((temp ^ tempOne) + "  " + tempOne);
    }
}
