import java.util.ArrayList;
import java.util.Arrays;


/**
 * Find collinear points.
 */
public class FastCollinearPoints {
    private final ArrayList<LineSegment> lineSegmentArrayList = new ArrayList<>();

    /**
     * Find collinear points given array of distinct points.
     * @param points The distinct points to investigate.
     */
    public FastCollinearPoints(Point[] points) {

        if (points == null) {
            throw new IllegalArgumentException("Point array should not be null.");
        }
        for (int i = 0; i < points.length; i++) {
            if (points[i] == null) {
                throw new IllegalArgumentException("Points in the array should be non null.");
            }
            findLineSegmentsWithGivenOrigin(i, points);
        }
    }

    private void findLineSegmentsWithGivenOrigin(int originIndex, Point[] points) {
        final Point origin = points[originIndex];
        Arrays.sort(points, originIndex+1, points.length, origin.slopeOrder());

        for (int index = originIndex+1; index < points.length;) {
            Point min = origin;
            Point max = origin;
            int firstInSegment = index;
            final double firstSlope = origin.slopeTo(points[firstInSegment]);


            while (index < points.length && origin.slopeTo(points[index]) == firstSlope) {
                Point p = points[index];
                if (p.compareTo(min) < 0) {
                    min = p;
                }
                if (p.compareTo(max) > 0) {
                    max = p;
                }
                index++;
            }
            if (index - firstInSegment >= 3) {
                lineSegmentArrayList.add(new LineSegment(min, max));
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
