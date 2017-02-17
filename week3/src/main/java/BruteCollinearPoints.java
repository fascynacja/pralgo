import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;
import java.util.List;

public class BruteCollinearPoints {

    private List<LineSegment> segments = new ArrayList<>();

    public BruteCollinearPoints(Point[] points) {
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
        BruteCollinearPoints collinear = new BruteCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }

    public int numberOfSegments() {
        return segments.size();
    }


    public LineSegment[] segments() {
        return segments.toArray(new LineSegment[0]);
    }

    private void createSegments(Point[] points) {
        for (int i = 0; i < points.length; i++) {
            Point p1 = points[i];
            for (int j = 0; j < points.length; j++) {
                Point p2 = points[j];
                for (int k = 0; k < points.length; k++) {

                    Point p3 = points[k];
                    for (int n = 0; n < points.length; n++) {
                        Point p4 = points[n];
                        if (i != j && i != k && i != n && j != k && j != n && k != n) {
                            double p1slopeToP2 = p1.slopeTo(p2);
                            if (Double.compare(p1slopeToP2 ,p1.slopeTo(p3))==0 && Double.compare(p1slopeToP2 , p1.slopeTo(p4))==0) {
                                if (p1.compareTo(p2) == -1 && p2.compareTo(p3) == -1 && p3.compareTo(p4) == -1) {
                                    createSegment(p1, p4);
                                }
                            }
                        }
                    }
                }

            }
        }
    }

    private void createSegment(Point p1, Point p2) {

        LineSegment segment = new LineSegment(p1, p2);
        segments.add(segment);
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

}