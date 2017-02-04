import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by USER on 2017-01-29.
 */
class PercolationStatsConstructTest {

    @Test
    public void shouldThrowExceptionWhenSizeNotInRange() {
        //given
        int n = 0;
        int anyTrials = 5;

        //when
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
            PercolationStats prelocation = new PercolationStats(n, anyTrials);
        });

        //then
    }

    @Test
    public void shouldThrowExceptionWhenTrialsNotInRange() {
        //given
        int anySize = 5;
        int trials = 0;

        //when
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
            PercolationStats prelocation = new PercolationStats(anySize, trials);
        });

        //then
    }

    @Test
    public void shouldNotThrowExceptionParamsInRange() {
        //given
        int anySize = 5;
        int trials = 55;

        //when
        PercolationStats prelocation = new PercolationStats(anySize, trials);

        //then
        // no exception
    }
}