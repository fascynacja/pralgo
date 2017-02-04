import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {

    private Item[] a;
    private int n;


    public RandomizedQueue() {
        a = (Item[]) new Object[2];
    }

    public boolean isEmpty() {
        return n == 0;
    }

    public int size() {
        return n;
    }

    public void enqueue(Item item) {
        if (n == a.length) {
            resize(2 * a.length);
        }
        a[n++] = item;
    }

    public Item dequeue() {
        checkNotEmpty();
        int random = StdRandom.uniform(n);
        Item item = a[random];
        a[random] = a[n - 1];
        a[n - 1] = null;
        n--;
        // shrink size of array if necessary
        if (n > 0 && n == a.length / 4) {
            resize(a.length / 2);
        }
        return item;
    }

    public Item sample() {
        checkNotEmpty();
        int random = StdRandom.uniform(n);
        return a[random];
    }

    public Iterator<Item> iterator() {
        return new ReverseArrayIterator();
    }

    // resize the underlying array holding the elements
    private void resize(int capacity) {
        assert capacity >= n;

        // textbook implementation
        Item[] temp = (Item[]) new Object[capacity];
        for (int i = 0; i < n; i++) {
            temp[i] = a[i];
        }
        a = temp;
    }

    private void checkNotEmpty() {
        if (isEmpty()) {
            throw new NoSuchElementException("Stack underflow");
        }
    }

    private class ReverseArrayIterator implements Iterator<Item> {
        private int i;
        private boolean[] unusedIndexes;

        public ReverseArrayIterator() {
            unusedIndexes = new boolean[n];
            i = n - 1;
        }

        public boolean hasNext() {
            return i >= 0;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            int random = -1;
            boolean pickedItemIsUsed = true;
            while (pickedItemIsUsed) {
                random = StdRandom.uniform(n);
                pickedItemIsUsed = unusedIndexes[random];
            }
            unusedIndexes[random] = true;
            Item item = a[random];
            i--;
            return item;
        }
    }

    public static void main(String[] args) {

    }
} 