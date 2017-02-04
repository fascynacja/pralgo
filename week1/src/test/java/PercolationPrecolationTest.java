import org.fest.assertions.Assertions;import org.junit.jupiter.api.Test;

import static org.fest.assertions.Assertions.assertThat;

/**
 * Created by USER on 2017-01-28.
 */
class PercolationPrecolationTest {
    @org.junit.jupiter.api.BeforeEach
    void setUp() {

    }

    @Test
    public void shouldPrecolateForAllSitesInFirstColumnOpen()
    {
        //given
        int n = 4;

        //when
        Percolation prelocation = new Percolation(n);
        prelocation.open(1,1);
        prelocation.open(2,1);
        prelocation.open(3,1);
        prelocation.open(4,1);


        //then
        Assertions.assertThat(prelocation.percolates()).isTrue();
    }

    @Test
    public void shouldNotPrecolateFor3SitesInFirstColumnOpen_WithoutBottom()
    {
        //given
        int n = 4;

        //when
        Percolation prelocation = new Percolation(n);
        prelocation.open(1,1);
        prelocation.open(2,1);
        prelocation.open(3,1);


        //then
        Assertions.assertThat(prelocation.percolates()).isFalse();
    }

    @Test
    public void shouldPrecolateFor3SitesInFirstColumnOpen_WithoutTop()
    {
        //given
        int n = 4;

        //when
        Percolation prelocation = new Percolation(n);
        prelocation.open(2,1);
        prelocation.open(3,1);
        prelocation.open(4,1);


        //then
        Assertions.assertThat(prelocation.percolates()).isFalse();
    }

    @Test
    public void shouldPerColateFor1SizeGridWithOpenSite()
    {
        //given
        int n = 1;
        Percolation prelocation = new Percolation(n);

        //when
        prelocation.open(1,1);

        //then
        Assertions.assertThat(prelocation.percolates()).isTrue();
    }

    @Test
    public void shouldNotPerColateFor1SizeGridWithClosedSite()
    {
        //given
        int n = 1;
        Percolation prelocation = new Percolation(n);

        //when

        //then
        Assertions.assertThat(prelocation.percolates()).isFalse();
    }



}