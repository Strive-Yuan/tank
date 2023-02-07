package com.module.algorithm.myTree;


public class MyTreeUtils {

    public static void main(String[] args) {
        TreeNode tree = getTree();
        System.out.println("前序遍历");
        MyTreeUtils.preorderTraversal(tree);
        System.out.println("中序遍历");
        MyTreeUtils.inorderTraversal(tree);
        System.out.println("后序遍历");
        MyTreeUtils.postorderTraversal(tree);

    }

    /**
     * 前序遍历
     */
    public static void preorderTraversal(TreeNode treeNode) {
        if (treeNode == null) {
            return;
        }
        preorderTraversal(treeNode.left);
        System.out.println(treeNode.val);
        preorderTraversal(treeNode.right);
    }

    /**
     * 中序遍历
     */
    public static void inorderTraversal(TreeNode treeNode) {
        if (treeNode == null) {
            return;
        }
        System.out.println(treeNode.val);
        inorderTraversal(treeNode.left);
        inorderTraversal(treeNode.right);
    }

    /**
     * 后序遍历
     */
    public static void postorderTraversal(TreeNode treeNode) {
        if (treeNode == null) {
            return;
        }
        postorderTraversal(treeNode.left);
        postorderTraversal(treeNode.right);
        System.out.println(treeNode.val);
    }

    public static TreeNode getTree() {
        TreeNode treeNode = new TreeNode(2);
//        treeNode.left = new TreeNode(1);
//        treeNode.right = new TreeNode(3);
//        treeNode.left.left = new TreeNode(4);
//        treeNode.left.right = new TreeNode(5);
//        treeNode.right.left = new TreeNode(3);
//        treeNode.right.right = new TreeNode(6);
//        treeNode.right.left.left = new TreeNode(8);
//        treeNode.right.left.right = new TreeNode(9);
//        treeNode.right.left.left.left = new TreeNode(10);
        return treeNode;
    }
}
