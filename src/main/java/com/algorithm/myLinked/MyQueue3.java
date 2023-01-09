package com.algorithm.myLinked;

/**
 * K个节点的组内逆序调整
 */
public class MyQueue3 {
    public Integer size;
    public Node head;
    public Node tail;

    public static void main(String[] args) {
        MyQueue3 myQueue = new MyQueue3();
        myQueue.add(1);
        myQueue.add(2);
        myQueue.add(3);
        myQueue.add(4);
        myQueue.add(5);
        myQueue.add(6);
        myQueue.add(7);
        myQueue.add(8);
        myQueue.add(9);
        myQueue.add(10);
        int k = 3;
        Node start = myQueue.head;
        Node end = getGroupTail(start, k);
        if (end == null){
            return;
        }
        reverse(start, end);
        myQueue.head = end;
        Node lastEnd = start;
        while (lastEnd.next != null) {
            start = lastEnd.next;
            end = getGroupTail(start, k);
            if (end == null) {
                return;
            }
            reverse(start, end);
            lastEnd.next = end;
            lastEnd = start;
        }
        while (myQueue.head != null) {
            System.out.println(myQueue.head.data);
            myQueue.head = myQueue.head.next;
        }
    }

    private static void reverse(Node head, Node end) {
        Node start = head;
        Node nextEnd = end.next;
        Node cur = null;
        Node next = null;
        while (cur != end) {
            next = start.next;
            start.next = cur;
            cur = start;
            start = next;
        }
        head.next = nextEnd;
    }

    private static Node getGroupTail(Node start, int k) {
        Node tail = null;
        while (start != null && k-- > 0) {
            tail = start;
            start = start.next;
        }
        return tail;
    }

    public void add(Integer data) {
        Node node = new Node(data);
        if (head == null) {
            head = node;
        } else {
            tail.next = node;
        }
        tail = node;
    }

    class Node {
        public Integer data;
        public Node next;

        public Node(Integer data) {
            this.data = data;
        }
    }
}
