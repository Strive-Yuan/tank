package com.module.advancedAlgorithm.linked;

import java.util.Iterator;

public class Node {

    public Integer value;
    public Node next;

    public Node(Integer value) {
        this.value = value;
    }

    public MyNodeIter iterator() {
        return new MyNodeIter(this);
    }

    static class MyNodeIter implements Iterator<Node> {
        public Node lastReturned;
        public Integer value;
        public Node next;

        MyNodeIter(Node node) {
            next = node;
        }

        @Override
        public boolean hasNext() {
            return next != null;
        }

        @Override
        public Node next() {
            lastReturned = next;
            next = next.next;
            return lastReturned;
        }
    }
}

