import org.junit.jupiter.api.Test;

import static org.fest.assertions.Assertions.assertThat;

/**
 * Created by USER on 2017-01-28.
 */
class PercolationIsFullTest {
    @org.junit.jupiter.api.BeforeEach
    void setUp() {

    }

    @Test
    public void shouldReturnTrueForConnectedSite()
    {
        //given
        int n=3;
        Percolation percolation = new Percolation(n);
        percolation.open(1,3);
        percolation.open(2,3);
        int row =2;
        int col =3;

        //when
        boolean isFull = percolation.isFull(row, col);

        //then
        assertThat(isFull).isTrue();

    }

    @Test
    public void shouldReturnFalseForNotConnectedSite()
    {
        //given
        int n=3;
        Percolation percolation = new Percolation(n);
        percolation.open(1,3);
        percolation.open(2,3);
        int row =3;
        int col =3;

        //when
        boolean isFull = percolation.isFull(row, col);

        //then
        assertThat(isFull).isFalse();

    }


    @Test
    public void shouldReturnFalseForNotConnectedSiteOnTheBottom()
    {
        //given
        int n=3;
        Percolation percolation = new Percolation(n);

        // last column percolates
        percolation.open(1,3);
        percolation.open(2,3);
        percolation.open(3,3);

        // first column does not
        percolation.open(2,1);
        percolation.open(3,1);

        int row =3;
        int col =1;

        //when
        boolean isFull = percolation.isFull(row, col);

        //then
        assertThat(isFull).isFalse();

    }
}