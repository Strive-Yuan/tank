package com.module.advancedAlgorithm.basicDataStructure;

/**
 * 链表中删除指定值
 */

public class MyLinked01 {
    public static void main(String[] args) {
        Node head = new Node(5);
        head.next = new Node(7);
        head.next.next = new Node(5);
        head.next.next.next = new Node(4);
        head.next.next.next.next = new Node(7);
        head.next.next.next.next.next = new Node(5);
        head.next.next.next.next.next.next = new Node(8);
        Node newHead = process(head, 5);
        Node.MyNodeIter iterator = newHead.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next().value);
        }
    }

    private static Node process(Node head, int value) {
        while (head != null && head.value == value) {
            head = head.next;
        }
        Node temp = head;
        Node newHead = head;
        head = head.next;
        while (head != null) {
            if (head.value != value) {
                temp.next = head;
                temp = temp.next;
            }
            head = head.next;
        }
        return newHead;
    }
}
