package com.module.advancedAlgorithm.linked;

import java.util.Iterator;

/**
 * 单向链表的反转
 */
public class SingleLinkedListReversal {
    public static void main(String[] args) {
        Node head = LinkedUtils.getSingleLinkedList();
        Iterator<Node> iterator = head.iterator();
        while (iterator.hasNext()) {
            Node next = iterator.next();
            System.out.println(next.value);
        }
        System.out.println("------------------------");
        Node root = reversal(head);
        Iterator<Node> iterator1 = root.iterator();
        while (iterator1.hasNext()) {
            Node next = iterator1.next();
            System.out.println(next.value);
        }
    }

    private static Node reversal(Node root) {
        Node next;
        Node cur = null;
        while (root != null) {
            next = root.next;
            root.next = cur;
            cur = root;
            root = next;

        }
        return cur;
    }
}
