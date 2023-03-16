package com.module.advancedAlgorithm.linked;

import java.util.Objects;

/**
 * 判断某链表是否为回文结构
 */
public class PalindromeLinked {

    public static void main(String[] args) {
        Node root = LinkedUtils.getSingleLinkedList();
        Node.MyNodeIter iterator = root.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next().value);
        }
        System.out.println("-------------------------------");
        System.out.println(isPalindromeLinked(root));
        System.out.println("-------------------------------");
        Node.MyNodeIter iterator1 = root.iterator();
        while (iterator1.hasNext()) {
            System.out.println(iterator1.next().value);
        }
    }


    public static boolean isPalindromeLinked(Node root) {
        boolean isPalindrome = true;
        //找到此链表的中点
        Node headNode = root;
        Node curNode = LinkedFindMiddle.findMiddle01(root);
        Node next = curNode.next; //n2
        curNode.next = null;
        Node last = null;
        //后半部分链表反转
        while (next != null) {
            last = next.next;
            next.next = curNode;
            curNode = next;
            next = last;
        }
        Node tailNode = curNode;
        //判断是否回文结构
        while (curNode != null && headNode != null) {
            if (!Objects.equals(curNode.value, headNode.value)) {
                isPalindrome = false;
            }
            curNode = curNode.next;
            headNode = headNode.next;
        }
        //将后半部分链表反转回来
        next = tailNode.next;
        while (next != null) {
            tailNode.next = last;
            last = tailNode;
            tailNode = next;
            next = next.next;
        }
        if (tailNode.next == null) {
            tailNode.next = last;
        }
        return isPalindrome;
    }
}
