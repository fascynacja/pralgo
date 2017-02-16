import org.junit.jupiter.api.Test;

import static org.fest.assertions.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by USER on 2017-02-16.
 */
class FastCollinearPointsTest {
    @Test
    public void shouldReturnOneLineSegment()
    {
        //given
        Point[] points =
                createPoints(
                        0,0,
                        20,20,
                        2,4,
                        3,8,
                        4,12,
                        5,16
                );


        //when
        FastCollinearPoints cp = new FastCollinearPoints(points);
        LineSegment[] segments = cp.segments();

        //then
        assertThat(segments).hasSize(1);
        assertThat(cp.numberOfSegments()).isEqualTo(1);
    }

    private Point[] createPoints(int... coords) {
        Point[] points = new Point[coords.length/2];
        for(int i=0; i<coords.length/2; i++ )
        {
            points[i] = new Point(coords[i*2], coords[2*i+1]);
        }
        return points;
    }

}