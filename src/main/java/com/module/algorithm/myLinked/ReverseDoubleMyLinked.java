package com.module.algorithm.myLinked;

public class ReverseDoubleMyLinked {

    static class Node {
        public Integer data;
        public Node last;
        public Node next;

        public Node(Integer data) {
            this.data = data;
        }
    }

    public static void main(String[] args) {
        Node node = new Node(1);
        node.next = new Node(2);
        node.next.last = node;
        node.next.next = new Node(3);
        node.next.next.last = node.next;
        node = reverse(node);
        while (node != null) {
            System.out.println(node.data);
            node = node.next;
        }
    }

    //双向链表反转
    private static Node reverse(Node head) {
        Node next;
        Node cur = null;
        while (head != null) {
            next = head.next;
            head.next = cur;
            head.last = next;
            cur = head;
            head = next;
        }
        return cur;
    }
}
