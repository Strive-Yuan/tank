package com.module.algorithm.myLinked;

public class MyStack {
    public Node head;
    public int size;

    public void push(Integer data) {
        Node node = new Node(data);
        if (head != null) {
            node.next = head;
        }
        head = node;
        size++;
    }

    public Integer pop() {
        Node node = head;
        if (head != null) {
            head = head.next;
        }
        if (node == null) {
            return null;
        }
        return node.data;
    }

    public Integer peek() {
        if (head == null) {
            return null;
        }
        return head.data;
    }

    public static void main(String[] args) {
        MyStack myStack = new MyStack();
        myStack.push(1);
        myStack.push(2);
        myStack.push(3);
        System.out.println(myStack.peek());
        System.out.println(myStack.pop());
        System.out.println(myStack.peek());
        System.out.println(myStack.pop());
        System.out.println(myStack.peek());
        System.out.println(myStack.pop());
    }

    static class Node {
        public Integer data;
        public Node next;
        public Node(Integer data) {
            this.data = data;
        }
    }

}
