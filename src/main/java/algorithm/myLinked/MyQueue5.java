package algorithm.myLinked;

/**
 * 两个有序联表的合并
 */
public class MyQueue5 {

    public Integer size;
    public Node head;
    public Node tail;

    public static void main(String[] args) {
        System.out.println(-2 >>> 1);
        MyQueue5 myQueue = new MyQueue5();
        myQueue.add(4);
        myQueue.add(6);
        myQueue.add(9);
        MyQueue5 myQueue2 = new MyQueue5();
        myQueue2.add(3);
        myQueue2.add(7);
        Node head = myQueue.head.data > myQueue2.head.data ? myQueue.head : myQueue2.head;

//        while (myQueue.head != null) {
//
//        }
    }

    class Node {
        public Integer data;
        public Node next;

        public Node(Integer data) {
            this.data = data;
        }
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
}