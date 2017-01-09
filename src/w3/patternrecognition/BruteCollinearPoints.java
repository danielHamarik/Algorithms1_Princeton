package w3.patternrecognition;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Daniel on 1/9/2017.
 */
public class BruteCollinearPoints {

    private LineSegment[] segments;

    /**
     * Find all line segments containing 4 points.
     *
     * @param points array of corresponding points
     */
    public BruteCollinearPoints(Point[] points) {
        if (points == null) throw new NullPointerException();
        Point[] copy = copyArray(points);
        ArrayList<LineSegment> list = new ArrayList<>();
        Arrays.sort(copy);
        for (int i = 0; i < (copy.length - 1); i++) {
            if (copy[i].compareTo(copy[i+1]) == 0)
                throw new IllegalArgumentException();
            for (int j = i + 1; j < (copy.length); j++) {
                double slope1 = copy[i].slopeTo(copy[j]);
                for (int k = j + 1; k < (copy.length - 1); k++) {
                    double slope2 = copy[i].slopeTo(copy[k]);
                    if (slope1 == slope2) {
                        for (int l = k + 1; l < copy.length; l++) {
                            double slope3 = copy[i].slopeTo(copy[l]);
                            if (slope2 == slope3) {
                                list.add(new LineSegment(copy[i], copy[l]));
                            }
                        }
                    }
                }
            }
        }
        segments = list.toArray(new LineSegment[list.size()]);
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
     * @return number of found segments.
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