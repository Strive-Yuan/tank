package com.module.algorithm.myTree;


import java.util.ArrayList;
import java.util.List;

/**
 * 收集所有累加和路径
 * https://leetcode.com/problems/path-sum-li/
 */
public class MyTree08 {

    public static void main(String[] args) {
        int sum = 20;
        TreeNode root = MyTreeUtils.getTree();

        List<List<Integer>> process = process(root, sum);
        System.out.println("process.size()：" + process.size());
        for (List<Integer> integerList : process) {
            System.out.println("-----------------------");
            for (Integer integer : integerList) {
                System.out.println(integer);
            }
        }

    }

    public static List<List<Integer>> process(TreeNode root, int targetSum) {
        List<List<Integer>> list = new ArrayList<>();
        if (root == null) {
            return list;
        }
        List<Integer> preList = new ArrayList<>();
        hasPathSum(list, preList, root, targetSum, 0);
        return list;
    }

    private static void hasPathSum(List<List<Integer>> list, List<Integer> preList, TreeNode root, int targetSum, int processValue) {
        if (root.left == null && root.right == null) {
            if (processValue + root.val == targetSum) {
                System.out.println(111);
                preList.add(root.val);
                list.add(copy(preList));
                preList.remove(preList.size() - 1);
            }
            return;

        }
        preList.add(root.val);
        processValue += root.val;
        if (root.left != null) {
            hasPathSum(list, preList, root.left, targetSum, processValue);
        }
        if (root.right != null) {
            hasPathSum(list, preList, root.right, targetSum, processValue);
        }
        preList.remove(preList.size() - 1);
    }


    private static List<Integer> copy(List<Integer> path) {
        return new ArrayList<>(path);
    }
}
