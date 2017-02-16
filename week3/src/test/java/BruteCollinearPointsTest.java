import org.junit.jupiter.api.Test;

import static org.fest.assertions.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by USER on 2017-02-16.
 */
class BruteCollinearPointsTest {

    @Test
    public void shouldThrowExceptionWhenArgumentIsNull()
    {
        //given

        //when
        Throwable exception = assertThrows(NullPointerException.class, () -> {
            new BruteCollinearPoints(null);
        });

        //then
        assertThat(exception).isInstanceOf(NullPointerException.class);
    }

    @Test
    public void shouldThrowExceptionWhenAnyElementOfArrayIsNull()
    {
        //given
        Point[] points = new Point[4];
        addPoint(points, 1,2, 0);
        addPoint(points, 4,5, 2);
        addPoint(points, 1,8, 3);
        //when
        Throwable exception = assertThrows(NullPointerException.class, () -> {
            new BruteCollinearPoints(points);
        });

        //then
        assertThat(exception).isInstanceOf(NullPointerException.class);
    }

    @Test
    public void shouldThrowExceptionWhenAnyElementOfArrayIsRepeated()
    {
        //given
        Point[] points = new Point[4];
        addPoint(points, 1,2, 0);
        addPoint(points, 1,6, 1);
        addPoint(points, 4,5, 2);
        addPoint(points, 1,2, 3);
        //when
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
            new BruteCollinearPoints(points);
        });

        //then
        assertThat(exception).isInstanceOf(IllegalArgumentException.class);
    }


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
        BruteCollinearPoints bruteCollinearPoints = new BruteCollinearPoints(points);
        LineSegment[] segments = bruteCollinearPoints.segments();

        //then
        assertThat(segments).hasSize(1);
        assertThat(bruteCollinearPoints.numberOfSegments()).isEqualTo(1);
    }

    @Test
    public void shouldReturnNotOneLineSegment()
    {
        //given
        Point[] points =
                createPoints(
                        11219,6140,1773,6140,11759,6140,1238,6140,3798,10339,20409,10339,3908,10339,4905,10339,6655,3126,2280,3126,11549,3126,1457,3126,5332,10232,1905,10232,2201,10232,14147,10232,19412,16761,3148,16761,12347,16761,19128,16761,1723,14330,1192,14330,15142,14330,7005,14330,17835,3956,17280,3956,12584,3956,16712,3956,14390,18394,1781,18394,14189,18394,6725,18394,11740,14461,4361,14461,10624,14461,6466,14461,18756,18630,16448,18630,20990,18630,19361,18630,15440,9886,19282,9886,3635,9886,4590,9886,3462,5453,9510,5453,8747,5453,10282,5453,5787,16784,9008,16784,5505,16784,12187,16784,10618,12721,14493,12721,9791,12721,18910,12721,1173,6889,19585,6889,16710,6889,15500,6889,14225,17159,1085,17159,19576,17159,20028,17159,17569,18675,17372,18675,6795,18675,18393,18675,13373,3814,2068,3814,15795,3814,6224,3814,3881,15773,5940,15773,8215,15773,20547,15773,16703,1877,19923,1877,20689,1877,2283,1877,10486,6123,20715,6123,2051,6123,10191,6123,1157,13507,10552,13507,8051,13507,3930,13507,1384,5505,18355,5505,10243,5505,12965,5505,20523,9135,9793,9135,14387,9135,6352,9135,16981,7659,5837,7659,4503,7659,4109,7659,14568,14170,19389,14170,19946,14170,13394,14170,6214,15500,7115,15500,13277,15500,2828,15500,7521,2491,10077,2491,11021,2491,17928,2491,14319,2070,12201,2070,19652,2070,1447,2070,12802,15261,18392,15261,4381,15261,4237
                );


        //when
        BruteCollinearPoints bruteCollinearPoints = new BruteCollinearPoints(points);
        LineSegment[] segments = bruteCollinearPoints.segments();

        //then
        assertThat(segments).hasSize(1);
        assertThat(bruteCollinearPoints.numberOfSegments()).isEqualTo(1);
    }

    private Point[] createPoints(int... coords) {
        Point[] points = new Point[coords.length/2];
        for(int i=0; i<coords.length/2; i++ )
        {
            points[i] = new Point(coords[i*2], coords[2*i+1]);
        }
        return points;
    }


    private void addPoint(Point[] points, int x, int y, int index ) {
        points[index] = new Point(x,y);
    }
}