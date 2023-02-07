package com.module.algorithm.myTree;

import com.stripe.Stripe;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;

/**
 * 判断是否是一个搜索二叉树
 */
public class MyTree05 {


    /**
     * 方案1:利用中序遍历（二叉树的中序遍历结果一定是递增的）
     */
    public static void main(String[] args) {

        TreeNode tree = MyTreeUtils.getTree();
        Info binarySearchTree = isBinarySearchTree(tree);
        System.out.println(binarySearchTree.isBinarySearchTree);

        System.out.println(LocalDate.now().until(LocalDate.of(2023, 3, 8), ChronoUnit.DAYS));
    }


    /**
     * 搜索二叉树：左小右大
     * 思路： 左树是搜索二叉树，右数是搜索二叉树，左数的值小于当前节点的值，右数的值小于当前节点的值
     */
    public static Info isBinarySearchTree(TreeNode node) {
        if (node == null) {
            return null;
        }
        int max = node.val;
        int min = node.val;
        boolean isBinarySearchTree = true;
        Info leftBinarySearchTree = isBinarySearchTree(node.left);
        Info rightBinarySearchTree = isBinarySearchTree(node.right);
        //左树是否是搜索二叉树
        if (leftBinarySearchTree != null) {
            max = Math.max(max, leftBinarySearchTree.max);
            min = Math.min(min, leftBinarySearchTree.min);
        }
        if (rightBinarySearchTree != null) {
            max = Math.max(max, rightBinarySearchTree.max);
            min = Math.min(min, rightBinarySearchTree.min);
        }
        if (leftBinarySearchTree != null && !leftBinarySearchTree.isBinarySearchTree) {
            isBinarySearchTree = false;
        }
        if (rightBinarySearchTree != null && !rightBinarySearchTree.isBinarySearchTree) {
            isBinarySearchTree = false;
        }
        //当前节点的值要小于右树的最小值，大于左树的最大值
        boolean leftMaxLessNode = leftBinarySearchTree == null || (leftBinarySearchTree.max < node.val);
        boolean rightMinMoreNode = rightBinarySearchTree == null || (rightBinarySearchTree.min > node.val);
        if (!leftMaxLessNode || !rightMinMoreNode) {
            isBinarySearchTree = false;
        }
        return new Info(isBinarySearchTree, max, min);
    }

    public static class Info {
        public boolean isBinarySearchTree;
        public Integer max;
        public Integer min;

        public Info(boolean isBinarySearchTree, Integer max, Integer min) {
            this.isBinarySearchTree = isBinarySearchTree;
            this.max = max;
            this.min = min;
        }
    }

}
