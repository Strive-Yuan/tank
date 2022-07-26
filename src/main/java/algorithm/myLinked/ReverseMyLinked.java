package algorithm.myLinked;

public class ReverseMyLinked {
    static class Node {
        public Integer data;
        public Node next;

        Node(Integer data) {
            this.data = data;
        }
    }

    public static void main(String[] args) {
        Node node = new Node(1);
        node.next = new Node(2);
        node.next.next = new Node(3);
        node.next.next.next = new Node(4);
        node = reverse(node);
        while (node != null) {
            System.out.println(node.data);
            node = node.next;
        }
    }

    //单向链表反转
    private static Node reverse(Node node) {
        Node next = null;
        Node cur = null;
        while (node != null) {
            next = node.next;
            node.next = cur;
            cur = node;
            node = next;
        }
        return cur;
    }
}
