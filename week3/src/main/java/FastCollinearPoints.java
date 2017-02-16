import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class FastCollinearPoints {

    private List<LineSegment> segments =  new ArrayList<>();

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
        for(int i =0; i< points.length; i++)
        {
            Point pCurrent = points[i];
            Point[] pointsSorted = Arrays.copyOf(points, points.length);
            Arrays.sort(pointsSorted, new Comparator<Point>() {
                @Override
                public int compare(Point o1, Point o2) {
                   return Double.compare(pCurrent.slopeTo(o1),pCurrent.slopeTo(o2) );
                }
            });

            double previousSlope = Double.NEGATIVE_INFINITY;
            int repeats =0;
            for(int sortedIndex =0; sortedIndex<points.length; sortedIndex++)
            {
                  double slopeToSorted = pCurrent.slopeTo(pointsSorted[sortedIndex]);
                  if(previousSlope == slopeToSorted)
                  {
                      repeats ++;
                  }
                  else
                  {
                      // repeats finished
                      if(repeats>=2)
                      {
                          createSegment(pCurrent, pointsSorted, repeats, sortedIndex
                          );

                      }
                      repeats=0;
                  }

                  previousSlope = slopeToSorted;
                  if(sortedIndex==points.length-1 && repeats>=2)
                  {
                      createSegment(pCurrent, pointsSorted, repeats, sortedIndex);
                  }
            }
        }
    }

    private void createSegment(Point pCurrent, Point[] pointsSorted, int repeats, int sortedIndexCurrent) {
        int numOfPoints = repeats+2;
        Point[] forSegment = new Point[numOfPoints];
        forSegment[0] = pCurrent;
        int sortedIndex = sortedIndexCurrent;
        for(int i =1; i< numOfPoints; i++)
        {
            forSegment[i]= pointsSorted[sortedIndex--] ;
        }

        if(checkDjacent(forSegment)) {


            LineSegment seg = new LineSegment(forSegment[0], forSegment[numOfPoints - 1]);
            segments.add(seg);
        }
    }

    private boolean checkDjacent(Point[] forSegment) {
        for(int i=0; i< forSegment.length-1 ; i++)
        {
            if(forSegment[i].compareTo(forSegment[i+1])==-1)
            {
                return false;
            }
        }
        return  true;
    }

    public int numberOfSegments() {
        if (segments == null) {
            return 0;
        }
        return segments.size();
    }

    public LineSegment[] segments() {
        return segments.toArray(new LineSegment[0]);
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