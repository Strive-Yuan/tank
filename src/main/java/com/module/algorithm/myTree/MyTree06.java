package com.module.algorithm.myTree;

/**
 * 判断是否是平衡搜索二叉树
 * https://leetcode.com/problems/balanced-binary-tree/
 */
public class MyTree06 {

    public static void main(String[] args) {
        TreeNode root = MyTreeUtils.getTree();

        MyTree04.Info balanceTree = MyTree04.isBalanceTree(root);
        MyTree05.Info binarySearchTree = MyTree05.isBinarySearchTree(root);
        if (balanceTree.isBalance && binarySearchTree.isBinarySearchTree) {
            System.out.println("这个树是平衡搜索二叉树");
        } else {
            System.out.println("这个树不是平衡搜索二叉树");
        }
    }
}
