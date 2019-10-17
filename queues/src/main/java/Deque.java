import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
    private Node<Item> firstNode = null;
    private Node<Item> lastNode = null;
    private int dequeSize = 0;

    private static class Node<Item> {
        Item item;
        Node<Item> next;
        Node<Item> previous;
        Node(Item item, Node<Item> next, Node<Item> previous) {
            this.item = item;
            this.next = next;
            this.previous = previous;
        }
    }

    public boolean isEmpty() {
        return dequeSize == 0;
    }

    public int size() {
        return dequeSize;
    }

    public void addFirst(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }
        if (isEmpty()) {
            firstNode = new Node<>(item, null, null);
            lastNode = firstNode;
        } else {
            firstNode = firstNode.previous = new Node<>(item, firstNode, null);
        }
        dequeSize++;
    }

    public void addLast(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }
        if (isEmpty()) {
            firstNode = new Node<>(item, null, null);
            lastNode = firstNode;
        } else {
            lastNode = lastNode.next = new Node<>(item, null, lastNode);
        }
        dequeSize++;
    }

    public Item removeFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        Item item = firstNode.item;
        if (size() == 1) {
            firstNode = null;
            lastNode = null;
        } else {
            firstNode = firstNode.next;
            firstNode.previous = null;
        }
        dequeSize--;
        return item;
    }

    public Item removeLast() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        Item item = lastNode.item;
        if (size() == 1) {
            firstNode = null;
            lastNode = null;
        } else {
            lastNode = lastNode.previous;
            lastNode.next = null;
        }
        dequeSize--;
        return item;
    }

    @Override
    public Iterator<Item> iterator() {
        return new DequeIterator<>(firstNode);
    }

    private static class DequeIterator<Item> implements Iterator<Item> {
        Deque.Node<Item> currentNode;

        DequeIterator(Node<Item> currentNode) {
            this.currentNode = currentNode;
        }


        @Override
        public boolean hasNext() {
            return currentNode != null;
        }

        @Override
        public Item next() {
            if (hasNext()) {
                Item item = currentNode.item;
                currentNode = currentNode.next;
                return item;
            }
            throw new NoSuchElementException();
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    public static void main(String[] args) {
        Deque<Integer> deque = new Deque<>();
        int n = 0;
        while (deque.size() < 4) {
            deque.addFirst(n++);
        }
        while (deque.size() < 8) {
            deque.addLast(n++);
        }
        while (deque.size() < 16) {
            deque.addFirst(n++);
            deque.addLast(n++);
        }
        StdOut.println("\nPrint entire deque using iterator");
        for (int x: deque) {
            StdOut.println(x);
        }
        StdOut.printf("\nRemove two from front and one from back\n");
        while (deque.size() >= 3) {
            StdOut.printf("%d %d %d\n", deque.removeFirst(), deque.removeFirst(), deque.removeLast());
        }
        StdOut.printf("\nRemove rest (if any)\n");
        while (!deque.isEmpty()) {
            StdOut.println(deque.removeFirst());
        }
    }
}
