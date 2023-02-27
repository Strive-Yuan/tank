package com.module.advancedAlgorithm.xor;

/**
 * 怎么把一个int类型的数，提取出最右侧的1来
 * a & (~a + 1) = a & (-a)
 */
public class XOROperation04 {
    public static void main(String[] args) {
        int a = 33;
        System.out.println(a ^ (~a + 1));
        System.out.println(a ^ (-a));
    }
}
