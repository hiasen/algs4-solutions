import org.junit.jupiter.api.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class RandomizedQueueTest {

    @Test
    void newRandomizedQueue() {
        RandomizedQueue rq = new RandomizedQueue();
        assertTrue(rq.isEmpty());
        assertEquals(0, rq.size());
    }
    @Test
    void enqueueOnce() {
        RandomizedQueue<Integer> rq = new RandomizedQueue<>();
        rq.enqueue(1);
        assertFalse(rq.isEmpty());
        assertEquals(1, rq.size());
        assertEquals(1, rq.sample());
        assertEquals(1, rq.sample());
    }
    @Test
    void enqueueThenDequeue() {
        RandomizedQueue<Integer> rq = new RandomizedQueue<>();
        rq.enqueue(1);
        assertEquals(1, rq.dequeue());
    }
    @Test
    void enqueueManyElements() {
        RandomizedQueue<Integer> rq = new RandomizedQueue<>();
        int n = 17;
        for (int i = 1; i <= n; i++) {
            rq.enqueue(i);
        }
        int sum = 0;
        while (!rq.isEmpty()) {
            sum += rq.dequeue();
        }
        assertEquals(n*(n+1), 2*sum);
    }

    @Test
    void iterator() {
        RandomizedQueue<Integer> rq = new RandomizedQueue<>();
        int n = 17;
        for (int i = 1; i <= n; i++) {
            rq.enqueue(i);
        }
        int sum1 = 0;
        int sum2 = 0;
        Iterator<Integer> it1 = rq.iterator();
        Iterator<Integer> it2 = rq.iterator();
        while (it1.hasNext()) {
            sum1 += it1.next();
            sum2 += it2.next();
        }
        assertEquals(sum1, sum2);
    }

    @Test
    void throwsTheCorrectExceptions() {
        RandomizedQueue<Integer> rq = new RandomizedQueue<>();
        assertThrows(NoSuchElementException.class, rq::sample);
        assertThrows(NoSuchElementException.class, rq::dequeue);
        assertThrows(IllegalArgumentException.class, () -> rq.enqueue(null));

        Iterator<Integer> it = rq.iterator();
        assertThrows(NoSuchElementException.class, it::next);
        assertThrows(UnsupportedOperationException.class, it::remove);

    }

    @Test
    void emptyQueueAndAddAgain() {
        RandomizedQueue<Integer> rq = new RandomizedQueue<>();
        rq.enqueue(1);
        rq.enqueue(1);
        rq.enqueue(1);
        rq.enqueue(1);
        rq.dequeue();
        rq.dequeue();
        rq.dequeue();
        rq.dequeue();
        rq.enqueue(1);
        rq.dequeue();

    }
}
