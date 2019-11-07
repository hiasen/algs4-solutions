import java.util.ArrayList;
import java.util.Arrays;


/**
 * Find collinear points.
 */
public class FastCollinearPoints {
    private ArrayList<MyLineSegment> lineSegmentArrayList = new ArrayList<>();
    private LineSegment[] lineSegments;

    /**
     * Find collinear points given array of distinct points.
     * @param points The distinct points to investigate.
     */
    public FastCollinearPoints(Point[] points) {

        if (points == null) {
            throw new IllegalArgumentException("Point array should not be null.");
        }
        for (Point p: points) {
            if (p == null) {
                throw new IllegalArgumentException("Points in the array should be non null.");
            }
        }
        Point[] sortedPoints = points.clone();
        Arrays.sort(sortedPoints);
        for (int i = 0; i < sortedPoints.length - 1; i++) {
            if (sortedPoints[i].compareTo(sortedPoints[i+1]) == 0) {
                throw new IllegalArgumentException("Duplicate points!");
            }
        }
        for (Point point : points) {
            Arrays.sort(sortedPoints, point.slopeOrder());
            findLineSegmentsWithGivenOrigin(point, sortedPoints);
        }
        makeUnique();
        createSegmentsArray();
    }

    private void findLineSegmentsWithGivenOrigin(Point origin, Point[] points) {

        for (int index = 0; index < points.length;) {
            Point min = origin;
            Point max = origin;
            final int firstInSegment = index;
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
                MyLineSegment newSegment = new MyLineSegment(min, max);
                lineSegmentArrayList.add(newSegment);
            }
        }
    }

    static private class MyLineSegment implements Comparable<MyLineSegment> {
        Point p;
        Point q;
        MyLineSegment(Point p, Point q) {
            this.p = p;
            this.q = q;
        }
        LineSegment toLineSegment() {
            return new LineSegment(p, q);
        }

        @Override
        public int compareTo(MyLineSegment myLineSegment) {
            int a = p.compareTo(myLineSegment.p);
            if (a != 0) {
                return a;
            }
            return q.compareTo(myLineSegment.q);
        }
    }

    private void makeUnique() {
        if (lineSegmentArrayList.size() == 0) {
            return;
        }
        ArrayList<MyLineSegment> unique = new ArrayList<>();
        lineSegmentArrayList.sort(null);
        MyLineSegment last = lineSegmentArrayList.get(0);
        unique.add(last);
        for (MyLineSegment ls: lineSegmentArrayList) {
            if (ls.compareTo(last) != 0) {
                last = ls;
                unique.add(last);
            }
        }
        lineSegmentArrayList = unique;
    }

    private void createSegmentsArray() {
        lineSegments = new LineSegment[lineSegmentArrayList.size()];
        for (int i = 0; i < lineSegments.length; i++) {
            lineSegments[i] = lineSegmentArrayList.get(i).toLineSegment();
        }
        lineSegmentArrayList.clear();
    }

    /**
     * Number of line segments in the array returned from segments method.
     * @return int
     */
    public int numberOfSegments() {
        return lineSegments.length;
    }

    /**
     * Get array of line segments.
     * @return Array of line segments
     */
    public LineSegment[] segments() {
        return lineSegments.clone();
    }
}
