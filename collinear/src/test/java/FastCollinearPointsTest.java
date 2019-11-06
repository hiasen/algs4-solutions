import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class FastCollinearPointsTest {
    private Point[] createCollinearPoints() {
        return createCollinearPoints(0, 0, 1,1, 4);
    }

    private Point[] createCollinearPoints(int x0, int y0, int dx, int dy, int number) {
        Point[] points = new Point[number];
        for (int i = 0; i < number; i++) {
            points[i] = new Point(x0+i*dx, y0+i*dy);
        }
        return points;
    }
    @Test
    void fafa() {
        FastCollinearPoints fastCollinearPoints = new FastCollinearPoints(createCollinearPoints());
        assertEquals(1, fastCollinearPoints.numberOfSegments());
    }

    @Test
    void asdfaswdf() {

    }
}
