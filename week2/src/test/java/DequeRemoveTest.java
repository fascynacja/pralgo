import org.junit.jupiter.api.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.fest.assertions.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Corner cases.
 * throw a java.util.NoSuchElementException if the client attempts to remove an item from an empty deque;
 */
class DequeRemoveTest {

    @Test
    public void shouldThrowNSEWhenRemovingFromEmpty()
    {
        //given
        Deque<String> deque = new Deque<>();

        //when
        Throwable exception = assertThrows(NoSuchElementException.class, () -> {
            deque.removeFirst();
        });

        //then
        assertThat(exception).isInstanceOf(NoSuchElementException.class);
    }
    @Test
    public void shouldThrowNSEWhenRemovingLastFromEmpty()
    {
        //given
        Deque<String> deque = new Deque<>();

        //when
        Throwable exception = assertThrows(NoSuchElementException.class, () -> {
            deque.removeLast();
        });

        //then
        assertThat(exception).isInstanceOf(NoSuchElementException.class);
    }


    @Test
    public void shouldRemoveTwiceFromFullQueueFirst()
    {
        //given
        Deque<Integer> deque = new Deque<>();
        init(deque,  1,2,3,4);

        //when
        deque.removeFirst();
        deque.removeFirst();

        //then
        assertThat(deque.size()).isEqualTo(2);

        Iterator<Integer> iterator = deque.iterator();
        assertThat(iterator.next()).isEqualTo(3);
        assertThat(iterator.next()).isEqualTo(4);
    }

    @Test
    public void shouldRemoveTwiceFromFullQueueLast()
    {
        //given
        Deque<Integer> deque = new Deque<>();
        init(deque,  1,2,3,4);

        //when
        deque.removeLast();
        deque.removeLast();

        //then
        assertThat(deque.size()).isEqualTo(2);

        Iterator<Integer> iterator = deque.iterator();
        assertThat(iterator.next()).isEqualTo(1);
        assertThat(iterator.next()).isEqualTo(2);
    }

    @Test
    public void shouldRemoveFromOneElementQueueLast()
    {
        //given
        Deque<Integer> deque = new Deque<>();
        init(deque,  5);

        //when
        deque.removeLast();

        //then
        assertThat(deque.size()).isEqualTo(0);

        Iterator<Integer> iterator = deque.iterator();
        assertThat(iterator.hasNext()).isFalse();
    }

    @Test
    public void shouldRemoveFromOneElementQueueFirst()
    {
        //given
        Deque<Integer> deque = new Deque<>();
        init(deque,  5);

        //when
        deque.removeFirst();

        //then
        assertThat(deque.size()).isEqualTo(0);

        Iterator<Integer> iterator = deque.iterator();
        assertThat(iterator.hasNext()).isFalse();
    }

    public static void init(Deque<Integer> deque,   int... values) {
        for(int i: values)
        {
            deque.addLast(i);
        }
    }
}