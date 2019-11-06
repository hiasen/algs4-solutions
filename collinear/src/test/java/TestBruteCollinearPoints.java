import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TestBruteCollinearPoints {

    private Point[] createCollinearPoints() {
        Point[] points = new Point[4];
        for (int i = 0; i < 4; i++) {
            points[i] = new Point(i, i);
        }
        return points;
    }

    @Test
    void test() {
        Point[] points = createCollinearPoints();
        BruteCollinearPoints bruteCollinearPoints = new BruteCollinearPoints(points);
        int numSeq = bruteCollinearPoints.numberOfSegments();
        assertEquals(1, numSeq, "Numer of line segments should be 1.");
        LineSegment[] lineSegments = bruteCollinearPoints.segments();
    }

    @Test
    void argumentToConstructorIsNull() {
        assertThrows(IllegalArgumentException.class, () -> new BruteCollinearPoints(null),
                "Null in argument to BruteForceCollinearPoints constructor should throw exceptions.");
    }

    @Test
    void nullInPointsArray() {
        Point[] points = new Point[10];
        assertThrows(IllegalArgumentException.class, () -> new BruteCollinearPoints(points));
    }
}
