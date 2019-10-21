import java.util.ArrayList;

import static java.util.Collections.max;
import static java.util.Collections.min;

/**
 * Finds all LineSegments of at least 4 collinear points
 * using a brute force method.
 */
public class BruteForceCollinearPoints {
    private LineSegment[] lineSegments;
    /**
     * Find collinear points given array of distinct points.
     * @param points The distinct points to investigate.
     */
    public BruteForceCollinearPoints(Point[] points) {
        ArrayList<LineSegment> lineSegments2 = new ArrayList<>();
        for (int i = 0; i < points.length; i++) {
            Point p = points[i];
            for (int j = i+1; j < points.length; j++) {
                double slope1 = p.slopeTo(points[j]);
                for (int k = j+1; k < points.length; k++) {
                    double slope2 = p.slopeTo(points[k]);
                    for (int l = k+1; l < points.length; l++) {
                        double slope3 = p.slopeTo(points[k]);
                        if (slope1 == slope2 && slope1 == slope3) {
                            ArrayList<Point> pointsOnLine = new ArrayList<>();
                            pointsOnLine.add(points[i]);
                            pointsOnLine.add(points[j]);
                            pointsOnLine.add(points[k]);
                            pointsOnLine.add(points[l]);
                            lineSegments2.add(new LineSegment(min(pointsOnLine), max(pointsOnLine)));
                        }
                    }
                }
            }
        }
        lineSegments = new LineSegment[lineSegments2.size()];
        lineSegments2.toArray(lineSegments);
    }

    /**
     * Number of line segments in the array returned from segments method.
     * @return int
     */
    public int numberOfSegments() {
        return lineSegments.length;
    }

    public LineSegment[] segments() {
        return lineSegments;
    }
}
