package com.module.algorithm.myTree;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 二叉树按层遍历并收集节点
 */
public class MyTree03 {

    public static void main(String[] args) {
        TreeNode tree = MyTreeUtils.getTree();
        MyTreeUtils.inorderTraversal(tree);
        List<List<TreeNode>> lists = levelOrderBottom(tree);
        System.out.println("二叉树按层遍历并收集节点");
        for (List<TreeNode> list : lists) {
            list.forEach(treeNode -> System.out.println(treeNode.val));
        }
    }

    public static List<List<TreeNode>> levelOrderBottom(TreeNode tree) {
        List<List<TreeNode>> linkedList = new LinkedList<>();
        if (tree == null) {
            return linkedList;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(tree);
        while (!queue.isEmpty()) {
            List<TreeNode> childLinkedList = new LinkedList<>();
            for (int i = 0; i < queue.size(); i++) {
                TreeNode currentNode = queue.poll();
                childLinkedList.add(currentNode);
                if (currentNode.left != null) {
                    queue.add(currentNode.left);
                }
                if (currentNode.right != null) {
                    queue.add(currentNode.right);
                }
            }
            linkedList.add(0, childLinkedList);
        }
        return linkedList;
    }
}
