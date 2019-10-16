import org.junit.jupiter.api.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class DequeTest {
    @Test
    void constructDeque() {
        new Deque<Integer>();
    }
    @Test
    void illegalArgumentExceptionOnNullArgument() {
        Deque<Integer> deque = new Deque<>();
        assertThrows(IllegalArgumentException.class, () -> deque.addFirst(null));
        assertThrows(IllegalArgumentException.class, () -> deque.addLast(null));
    }
    @Test
    void noElementInNewDeque() {
        Deque<Integer> deque = new Deque<>();
        assertThrows(NoSuchElementException.class, deque::removeFirst);
        assertThrows(NoSuchElementException.class, deque::removeLast);
    }
    @Test
    void newDequeIsEmpty() {
        Deque<Integer> deque = new Deque<>();
        assertTrue(deque.isEmpty());
        assertEquals(0, deque.size());
    }

    @Test
    void isIterator() {
        for (Integer x: new Deque<Integer>()) {
            fail();
        }
        Iterator<Integer> it = new Deque<Integer>().iterator();
        assertThrows(NoSuchElementException.class, it::next);
        assertThrows(UnsupportedOperationException.class, it::remove);
    }

    @Test
    void addAndRemoveOneItemToFront() {
        Deque<Integer> deque = new Deque<>();
        deque.addFirst(100);
        assertFalse(deque.isEmpty());
        assertEquals(1, deque.size());

        assertEquals(100, deque.removeFirst());
        assertTrue(deque.isEmpty());
        assertEquals(0, deque.size());
    }

    @Test
    void addAndRemoveOneItemToBack() {
        Deque<Integer> deque = new Deque<>();
        deque.addLast(100);
        assertFalse(deque.isEmpty());
        assertEquals(1, deque.size());

        assertEquals(100, deque.removeLast());
        assertTrue(deque.isEmpty());
        assertEquals(0, deque.size());
    }

    @Test
    void addFirstRemoveLast() {
        Deque<Integer> deque = new Deque<>();
        deque.addFirst(100);
        assertEquals(100, deque.removeLast());
        assertTrue(deque.isEmpty());
    }
    @Test
    void addLastRemoveFirst() {
        Deque<Integer> deque = new Deque<>();
        deque.addLast(100);
        assertEquals(100, deque.removeFirst());
        assertTrue(deque.isEmpty());
    }

    @Test
    void addFirstTwoItems() {
        Deque<Integer> deque = new Deque<>();
        deque.addFirst(1);
        deque.addFirst(2);
        assertEquals(2, deque.size());
        assertEquals(2, deque.removeFirst());
        assertEquals(1, deque.size());
        assertEquals(1, deque.removeFirst());
        assertTrue(deque.isEmpty());
        assertEquals(0, deque.size());
    }

    @Test
    void stackLikeFront() {
        Deque<Integer> deque = new Deque<>();
        deque.addFirst(1);
        deque.addFirst(2);
        deque.addFirst(3);
        deque.addFirst(4);

        assertEquals(4, deque.removeFirst());
        assertEquals(3, deque.removeFirst());
        assertEquals(2, deque.removeFirst());
        assertEquals(1, deque.removeFirst());

        assertTrue(deque.isEmpty());
    }

    @Test
    void stackLikeBack() {
        Deque<Integer> deque = new Deque<>();
        deque.addLast(1);
        deque.addLast(2);
        deque.addLast(3);
        deque.addLast(4);

        assertEquals(4, deque.removeLast());
        assertEquals(3, deque.removeLast());
        assertEquals(2, deque.removeLast());
        assertEquals(1, deque.removeLast());

        assertTrue(deque.isEmpty());
    }

    @Test
    void iteration() {
        Deque<Integer> deque = new Deque<>();

        deque.addLast(2);
        deque.addLast(3);
        deque.addLast(4);
        deque.addFirst(1);

        int y = 1;
        for (int x: deque) {
            assertEquals(x, y);
            y++;
        }
        assertEquals(5, y);

    }

}
