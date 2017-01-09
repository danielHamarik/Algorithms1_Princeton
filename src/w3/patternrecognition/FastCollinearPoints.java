package w3.patternrecognition;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Daniel on 1/9/2017.
 */

public class FastCollinearPoints {

    private LineSegment[] segments;

    /**
     * Find all line segments containing 4 points.
     *
     * @param points array of corresponding points
     */
    public FastCollinearPoints(Point[] points) {
        Point[] pointsCopy = copyArray(points);
        Arrays.sort(pointsCopy);
        int length = pointsCopy.length;
        Point[] helper = Arrays.copyOf(pointsCopy, length);
        ArrayList<LineSegment> lineSegments = new ArrayList<>();
        for (int i = 0; i < length; i++) {
            Point p = pointsCopy[i];
            Arrays.sort(helper);
            Arrays.sort(helper, p.slopeOrder());
            if (p.slopeTo(helper[1]) == Double.NEGATIVE_INFINITY)
                throw new IllegalArgumentException();
            int posStart = 1;
            int posEnd = posStart;
            while (posStart < length) {
                while (posEnd < length && p.slopeTo(helper[posEnd]) == p.slopeTo(helper[posStart])) posEnd++;
                if (posEnd - posStart >= 3) {
                    if (helper[posStart].compareTo(p) >= 0) {
                        lineSegments.add(new LineSegment(p, helper[posEnd - 1]));
                    }
                }
                posStart = posEnd;
            }
        }
        segments = lineSegments.toArray(new LineSegment[lineSegments.size()]);
    }

    private Point[] copyArray(Point[] points) {
        Point[] copy = new Point[points.length];
        for (int i = 0; i < copy.length; i++) {
            if (points[i] == null) throw new NullPointerException();
            copy[i] = points[i];
        }
        return copy;
    }


    /**
     * Return the number of line segments.
     *
     * @return number of found line segments.
     */
    public int numberOfSegments() {
        return segments.length;
    }

    /**
     * Return array of line segments.
     *
     * @return LineSegment array.
     */
    public LineSegment[] segments() {
        return Arrays.copyOf(segments, segments.length);
    }
}