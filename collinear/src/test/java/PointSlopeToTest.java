import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class PointSlopeToTest {
    @Test
    void degenerativeLineSegment() {
        Point point = new Point(1, 2);
        Point other = new Point(1, 2);
        double slope = point.slopeTo(other);
        assertEquals(Double.NEGATIVE_INFINITY, slope,
                "Equal points should give slope of negative infinity.");
    }

    @Test
    void horizontal() {
        Point point = new Point(1, 2);
        Point other = new Point(10, 2);
        double slope = point.slopeTo(other);
        assertEquals(+0.0, slope,
                "Horizontal lines should give positive zero.");
    }

    @Test
    void horizontal2() {
        Point point = new Point(1, 2);
        Point other = new Point(-10, 2);
        double slope = point.slopeTo(other);
        assertEquals(+0.0, slope,
                "Horizontal lines should give positive zero.");
    }

    @Test
    void vertical() {
        Point point = new Point(1, 2);
        Point other = new Point(1, 1000);
        double slope = point.slopeTo(other);
        assertEquals(Double.POSITIVE_INFINITY, slope,
                "Vertical lines should give slope of positive infinity.");
    }

    @Test
    void testPositiveSlope() {
        Point point = new Point(1, 2);
        Point other = new Point(2, 3);
        double slope = point.slopeTo(other);
        assertEquals(1.0, slope,
                "Should have slope of 1.");
    }

    @Test
    void testNegativeSlope() {
        Point point = new Point(1, 2);
        Point other = new Point(2, 1);
        double slope = point.slopeTo(other);
        assertEquals(-1.0, slope,
                "Should have slope of -1.");
    }

}
