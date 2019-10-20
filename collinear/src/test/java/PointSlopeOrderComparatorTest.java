import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import java.util.Comparator;

class PointSlopeOrderComparatorTest {
    static private Comparator<Point> slopeComparator = new Point(0,0).slopeOrder();

    private int compare(Point point1, Point point2) {
        return slopeComparator.compare(point1, point2);
    }

    private void assertSmaller(Point p1, Point p2) {
        int compared = slopeComparator.compare(p1, p2);
        assertEquals(-1, compared,
                String.format("%s should be smaller than %s using %s as comparator.", p1, p2, slopeComparator));
    }

    private void assertLarger(Point p1, Point p2) {
        int compared = slopeComparator.compare(p1, p2);
        assertEquals(1, compared,
                String.format("%s should be larger than %s using %s as comparator.", p1, p2, slopeComparator));
    }

    private void assertCompareEqual(Point p1, Point p2) {
        int compared = slopeComparator.compare(p1, p2);
        assertEquals(0, compared,
                String.format("%s should be equal %s using %s as comparator.", p1, p2, slopeComparator));
    }

    @Test
    void testSmaller() {
        assertSmaller(new Point(1,1), new Point(1,2));
    }

    @Test
    void testLarger() {
        assertLarger(new Point(1,2), new Point(1,1));
    }

    @Test
    void testCompareEqual() {
        assertCompareEqual(new Point(1,1), new Point(2,2));
    }
}
