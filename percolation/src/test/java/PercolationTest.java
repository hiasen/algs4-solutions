import org.junit.jupiter.api.Test;


import org.junit.jupiter.api.function.Executable;

import static org.junit.jupiter.api.Assertions.*;


public class PercolationTest {

    void assertThrowsIllegalArgument(Executable thunk) {
        assertThrows(IllegalArgumentException.class, thunk);
    }

    @Test
    void throwsIllegalArgumentExceptionInConstructor() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new Percolation(-1)
                );
    }
    @Test
    void throwsIllegalArgumentExceptionIfOutsideRange() {
        Percolation percolation = new Percolation(10);
        assertThrowsIllegalArgument(() -> percolation.open(11, 1));
        assertThrowsIllegalArgument(() -> percolation.open(1, 11));
        assertThrowsIllegalArgument(() -> percolation.open(0, 1));
        assertThrowsIllegalArgument(() -> percolation.open(1, 0));

        assertThrowsIllegalArgument(() -> percolation.isOpen(11, 1));
        assertThrowsIllegalArgument(() -> percolation.isOpen(1, 11));
        assertThrowsIllegalArgument(() -> percolation.isOpen(0, 1));
        assertThrowsIllegalArgument(() -> percolation.isOpen(1, 0));

        assertThrowsIllegalArgument(() -> percolation.isFull(11, 1));
        assertThrowsIllegalArgument(() -> percolation.isFull(1, 11));
        assertThrowsIllegalArgument(() -> percolation.isFull(0, 1));
        assertThrowsIllegalArgument(() -> percolation.isFull(1, 0));
    }
    @Test
    void testOpen() {
        Percolation percolation = new Percolation(10);

        assertFalse(percolation.isOpen(1,1));
        assertEquals(percolation.numberOfOpenSites(), 0);

        percolation.open(1,1);
        assertTrue(percolation.isOpen(1,1));
        assertEquals(percolation.numberOfOpenSites(), 1);
    }
    @Test
    void noOpenedDoesNotPercolate() {
        Percolation percolation = new Percolation(10);
        assertFalse(percolation.percolates());
    }

    @Test
    void singleSite() {
        Percolation percolation = new Percolation(1);
        assertFalse(percolation.percolates());

        percolation.open(1,1);
        assertTrue(percolation.percolates());
    }

    @Test
    void twoByTwo() {
        Percolation percolation = new Percolation(2);
        assertFalse(percolation.percolates());

        percolation.open(1,1);
        percolation.open(2,2);
        assertFalse(percolation.percolates());

        percolation.open(1,2);
        assertTrue(percolation.percolates());
    }

    @Test
    void threeByThree() {
        Percolation percolation = new Percolation(3);
        assertFalse(percolation.percolates());
        percolation.open(2,1);
        assertFalse(percolation.isFull(2, 1));
        percolation.open(1,1);
        assertTrue(percolation.isFull(2, 1));
        percolation.open(2,2);
        percolation.open(2,3);
        percolation.open(3,3);
        assertTrue(percolation.percolates());
    }
}
