import org.junit.jupiter.api.Test;

import java.util.Iterator;

import static org.fest.assertions.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Corner cases.
 * Throw a java.lang.NullPointerException if the client attempts to add a null item;
 */
class DequeAddItem {
    @Test
    public void shouldThrowNPEWhenAddingNullItem()
    {
        //given
        Deque<String> deque = new Deque<>();

        //when
        Throwable exception = assertThrows(NullPointerException.class, () -> {
            deque.addFirst(null);
        });
        //then

    }

    @Test
    public void shouldThrowNPEWhenAddingLastNullItem()
    {
        //given
        Deque<String> deque = new Deque<>();

        //when
        Throwable exception = assertThrows(NullPointerException.class, () -> {
            deque.addLast(null);
        });
        //then

    }

    @Test
    public void shouldAddItemFirst()
    {
        //given
        Deque<Integer> deque = new Deque<>();
        DequeRemoveTest.init(deque,  1);

        //when
        deque.addFirst(0);

        //then
        assertThat(deque.size()).isEqualTo(2);

        Iterator<Integer> iterator = deque.iterator();
        assertThat(iterator.hasNext()).isTrue();

        assertThat(iterator.next()).isEqualTo(0);
        assertThat(iterator.next()).isEqualTo(1);
    }

    @Test
    public void shouldAddItemLast()
    {
        //given
        Deque<Integer> deque = new Deque<>();
        DequeRemoveTest.init(deque,  1);

        //when
        deque.addLast(2);

        //then
        assertThat(deque.size()).isEqualTo(2);

        Iterator<Integer> iterator = deque.iterator();
        assertThat(iterator.hasNext()).isTrue();

        assertThat(iterator.next()).isEqualTo(1);
        assertThat(iterator.next()).isEqualTo(2);
    }

    @Test
    public void shouldAddItemFirstToEmptyCollection()
    {
        //given
        Deque<Integer> deque = new Deque<>();

        //when
        deque.addFirst(0);

        //then
        assertThat(deque.size()).isEqualTo(1);

        Iterator<Integer> iterator = deque.iterator();
        assertThat(iterator.hasNext()).isTrue();

        assertThat(iterator.next()).isEqualTo(0);
    }

    @Test
    public void shouldAddItemLastToEmptyCollection()
    {
        //given
        Deque<Integer> deque = new Deque<>();

        //when
        deque.addLast(0);

        //then
        assertThat(deque.size()).isEqualTo(1);

        Iterator<Integer> iterator = deque.iterator();
        assertThat(iterator.hasNext()).isTrue();

        assertThat(iterator.next()).isEqualTo(0);
    }
}