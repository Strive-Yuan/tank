package com.module.algorithm.myTree;

import java.util.HashMap;
import java.util.Map;

/**
 * 有一个颗树，先序遍历的结果是pre[L1...R1],中序结果是in[L2...R2]
 * 请建出整个树返回
 */
public class MyTree02 {

    public static TreeNode buildTree(int[] pre, int[] in) {
        if (pre == null || in == null || pre.length != in.length) {
            return null;
        }
        Map<Integer, Integer> valueIndexMap = new HashMap<>();
        for (int i = 0; i < in.length; i++) {
            valueIndexMap.put(in[i], i);
        }

        return f(pre, 0, pre.length - 1, in, 0, in.length, valueIndexMap);
    }

    private static TreeNode f(int[] pre, int L1, int R1, int[] in, int L2, int R2, Map<Integer, Integer> valueIndexMap) {
        if (L1 > R1) {
            return null;
        }
        TreeNode head = new TreeNode(pre[L1]);
        if (L1 == R1) {
            return head;
        }
        int find = valueIndexMap.get(pre[L1]);
        head.left = f(pre, L1 + 1, L1 + find - L2, in, L2, find - 1, valueIndexMap);
        head.right = f(pre, L1 + find - L2 + 1, R1, in, find + 1, R2, valueIndexMap);
        return head;
    }

    public static void main(String[] args) {
        int[] pre = {2, 3, 9, 4, 7, 6, 5};
        int[] in = {9, 3, 4, 2, 6, 7, 5};

        TreeNode root = buildTree(pre, in);
        System.out.println(root.val);
        System.out.println(root.left.val);
        System.out.println(root.left.left.val);
        System.out.println(root.left.right.val);
        System.out.println(root.right.val);
        System.out.println(root.right.left.val);
        System.out.println(root.right.right.val);
    }
}
