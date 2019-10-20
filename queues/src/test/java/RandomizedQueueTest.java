import static org.junit.jupiter.api.Assertions.*;

import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;




class RandomizedQueueTest {
    private RandomizedQueue<Integer> rq;

    @BeforeEach
    void createRandomizedQueue() {
        rq = new RandomizedQueue<>();
    }

    void assertIsEmpty() {
        assertTrue(rq.isEmpty(), "RandomizedQueue should be empty.");
    }

    @Test
    void newRandomizedQueueIsEmpty() {
        assertIsEmpty();
    }

    @Test
    void newRandomizedQueueHasSizeZero() {
        assertEquals(0, rq.size(), "Should have size zero.");
    }

    @Test
    void enqueueOnceNonEmpty() {
        rq.enqueue(1);
        assertFalse(rq.isEmpty(), "Should be nonempty.");
    }

    @Test
    void enqueueOnceSizeIsOne() {
        rq.enqueue(1);
        assertEquals(1, rq.size(), "Size should be 1.");
    }

    @Test
    void callingSampleTwiceGivesSameResultForOnlyOneValueInQueue() {
        rq.enqueue(1);
        assertEquals(rq.sample(), rq.sample(), "Both samples should be equal.");
    }

    @Test
    void enqueueThenDequeue() {
        rq.enqueue(1);
        assertEquals(1, rq.dequeue(), "Dequeue should equal what was enqueued.");
    }

    @Test
    void enqueueManyElements() {
        final int n = 17;
        final int correctSum = n * (n + 1) / 2;
        for (int i = 1; i <= n; i++) {
            rq.enqueue(i);
        }
        int sum = 0;
        while (!rq.isEmpty()) {
            sum += rq.dequeue();
        }
        assertEquals(correctSum, sum, "Sum should be independent of order of dequeued values.");
    }

    @Test
    void iterator() {
        for (int i = 1; i <= 17; i++) {
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
        assertEquals(sum1, sum2, "Sums should be equal.");
    }

    @Test
    void sample_ThrowsException_When_QueueIsEmpty() {
        assertThrows(NoSuchElementException.class, rq::sample);
    }

    @Test
    void dequeue_ThrowsException_When_QueueIsEmpty() {
        assertThrows(NoSuchElementException.class, rq::dequeue);
    }

    @Test
    void enqueue_ThrowsException_When_GivenNull() {
        assertThrows(IllegalArgumentException.class, () -> rq.enqueue(null));
    }

    @Test
    void iteratorNext_ThrowsException_When_Empty() {
        assertThrows(NoSuchElementException.class, rq.iterator()::next,
                "Next should throw exception on empty iterator.");
    }

    @Test
    void removeMethodIsNotSupportedOnIterator() {
        assertThrows(UnsupportedOperationException.class, rq.iterator()::remove,
                "Remove should throw UnsupportedOperationException.");
    }

    @Test
    void emptyQueueAndAddAgain() {
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
        assertIsEmpty();
    }
}
