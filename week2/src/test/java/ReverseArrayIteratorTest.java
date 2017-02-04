import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.fest.assertions.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by USER on 2017-02-04.
 */
class ReverseArrayIteratorTest {

    @Test
    public void shouldIterateOverRandomizedQueue()
    {
        //given
        RandomizedQueue<Integer> queue = new RandomizedQueue<>();

        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);

        //when
        Iterator iterator = queue.iterator();

        //then
        assertThat(iterator.hasNext()).isTrue();

        List<Integer> integers = new ArrayList<>();

        for(Integer i : queue)
        {
            integers.add(i);
        }

        assertThat(integers).hasSize(3);
    }

}