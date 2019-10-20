import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;


class PercolationTest {

    void assertThrowsIllegalArgument(Executable thunk) {
        assertThrows(IllegalArgumentException.class, thunk, "Should throw IllegalArgumentException");
    }

    void assertPercolates(Percolation percolation) {
        assertTrue(percolation.percolates(), "This system should percolate.");
    }

    @Test
    void constructor_ThrowsIllegalArgumentException_IfNIsNegative() {
        assertThrowsIllegalArgument(() -> new Percolation(-1));
    }

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
    void when_NewPercolation_Expect_AllSitesClosed() {
        final int n = 10;
        Percolation percolation = new Percolation(n);
        for (int row = 1; row <= n; row++) {
            for (int col = 1; col <= n; col++) {
                assertFalse(percolation.isOpen(row, col), "Site should not be open");
            }
        }
    }

    @Test
    void numberOfSites_IsZero_IfNewlyCreatedPercolation() {
        Percolation percolation = new Percolation(10);
        assertEquals(0, percolation.numberOfOpenSites(), "Number of open sites should be zero.");
    }

    @Test
    void numberOfSites_IsOne_AfterOpeningOneSite() {
        Percolation percolation = new Percolation(10);
        percolation.open(1, 1);
        assertEquals(1, percolation.numberOfOpenSites(), "numberOfOpenSites should be one.");
    }

    @Test
    void isOpen_True_AfterOpeningTheSite() {
        Percolation percolation = new Percolation(10);
        percolation.open(1, 1);
        assertTrue(percolation.isOpen(1,1), "The site should be open.");
    }

    @Test
    void percolates_false_IfNoOpenedSites() {
        Percolation percolation = new Percolation(10);
        assertFalse(percolation.percolates(), "A new percolation system should not percolate.");
    }

    @Test
    void should_percolate_When_OpenedSiteInSystemWithOnlyOneSite() {
        Percolation percolation = new Percolation(1);
        percolation.open(1,1);
        assertPercolates(percolation);
    }

    @Test
    void should_percolate_When_PathOpenedInTwoByTwoSystem() {
        Percolation percolation = new Percolation(2);
        percolation.open(1,1);
        percolation.open(2,2);
        percolation.open(1,2);

        assertPercolates(percolation);
    }

    @Test
    void should_percolate_When_PathOpenedInThreeByThreeSystem() {
        Percolation percolation = new Percolation(3);
        percolation.open(1,1);
        percolation.open(2,1);
        percolation.open(2,2);
        percolation.open(2,3);
        percolation.open(3,3);
        assertPercolates(percolation);
    }

    @Test
    void when_Percolating_DontBackwash() {
        // Test for recreating the backwash-bug
        Percolation p = new Percolation(3);
        p.open(1,1);
        p.open(2, 1);
        p.open(3,1);
        p.open(3,3);
        assertFalse(p.isFull(3,3), "This site should not be full.");
    }
}
