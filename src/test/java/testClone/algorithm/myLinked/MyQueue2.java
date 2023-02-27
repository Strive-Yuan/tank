package testClone.algorithm.myLinked;

/**
 * 用双向链表结构实现双端队列
 */
public class MyQueue2 {
    public Node head;
    public Node tail;

    class Node {
        public Integer data;
        public Node next;
        public Node last;

        public Node(Integer data) {
            this.data = data;
        }
    }

    public static void main(String[] args) {
        MyQueue2 myQueue2 = new MyQueue2();
        myQueue2.pushHead(1);
        myQueue2.pushHead(2);
        myQueue2.pushHead(3);
        myQueue2.pushTail(2);
        myQueue2.pushTail(3);
//        while (myQueue2.head != null) {
//            System.out.println(myQueue2.head.data);
//            myQueue2.head = myQueue2.head.next;
//        }

        System.out.println("===================================");
        System.out.println(myQueue2.pollHead());
        System.out.println(myQueue2.pollTail());
        while (myQueue2.head != null) {
            System.out.println(myQueue2.head.data);
            myQueue2.head = myQueue2.head.next;
        }
//
//        System.out.println(myQueue2.head.data);
//        System.out.println(myQueue2.tail.data);
    }

    private void pushHead(int i) {
        Node node = new Node(i);
        if (head == null) {
            head = node;
            tail = node;
        } else {
            head.last = node;
            node.next = head;
            head = node;
        }

    }

    private void pushTail(int i) {
        Node node = new Node(i);
        if (tail == null) {
            head = node;
        } else {
            tail.next = node;
            node.last = tail;
        }
        tail = node;
    }

    private Node pollHead() {
        Node node = null;
        if (head != null) {
            node = head;
            head = head.next;
            head.last = null;
        }
        return node;
    }

    private Node pollTail() {
        Node node = null;
        if (tail != null) {
            node = tail;
            tail = tail.last;
            tail.next = null;
        }
        return node;
    }
}
