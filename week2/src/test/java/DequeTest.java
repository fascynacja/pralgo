import org.junit.jupiter.api.Test;

import java.util.Iterator;

import static org.fest.assertions.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;


class DequeTest {

    @Test
    public void shoulAddAndRemoveItems()
    {
        //given
        Deque<Integer> deque = new Deque<>();

        //when
        deque.addFirst(0);
        deque.removeLast();
        deque.addLast(3);
        deque.removeFirst();
        deque.addFirst(4);
        deque.removeFirst();
        deque.addLast(5);

        //then
        assertThat(deque.size()).isEqualTo(1);

        Iterator<Integer> iterator = deque.iterator();
        assertThat(iterator.hasNext()).isTrue();

        assertThat(iterator.next()).isEqualTo(5);
    }

}