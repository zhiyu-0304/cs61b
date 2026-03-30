package deque;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedListDeque<T> implements Iterable<T>, Deque<T> {
    private class Node {
        private T item;
        private Node next;
        private Node prev;

        public Node(T item, Node next, Node prev) {
            this.item = item;
            this.next = next;
            this.prev = prev;
        }

        public T nodeGetRecursive(int index) {
            if (index == 0) {
                return item;
            } else
                return next.nodeGetRecursive(index - 1);
        }
    }

    private Node sentinel;
    private int size;

    public LinkedListDeque() {
        sentinel = new Node(null, null, null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
        size = 0;
    }

    //get the ith item of the list
    @Override
    public T get(int index) {
        Node node = this.sentinel.next;
        if (node == sentinel) {
            return null;
        }
        for (int i = 0; i < index; i++) {
            if (node == sentinel) {
                return null;
            }
            node = node.next;
        }
        return node.item;
    }

    public T getRecursive(int index) {
        if (size == 0 || index >= size) {
            return null;
        }
        Node node = this.sentinel.next;
        return node.nodeGetRecursive(index);
    }

    //add an item to the first location in the list
    @Override
    public void addFirst(T item) {
        Node next = sentinel.next;
        sentinel.next = new Node(item, next, sentinel);
        next.prev = sentinel.next;
        size++;
    }

    @Override
    public void addLast(T item) {
        Node prev = sentinel.prev;
        sentinel.prev = new Node(item, sentinel, prev);
        prev.next = sentinel.prev;
        size++;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void printDeque() {
        Node curr = sentinel.next;
        while (curr != sentinel) {
            System.out.print(curr.item.toString() + " ");
            curr = curr.next;
        }
        System.out.println();
    }

    @Override
    public T removeFirst() {
        Node node = sentinel.next;
        if (node == sentinel) {
            return null;
        }
        sentinel.next = node.next;
        node.next.prev = sentinel;
        T temp = node.item;
        node = null;
        size--;
        return temp;
    }

    @Override
    public T removeLast() {
        Node node = sentinel.prev;
        if (node == sentinel) {
            return null;
        }
        sentinel.prev = node.prev;
        node.prev.next = sentinel;
        T temp = node.item;
        node = null;
        size--;
        return temp;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (o instanceof Deque) {
            Deque<T> d = (Deque<T>) o;
            if (size == d.size()) {
                for (int i = 0; i < size; i++) {
                    if (!d.get(i).equals(this.get(i))) {
                        return false;
                    }
                }
                return true;
            }
        }
        return false;
    }

    private class LinkedListDequeIterator implements Iterator<T> {
        private int position;

        public LinkedListDequeIterator() {
            position = 0;
        }

        @Override
        public boolean hasNext() {
            return position < size;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            T temp = get(position);
            position++;
            return temp;
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new LinkedListDequeIterator();
    }
}
