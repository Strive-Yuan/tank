package com.module.algorithm.myLinked;

/**
 * 两个链表相加
 */
public class MyQueue4 {
    public Integer size;
    public Node head;
    public Node tail;

    public static void main(String[] args) {
        MyQueue4 myQueue = new MyQueue4();
        myQueue.add(4);
        myQueue.add(6);
        myQueue.add(9);
        myQueue.add(9);
        MyQueue4 myQueue2 = new MyQueue4();
        myQueue2.add(8);
        myQueue2.add(5);

        MyQueue4 myQueue3 = new MyQueue4();

        Integer length = getLength(myQueue.head);
        Integer length2 = getLength(myQueue2.head);
        int carryBit = 0;
        Node l = length > length2 ? myQueue.head : myQueue2.head;
        Node s = length > length2 ? myQueue2.head : myQueue.head;
        while (s != null) {
            myQueue3.add((s.data + l.data) % 10 + carryBit);
            carryBit = (s.data + l.data) / 10;
            l = l.next;
            s = s.next;
        }

        while (l != null) {
            myQueue3.add((l.data + carryBit) % 10);
            carryBit = (l.data + carryBit) / 10;
            l = l.next;
        }

        if (carryBit != 0){
            myQueue3.add(carryBit);
        }

        while (myQueue3.head != null){
            System.out.println(myQueue3.head.data);
            myQueue3.head = myQueue3.head.next;
        }
    }

    private static Integer getLength(Node head) {
        int count = 0;
        while (head != null) {
            head = head.next;
            count++;
        }
        return count;
    }

    class Node {
        public Integer data;
        public Node next;

        public Node(Integer data) {
            this.data = data;
        }
    }

    public void add(Integer data) {
        MyQueue4.Node node = new MyQueue4.Node(data);
        if (head == null) {
            head = node;
        } else {
            tail.next = node;
        }
        tail = node;
    }
}
