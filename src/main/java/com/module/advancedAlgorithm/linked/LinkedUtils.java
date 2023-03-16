package com.module.advancedAlgorithm.linked;

public class LinkedUtils {

    public static Node getSingleLinkedList() {
        Node head = new Node(5);
        head.next = new Node(7);
        head.next.next = new Node(7);
        head.next.next.next = new Node(7);
        head.next.next.next.next = new Node(7);
        head.next.next.next.next.next = new Node(5);
//        head.next.next.next.next.next.next = new Node(12);
        return head;
    }


    public static DoubleNode getDoubleLinkedList() {
        DoubleNode head = new DoubleNode(5);
        DoubleNode second = new DoubleNode(7);
        head.next = second;
        DoubleNode third = new DoubleNode(1);
        second.last = head;
        second.next = third;
        DoubleNode fourth = new DoubleNode(4);
        third.next = fourth;
        fourth.last = third;
        DoubleNode fifth = new DoubleNode(8);
        fourth.next = fifth;
        fifth.last = fourth;
        return head;
    }
}
