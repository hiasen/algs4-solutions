import static org.junit.jupiter.api.Assertions.*;

import java.util.NoSuchElementException;
import org.junit.jupiter.api.*;


class DequeTest {
    private Deque<Integer> deque;

    @BeforeEach
    void createDeque() {
        deque = new Deque<>();
    }

    @Test
    void addFirst_ThrowsIllegalArgumentException_When_SuppliedNull() {
        assertThrows(IllegalArgumentException.class, () -> deque.addFirst(null),
                "Trying to add null to deque is illegal.");
    }

    @Test
    void addLast_ThrowsIllegalArgumentException_When_SuppliedNull() {
        assertThrows(IllegalArgumentException.class, () -> deque.addLast(null),
                "Trying to add null to deque is illegal.");
    }

    @Test
    void removeFirst_ThrowsException_When_DequeIsEmpty() {
        assertThrows(NoSuchElementException.class, deque::removeFirst,
                "Exception should be raised when trying to remove element from empty deque.");
    }

    @Test
    void removeLast_ThrowsException_When_DequeIsEmpty() {
        assertThrows(NoSuchElementException.class, deque::removeLast,
                "Exception should be raised when trying to remove element from empty deque.");
    }

    @Test
    void newDequeIsEmpty() {
        assertTrue(deque.isEmpty(), "A new deque should be empty.");
    }

    @Test
    void newDequeHasZeroSize() {
        assertEquals(0, deque.size(), "Size of a new deque should be 0.");
    }

    @Test
    void dequeIsIterable() {
        assertDoesNotThrow(deque::iterator, "Calling iterator on deque should not throw exceptions.");
    }

    @Test
    void nextOnEmptyIteratorThrowsNoSuchElementException() {
        assertThrows(NoSuchElementException.class, deque.iterator()::next,
                "Calling next on empty iterator should throw exception.");

    }

    @Test
    void dequeIteratorDoesNotSupportRemove() {
        assertThrows(UnsupportedOperationException.class, deque.iterator()::remove,
                "remove on iterator should throw exception.");
    }

    @Test
    void addFirst_ResultsIn_DequeNonEmpty() {
        deque.addFirst(100);
        assertFalse(deque.isEmpty(), "Deque should be nonempty.");
    }

    @Test
    void addLast_ResultsIn_DequeNonEmpty() {
        deque.addLast(100);
        assertFalse(deque.isEmpty(), "Deque should be nonempty.");
    }

    @Test
    void addingOneItemResultsInDequeSizeOfOne() {
        deque.addFirst(100);
        assertEquals(1, deque.size(), "Deque should have size of one.");
    }

    @Test
    void addLastResultsInDequeSizeOfOne() {
        deque.addLast(100);
        assertEquals(1, deque.size(), "Deque should have size of one.");
    }

    @Test
    void addAndRemoveOneItemInFront() {
        deque.addFirst(100);
        assertEquals(100, deque.removeFirst(), "Removed item should equal what was added.");
    }

    @Test
    void addAndRemoveOneItemToBack() {
        deque.addLast(100);
        assertEquals(100, deque.removeLast(), "Removed item should equal what was added.");
    }

    @Test
    void addFirstRemoveLast() {
        deque.addFirst(100);
        assertEquals(100, deque.removeLast(), "Removed item should equal what was added.");
    }

    @Test
    void addLastRemoveFirst() {
        deque.addLast(100);
        assertEquals(100, deque.removeFirst(), "Removed item should equal what was added.");
    }

    @Test
    @SuppressWarnings("PMD.JUnitTestContainsTooManyAsserts")
    void stackLikeFront() {
        for (int i = 0; i <= 4; i++) {
            deque.addFirst(i);
        }

        for (int i = 4; i >= 0; i--) {
            assertEquals(i, deque.removeFirst(), "Elements should come in reverse order.");
        }
        assertTrue(deque.isEmpty(), "Deque should be empty.");
    }

    @Test
    @SuppressWarnings("PMD.JUnitTestContainsTooManyAsserts")
    void stackLikeBack() {
        for (int i = 0; i <= 4; i++) {
            deque.addLast(i);
        }

        for (int i = 4; i >= 0; i--) {
            assertEquals(i, deque.removeLast(), "Elements should come in reverse order.");
        }

        assertTrue(deque.isEmpty(), "Deque should be empty.");
    }

    @Test
    @SuppressWarnings("PMD.JUnitTestContainsTooManyAsserts")
    void iteration() {

        deque.addLast(2);
        deque.addLast(3);
        deque.addLast(4);
        deque.addFirst(1);

        int y = 1;
        for (int x: deque) {
            assertEquals(x, y, "Deque should contain integers increasing one by one.");
            y++;
        }
        assertEquals(5, y, "The for loop should have run.");
    }
}
