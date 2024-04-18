package org.example;

import java.util.Iterator;

public class MyLinkedList<E> implements MyListInterface<E> {
    private Node first;
    private Node last;
    private int size;

    public MyLinkedList() {

    }

    public MyLinkedList(MyLinkedList<? extends E> myList) {
        for (E item : myList) {
            add(item);
        }
    }

    @Override
    public E get(int index) {
        return getNode(index).value;
    }

    @Override
    public void add(E e) {
        if (size == 0) {
            first = new Node(null, e, null);
            last = first;
        } else {
            Node secondLast = last;
            last = new Node(secondLast, e, null);
            secondLast.next = last;
        }
        size++;
    }

    @Override
    public void addByIndex(E e, int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }
        if (index == size) {
            add(e);
            return;
        }
        Node nodeNext = getNode(index);
        Node nodePrevious = nodeNext.previous;
        Node newNode = new Node(nodePrevious, e, nodeNext);
        nodeNext.previous = newNode;
        if (nodePrevious != null) {
            nodePrevious.next = newNode;
        } else {
            first = newNode;
        }
        size++;
    }

    @Override
    public void remove(E e) {
        Node node = first;
        for (int i = 0; i < size; i++) {
            if (node.value.equals(e)) {
                removeByIndex(i);
            }
            node = node.next;
        }
    }

    @Override
    public void removeByIndex(int index) {
        Node node = getNode(index);
        Node nodeNext = node.next;
        Node nodePrevious = node.previous;
        if (nodeNext != null) {
            nodeNext.previous = nodePrevious;
        } else {
            last = nodePrevious;
        }
        if (nodePrevious != null) {
            nodePrevious.next = nodeNext;
        } else {
            first = nodeNext;
        }
        size--;
    }

    @Override
    public void addAll(MyListInterface<E> myList) {

    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        first = null;
        last = null;
        size = 0;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {

            private Node node = first;

            @Override
            public boolean hasNext() {
                return node != null;
            }

            @Override
            public E next() {
                E e = node.value;
                node = node.next;
                return e;
            }
        };
    }

    private Node getNode(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        Node node = first;
        for (int i = 0; i < index; i++) {
            node = node.next;
        }
        return node;
    }


    private class Node {
        private Node previous;
        private E value;
        private Node next;

        public Node(Node previous, E value, Node next) {
            this.previous = previous;
            this.value = value;
            this.next = next;
        }
    }
}
