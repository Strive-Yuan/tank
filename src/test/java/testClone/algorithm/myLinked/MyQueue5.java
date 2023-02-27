package testClone.algorithm.myLinked;

/**
 * 两个有序链表的合并
 */
public class MyQueue5 {

    public Integer size;
    public Node head;
    public Node tail;

    public static void main(String[] args) {
        MyQueue5 myQueue = new MyQueue5();
        myQueue.add(4);
        myQueue.add(6);
        myQueue.add(9);
        MyQueue5 myQueue2 = new MyQueue5();
        myQueue2.add(3);
        myQueue2.add(7);
        Node head = myQueue.head.data < myQueue2.head.data ? myQueue.head : myQueue2.head;
        Node cur1 = head.next;
        Node cur2 = head == myQueue.head ? myQueue2.head : myQueue.head;
        Node pre = head;
        while (cur1 != null && cur2 != null) {
            if (cur1.data < cur2.data) {
                pre.next = cur1;
                cur1 = cur1.next;
            } else {
                pre.next = cur2;
                cur2 = cur2.next;
            }
            pre = pre.next;
        }
        pre.next = cur1 != null ? cur1 : cur2;
        while (head != null) {
            System.out.println(head.data);
            head = head.next;
        }
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