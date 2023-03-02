package com.module.advancedAlgorithm.basicDataStructure;

/**
 * 链表实现队列
 */
public class MyLinkedImplementsQueueAndStack {
    public DoubleNode head;
    public DoubleNode tail;

    public void pushFromHead(Integer num) {
        DoubleNode node = new DoubleNode(num);
        if (head == null) {
            head = node;
            tail = node;
        } else {
            head.last = node;
            node.next = head;
            head = node;
        }
    }

    public Integer pollFromHead() {
        if (head == null) {
            return null;
        }
        DoubleNode temp = head;
        if (head == tail) {
            head = null;
            tail = null;
        } else {
            head = head.next;
            temp.next = null;
            head.last = null;
        }
        return temp.value;
    }

    public void pushFromTail(Integer num) {
        DoubleNode node = new DoubleNode(num);
        if (head == null) {
            head = node;
        } else {
            tail.next = node;
            node.last = tail;
        }
        tail = node;
    }

    public Integer pollFromTail() {
        if (tail == null) {
            return null;
        }
        DoubleNode temp = tail;
        if (head == tail) {
            head = null;
            tail = null;
        } else {
            tail = tail.last;
            temp.next = null;
            tail.next = null;
        }
        return temp.value;
    }

    public static void main(String[] args) {
        System.out.println("----------------------栈----------------------");
        MyLinkedImplementsQueueAndStack queue = new MyLinkedImplementsQueueAndStack();
        queue.pushFromHead(2);
        queue.pushFromHead(4);
        queue.pushFromHead(5);
        queue.pushFromHead(6);
        System.out.println(queue.pollFromHead());
        System.out.println(queue.pollFromHead());
        System.out.println(queue.pollFromHead());
        System.out.println(queue.pollFromHead());

        System.out.println("------------------------队列--------------------");
        queue.pushFromTail(2);
        queue.pushFromTail(4);
        queue.pushFromTail(5);
        queue.pushFromTail(6);
        System.out.println(queue.pollFromHead());
        System.out.println(queue.pollFromHead());
        System.out.println(queue.pollFromHead());
        System.out.println(queue.pollFromHead());
    }
}
