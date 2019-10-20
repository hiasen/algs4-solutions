import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class PointAsComparable {
    private static final Point thisPoint = new Point(0, 0);

    void assertThisPointIsEqualTo(Point other) {
        int compared = thisPoint.compareTo(other);
        assertEquals(0, compared, "Points should be equal.");
    }

    void assertThisPointIsSmallerThan(Point other) {
        int compared = thisPoint.compareTo(other);
        assertEquals(-1, compared, "This point should be smaller than other point.");
    }

    void assertThisPointIsLargerThan(Point other) {
        int compared = thisPoint.compareTo(other);
        assertEquals(1, compared, "This point should be larger than other point.");
    }

    @Test
    void equalPointsShouldCompareEqual() {
        assertThisPointIsEqualTo(new Point(0,0));
    }

    @Test
    void thisObjectHasSmallerY() {
        assertThisPointIsSmallerThan(new Point(0,1));
    }

    @Test
    void thisObjectHasLargerY() {
        assertThisPointIsLargerThan(new Point(0,-1));
    }

    @Test
    void thisObjectHasEqualYAndSmallerX() {
        assertThisPointIsSmallerThan(new Point(1,0));
    }

    @Test
    void thisObjectHasEqualYAndLargerX() {
        assertThisPointIsLargerThan(new Point(-1,0));
    }

    @Test
    void anotherTest() {
        assertThisPointIsSmallerThan(new Point(1,1));
    }

    @Test
    void anotherTest2() {
        assertThisPointIsLargerThan(new Point(-1,-1));
    }
}
