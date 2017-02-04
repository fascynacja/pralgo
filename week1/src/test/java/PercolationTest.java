import edu.princeton.cs.algs4.Stack;
import org.junit.jupiter.api.Test;

import java.util.Iterator;

import static org.fest.assertions.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
/**
 * Created by USER on 2017-01-28.
 */
class PercolationTest {
    @org.junit.jupiter.api.BeforeEach
    void setUp() {

    }


    @Test
    public void test()
    {
        Stack<String> stack = new Stack<>();
        Iterator<String> iterator = stack.iterator();

    }
   /* @Test
    public void shouldInitializeGridith4Size()
    {
        //given
        int n = 4;

        //when
        Percolation prelocation = new Percolation(n);

        //then
        assertThat(prelocation.grid).hasSize(n);
        assertThat(prelocation.grid[0]).hasSize(n);
    }

    @Test
    public void shouldInitializeUnionComponent()
    {
        //given
        int n = 4;

        //when
        Percolation prelocation = new Percolation(n);

        //then

        // assert that virtual top site is connected to any site from the top row
        assertThat(prelocation.union.connected(n*n, PercolationOpenTest.mapTo1D(1,2,n))).isTrue();
        assertThat(prelocation.union4Full.connected(n*n, PercolationOpenTest.mapTo1D(1,2,n))).isTrue();

        // assert that virtual bottom site is connected to any site from the bottom row
        assertThat(prelocation.union.connected(n*n+1, PercolationOpenTest.mapTo1D(n,1,n))).isFalse();
    }

    @Test
    public void shouldThrowExceptionWhenSizeIs0()
    {
        //given
        int n = 0;

        //when
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
            Percolation prelocation = new Percolation(n);
        });

        //then
    }

    @Test
    public void shouldThrowExceptionWhenSizeIsNegative()
    {
        //given
        int n = -5;

        //when
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
            Percolation prelocation = new Percolation(n);
        });

        //then
    }

    @Test
    public void shouldInitializeGridithWithBlockedSites()
    {
        //given
        int n = 1;

        //when
        Percolation prelocation = new Percolation(n);

        //then
        assertThat(prelocation.grid[0][0]).isFalse();
    }


    @org.junit.jupiter.api.Test
    void open() {

    }

    @org.junit.jupiter.api.Test
    void isOpen() {

    }

    @org.junit.jupiter.api.Test
    void isFull() {

    }

    @org.junit.jupiter.api.Test
    void numberOfOpenSites() {

    }

    @org.junit.jupiter.api.Test
    void percolates() {

    }*/

}