import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TestBruteForceCollinearPoints {

    @Test
    void test() {

        Point[] points = new Point[4];
        for (int i = 0; i < 4; i++) {
            points[i] = new Point(i, i);
        }
        BruteForceCollinearPoints bruteForceCollinearPoints = new BruteForceCollinearPoints(points);
        int numSeq = bruteForceCollinearPoints.numberOfSegments();
        assertEquals(1, numSeq, "Numer of line segments should be 1.");
        LineSegment[] lineSegments = bruteForceCollinearPoints.segments();
    }
}
