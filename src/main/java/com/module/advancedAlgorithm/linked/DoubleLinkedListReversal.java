package com.module.advancedAlgorithm.linked;

public class DoubleLinkedListReversal {
    public static void main(String[] args) {
        //5,7,1,4,8
        DoubleNode root = LinkedUtils.getDoubleLinkedList();
        DoubleNode head = reversal(root);
        System.out.println(head.value);
        System.out.println(head.next.value);
        System.out.println(head.next.next.value);
        System.out.println(head.next.next.next.value);
        System.out.println(head.next.next.next.next.value);
    }

    private static DoubleNode reversal(DoubleNode root) {
        DoubleNode next;
        DoubleNode cur = null;
        while (root != null) {
            next = root.next;
            root.last = next;
            root.next = cur;
            cur = root;
            root = next;
        }
        return cur;
    }

}
