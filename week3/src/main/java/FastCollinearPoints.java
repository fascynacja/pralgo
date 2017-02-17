import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FastCollinearPoints {

    private List<Point[]> segments = new ArrayList<>();

    public FastCollinearPoints(Point[] points) {
        nullCheck(points);
        nullElementCheck(points);
        repatedPointCheck(points);

        createSegments(points);
    }

    public static void main(String[] args) {

        // read the n points from a file
        In in = new In(args[0]);
        int n = in.readInt();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
        }

        // draw the points
        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        for (Point p : points) {
            p.draw();
        }
        StdDraw.show();

        // print and draw the line segments
        FastCollinearPoints collinear = new FastCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }

    private void createSegments(Point[] points) {
        for (int i = 0; i < points.length; i++) { // i loop

            Arrays.sort(points);
            Point pCurrent = points[i];
            Point[] smallArray = copyToSmallArray(i, points);
            smallArrayOperation(pCurrent, smallArray);
        }
    }

    private void smallArrayOperation(Point pCurrent, Point[] smallArray) {
        Arrays.sort(smallArray, pCurrent.slopeOrder());


        double previousSlope = Double.NEGATIVE_INFINITY;
        int repeats = 1;
        for (int k = 0; k < smallArray.length; k++) { // k loop
            double slope = pCurrent.slopeTo(smallArray[k]);
            if (Double.compare(previousSlope, slope) == 0) {
                repeats++;
            } else {
                // repeats finished
                if (repeats >= 3) {
                    createSegment(pCurrent, smallArray, repeats, k - 1);
                }
                repeats = 1;
            }

            previousSlope = slope;
            if (k == smallArray.length - 1 && repeats >= 3) {
                createSegment(pCurrent, smallArray, repeats, k);
            }
        }
    }

    private Point[] copyToSmallArray(int i, Point[] points) {
        // create an array which will have all the points from "points" without one point from index "i"
        Point[] pointsWithoutOne = new Point[points.length - 1];
        System.arraycopy(points, 0, pointsWithoutOne, 0, i);
        System.arraycopy(points, i + 1, pointsWithoutOne, i, points.length - i - 1);

        return pointsWithoutOne;
    }

    private void createSegment(Point pCurrent, Point[] points, int repeats, int index) {

        int numOfPoints = repeats + 1;

        Point[] forSegment = new Point[numOfPoints];
        forSegment[numOfPoints - 1] = pCurrent;

        for (int i = 0; i < numOfPoints - 1; i++) {
            forSegment[i] = points[index--];
        }
        Arrays.sort(forSegment);
        if (!segmentAlreadyAdded(forSegment)) {
            segments.add(new Point[]{forSegment[0], forSegment[numOfPoints - 1]});
        }


    }

    private boolean segmentAlreadyAdded(Point[] forSegment) {

        for (Point[] segment : segments) {

            if (forSegment[0].equals(segment[0]) && forSegment[forSegment.length - 1].equals(segment[1])) {
                return true;
            }
        }
        return false;
    }


    public int numberOfSegments() {
        if (segments == null) {
            return 0;
        }
        return segments.size();
    }

    public LineSegment[] segments() {
        LineSegment[] lineSegments = new LineSegment[segments.size()];
        int index = 0;
        for (Point[] points : segments) {
            LineSegment ls = new LineSegment(points[0], points[1]);
            lineSegments[index++] = ls;
        }
        return lineSegments;
    }

    private void nullElementCheck(Point[] points) {
        for (Point p : points) {
            if (p == null) {
                throw new NullPointerException("One of the points in array is null");
            }
        }
    }

    private void nullCheck(Point[] points) {
        if (points == null) {
            throw new NullPointerException();
        }
    }

    private void repatedPointCheck(Point[] points) {
        for (int i = 0; i < points.length; i++) {
            Point p1 = points[i];
            for (int j = 0; j < points.length; j++) {
                Point p2 = points[j];
                if (i != j) {
                    if (p1.compareTo(p2) == 0) {
                        throw new IllegalArgumentException(
                                "Two points have same coordinates: p1:" + p1.toString() + ", p2: " + p2.toString());
                    }
                }
            }
        }

    }


}