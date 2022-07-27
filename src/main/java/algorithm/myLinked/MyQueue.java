package algorithm.myLinked;

public class MyQueue {
    public Node head;
    public Node tail;
    public int size;


    public static void main(String[] args) {
        MyQueue myQueue = new MyQueue();
        myQueue.add(1);
        myQueue.add(2);
        System.out.println(myQueue.head.data);
        System.out.println(myQueue.head.next.data);
        System.out.println(myQueue.pop().data);
        System.out.println(myQueue.pop().data);
        System.out.println(myQueue.pop());
        System.out.println(myQueue.peek());
    }

    public void add(Integer data) {
        Node node = new Node(data);
        if (head == null) {
            head = node;
            tail = node;
        } else {
            tail.next = node;
            tail = node;
        }
        size++;
    }

    public Node pop() {
        Node curr = head;
        if (head == null) {
            tail = null;
            return curr;
        }
        head = head.next;
        size--;
        return curr;
    }

    public Node peek() {
        return head;
    }

    static class Node {
        public Integer data;
        public Node next;

        public Node(Integer data) {
            this.data = data;
        }
    }

}
