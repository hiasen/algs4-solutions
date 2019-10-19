import org.junit.jupiter.api.Test;


import org.junit.jupiter.api.function.Executable;

import static org.junit.jupiter.api.Assertions.*;


class PercolationTest {

    void assertThrowsIllegalArgument(Executable thunk) {
        assertThrows(IllegalArgumentException.class, thunk);
    }

    @Test
    void Constructor_ThrowsIllegalArgumentException_IfNIsNegative() {
        assertThrowsIllegalArgument(() -> new Percolation(-1));
    }

    // open
    @Test
    void open_ThrowsIllegalArgumentException_IfRowLargerThanN() {
        Percolation percolation = new Percolation(10);
        assertThrowsIllegalArgument(() -> percolation.open(11, 1));
    }

    @Test
    void open_ThrowsIllegalArgumentException_IfColLargerThanN() {
        Percolation percolation = new Percolation(10);
        assertThrowsIllegalArgument(() -> percolation.open(1, 11));
    }
    @Test
    void open_ThrowsIllegalArgumentException_IfRowIsZero() {
        Percolation percolation = new Percolation(10);
        assertThrowsIllegalArgument(() -> percolation.open(0, 1));
    }

    @Test
    void open_ThrowsIllegalArgumentException_IfColIsZero() {
        Percolation percolation = new Percolation(10);
        assertThrowsIllegalArgument(() -> percolation.open(1, 0));
    }
    // isOpen
    @Test
    void isOpen_ThrowsIllegalArgumentException_IfRowLargerThanN() {
        Percolation percolation = new Percolation(10);
        assertThrowsIllegalArgument(() -> percolation.isOpen(11, 1));
    }

    @Test
    void isOpen_ThrowsIllegalArgumentException_IfColLargerThanN() {
        Percolation percolation = new Percolation(10);
        assertThrowsIllegalArgument(() -> percolation.isOpen(1, 11));
    }
    @Test
    void isOpen_ThrowsIllegalArgumentException_IfRowIsZero() {
        Percolation percolation = new Percolation(10);
        assertThrowsIllegalArgument(() -> percolation.isOpen(0, 1));
    }

    @Test
    void isOpen_ThrowsIllegalArgumentException_IfColIsZero() {
        Percolation percolation = new Percolation(10);
        assertThrowsIllegalArgument(() -> percolation.isOpen(1, 0));
    }
    // isFull
    @Test
    void isFull_ThrowsIllegalArgumentException_IfRowLargerThanN() {
        Percolation percolation = new Percolation(10);
        assertThrowsIllegalArgument(() -> percolation.isFull(11, 1));
    }

    @Test
    void isFull_ThrowsIllegalArgumentException_IfColLargerThanN() {
        Percolation percolation = new Percolation(10);
        assertThrowsIllegalArgument(() -> percolation.isFull(1, 11));
    }
    @Test
    void isFull_ThrowsIllegalArgumentException_IfRowIsZero() {
        Percolation percolation = new Percolation(10);
        assertThrowsIllegalArgument(() -> percolation.isFull(0, 1));
    }

    @Test
    void isFull_ThrowsIllegalArgumentException_IfColIsZero() {
        Percolation percolation = new Percolation(10);
        assertThrowsIllegalArgument(() -> percolation.isFull(1, 0));
    }

    @Test
    void When_NewPercolation_Expect_AllSitesClosed() {
        final int n = 10;
        Percolation percolation = new Percolation(n);
        for (int row = 1; row <= n; row++) {
            for (int col = 1; col <= n; col++) {
                assertFalse(percolation.isOpen(row, col));
            }
        }
    }

    @Test
    void numberOfSites_IsZero_IfNewlyCreatedPercolation() {
        Percolation percolation = new Percolation(10);
        assertEquals(0, percolation.numberOfOpenSites());
    }
    @Test
    void numberOfSites_IsOne_AfterOpeningOneSite() {
        Percolation percolation = new Percolation(10);
        percolation.open(1, 1);
        assertEquals(1, percolation.numberOfOpenSites());
    }
    @Test
    void isOpen_True_AfterOpeningTheSite() {
        Percolation percolation = new Percolation(10);
        percolation.open(1, 1);
        assertTrue(percolation.isOpen(1,1));
    }
    @Test
    void percolates_false_IfNoOpenedSites() {
        Percolation percolation = new Percolation(10);
        assertFalse(percolation.percolates());
    }

    @Test
    void Should_percolate_When_OpenedSiteInSystemWithOnlyOneSite() {
        Percolation percolation = new Percolation(1);
        percolation.open(1,1);
        assertTrue(percolation.percolates());
    }

    @Test
    void Should_percolate_When_PathOpenedInTwoByTwoSystem() {
        Percolation percolation = new Percolation(2);
        percolation.open(1,1);
        percolation.open(2,2);
        percolation.open(1,2);

        assertTrue(percolation.percolates());
    }

    @Test
    void Should_percolate_When_PathOpenedInThreeByThreeSystem() {
        Percolation percolation = new Percolation(3);
        percolation.open(1,1);
        percolation.open(2,1);
        percolation.open(2,2);
        percolation.open(2,3);
        percolation.open(3,3);
        assertTrue(percolation.percolates());
    }

    @Test
    void When_Percolates_DontBackwash() {
        // Test for recreating the backwash-bug
        Percolation p = new Percolation(3);
        p.open(1,1);
        p.open(2, 1);
        p.open(3,1);
        p.open(3,3);
        assertFalse(p.isFull(3,3));
    }
}
