import static java.util.Collections.max;
import static java.util.Collections.min;

import java.util.ArrayList;

/**
 * Finds all LineSegments of at least 4 collinear points
 * using a brute force method.
 */
public class BruteCollinearPoints {
    private final ArrayList<LineSegment> lineSegmentArrayList = new ArrayList<>();

    /**
     * Find collinear points given array of distinct points.
     * @param points The distinct points to investigate.
     */
    public BruteCollinearPoints(Point[] points) {
        if (points == null) {
            throw new IllegalArgumentException("Point array should not be null.");
        }
        for (int i = 0; i < points.length; i++) {
            Point p = points[i];
            if (p == null) {
                throw new IllegalArgumentException("Points in the array should be non null.");
            }
            for (int j = i + 1; j < points.length; j++) {
                double slope1 = p.slopeTo(points[j]);
                for (int k = j + 1; k < points.length; k++) {
                    double slope2 = p.slopeTo(points[k]);
                    for (int l = k + 1; l < points.length; l++) {
                        double slope3 = p.slopeTo(points[k]);
                        if (slope1 == slope2 && slope1 == slope3) {
                            ArrayList<Point> pointsOnLine = new ArrayList<>();
                            pointsOnLine.add(points[i]);
                            pointsOnLine.add(points[j]);
                            pointsOnLine.add(points[k]);
                            pointsOnLine.add(points[l]);
                            lineSegmentArrayList.add(new LineSegment(min(pointsOnLine), max(pointsOnLine)));
                        }
                    }
                }
            }
        }
    }

    /**
     * Number of line segments in the array returned from segments method.
     * @return int
     */
    public int numberOfSegments() {
        return lineSegmentArrayList.size();
    }

    /**
     * Get array of line segments.
     * @return Array of line segments
     */
    public LineSegment[] segments() {
        LineSegment[] lineSegments = new LineSegment[lineSegmentArrayList.size()];
        lineSegmentArrayList.toArray(lineSegments);
        return lineSegments;

    }
}
