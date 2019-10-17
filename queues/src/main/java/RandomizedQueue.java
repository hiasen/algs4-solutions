import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item>{
    private static final int MINIMUM_CAPACITY = 4;
    private Item[] items;
    private int count = 0;

    public RandomizedQueue() {
        items = createArray(MINIMUM_CAPACITY);
    }

    private Item[] createArray(int capacity) {
        return (Item[]) new Object[capacity];
    }

    public boolean isEmpty() {
        return count == 0;
    }

    public int size() {
        return count;
    }

    public void enqueue(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }
        if (count == items.length) {
            resize(2 * items.length);
        }
        items[count++] = item;
    }
    private void resize(int capacity) {
        if (capacity < MINIMUM_CAPACITY) return;
        Item[] newItems = createArray(capacity);
        System.arraycopy(items, 0, newItems, 0, count);
        items = newItems;
    }

    public Item sample() {
        if (count == 0) {
            throw new NoSuchElementException();
        }
        return items[StdRandom.uniform(count)];
    }

    public Item dequeue() {
        if (count == 0) {
            throw new NoSuchElementException();
        }
        if (4*count == items.length) {
            resize(items.length/2);
        }
        int i = StdRandom.uniform(count);
        Item item = items[i];
        items[i] = items[--count];
        items[count] = null;
        return item;
    }

    @Override
    public Iterator<Item> iterator() {
        return new RandomizedQueueIterator();
    }

    private class RandomizedQueueIterator implements Iterator<Item> {
        Item[] itemsCopy;
        int itemsLeft;

        RandomizedQueueIterator() {
            itemsCopy = createArray(count);
            System.arraycopy(items, 0, itemsCopy, 0, count);
            itemsLeft = count;
        }

        @Override
        public boolean hasNext() {
            return itemsLeft > 0;
        }

        @Override
        public Item next() {
            if (hasNext()) {
                int i = StdRandom.uniform(itemsLeft);
                Item item = itemsCopy[i];
                itemsCopy[i] = itemsCopy[--itemsLeft];
                itemsCopy[itemsLeft] = null;
                return item;
            }
            throw new NoSuchElementException();
        }
    }
    public static void main(String[] args) {
        RandomizedQueue<Integer> rq = new RandomizedQueue<>();
        while (rq.size() < 10) {
            rq.enqueue(StdRandom.uniform(100));
        }
        int n = 4;
        StdOut.printf("\nSample %d elements from the queue:\n", n);
        for (int i = 0; i < n; i++) {
            StdOut.println(rq.sample());
        }
        StdOut.printf("\nDequeuing %d elements from the queue\n", n);
        for (int i = 0; i < n; i++) {
            StdOut.println(rq.dequeue());
        }

        StdOut.println("\nIterate through the remaining elements concurrently with two iterators.");

        Iterator<Integer> it1 = rq.iterator();
        Iterator<Integer> it2 = rq.iterator();
        while (it1.hasNext()) {
            StdOut.printf("%d %d\n", it1.next(), it2.next());
        }

        StdOut.println("\nDequeue the rest of the elements");
        while (!rq.isEmpty()) {
            StdOut.println(rq.dequeue());
        }
    }
}
