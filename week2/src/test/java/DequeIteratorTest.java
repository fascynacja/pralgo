import org.junit.jupiter.api.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.fest.assertions.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Corner cases.
 * throw a java.lang.UnsupportedOperationException if the client calls the remove() method in the iterator;
 * throw a java.util.NoSuchElementException
 * if the client calls the next() method in the iterator and there are no more items to return.
 */
class DequeIteratorTest {

    @Test
    public void shouldThrowUOEWhenCallingRemoveOnIterator()
    {
        //given
        Deque<String> deque = new Deque<>();
        Iterator<String> iterator = deque.iterator();

        //when
        Throwable exception = assertThrows(UnsupportedOperationException.class, () -> {
            iterator.remove();
        });

        //then
        assertThat(exception).isInstanceOf(UnsupportedOperationException.class);
    }

    @Test
    public void shouldThrowNSEWhenCallingNextButNoItemsAvailable()
    {
        //given
        Deque<String> deque = new Deque<>();
        Iterator<String> iterator = deque.iterator();

        //when
        Throwable exception = assertThrows(NoSuchElementException.class, () -> {
            iterator.next();
        });

        //then
        assertThat(exception).isInstanceOf(NoSuchElementException.class);
    }
}