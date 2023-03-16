package com.module.advancedAlgorithm.linked;

/**
 * 找到链表的中点
 * 1）输入链表头节点，奇数长度返回中点，偶数长度返回上中点
 * 2）输入链表头节点，奇数长度返回中点，偶数长度返回下中点
 * 3）输入链表头节点，奇数长度返回中点前一个，偶数长度返回上中点前一个
 * 4）输入链表头节点，奇数长度返回中点前一个，偶数长度返回下中点前一个
 */
public class LinkedFindMiddle {

    public static void main(String[] args) {
        Node root = LinkedUtils.getSingleLinkedList();
        Node middleNode = findMiddle01(root);
        System.out.println(middleNode.value);
        System.out.println(findMiddle02(root).value);
        System.out.println(findMiddle03(root).value);
        System.out.println(findMiddle04(root).value);
    }

    /**
     * 快慢指针
     * 1）输入链表头节点，奇数长度返回中点，偶数长度返回上中点
     */
    public static Node findMiddle01(Node root) {
        if (root == null) {
            return root;
        }
        Node quickNode = root;
        Node slowNode = root;
        while (quickNode.next != null && quickNode.next.next != null) {
            quickNode = quickNode.next.next;
            slowNode = slowNode.next;
        }
        return slowNode;
    }

    /**
     * 快慢指针
     * 2）输入链表头节点，奇数长度返回中点，偶数长度返回下中点
     */
    private static Node findMiddle02(Node root) {
        if (root == null) {
            return root;
        }
        Node quickNode = root;
        Node slowNode = root;
        while (quickNode.next != null && quickNode.next.next != null) {
            quickNode = quickNode.next.next;
            slowNode = slowNode.next;
        }
        if (quickNode.next != null) {
            slowNode = slowNode.next;
        }
        return slowNode;
    }

    /**
     * 快慢指针
     * 3）输入链表头节点，奇数长度返回中点前一个，偶数长度返回上中点前一个
     */
    private static Node findMiddle03(Node root) {
        if (root == null) {
            return root;
        }
        Node quickNode = root;
        Node slowNode = root;
        Node lastSlowNode = root;
        while (quickNode.next != null && quickNode.next.next != null) {
            quickNode = quickNode.next.next;
            lastSlowNode = slowNode;
            slowNode = slowNode.next;
        }
        return lastSlowNode;
    }

    /**
     * 快慢指针
     * 4）输入链表头节点，奇数长度返回中点前一个，偶数长度返回下中点前一个
     */
    private static Node findMiddle04(Node root) {
        if (root == null) {
            return root;
        }
        Node quickNode = root;
        Node slowNode = root;
        while (quickNode.next != null && quickNode.next.next != null) {
            quickNode = quickNode.next.next;
            slowNode = slowNode.next;
        }
        if (quickNode.next != null) {
            slowNode = slowNode.next;
        }
        if (slowNode.next != null) {
            return slowNode.next;
        }
        return slowNode;
    }
}
