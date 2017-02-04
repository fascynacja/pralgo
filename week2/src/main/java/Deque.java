import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

    private Node<Item> first;
    private Node<Item> last;
    private int n;


    public Deque() {
    }


    private static class Node<Item> {
        private Item item;
        private Node<Item> next;
        private Node<Item> previous;

        Node(Item i) {
            this.item = i;
        }
    }


    public boolean isEmpty() {
        return first == null;
    }


    public int size() {
        return n;
    }

    public void addFirst(Item item) {
        nullCheck(item);
        Node<Item> newNode = new Node(item);
        newNode.next = first;
        if (!isEmpty()) {
            first.previous = newNode;
        } else {
            last = newNode;
        }
        first = newNode;
        n++;

    }

    public void addLast(Item item) {
        nullCheck(item);

        Node<Item> newNode = new Node(item);
        newNode.previous = last;
        Node oldLast = last;
        if (!isEmpty()) {
            oldLast.next = newNode;
        } else {
            first = newNode;
        }
        last = newNode;
        n++;
    }

    public Item removeFirst() {
        checkNotEmpty();
        Item item = first.item;

        Node newFirst = first.next;
        first.next = null;

        first = newFirst;
        if (!isEmpty()) {
            first.previous = null;
        } else {
            last = null;
        }
        n--;

        return item;
    }

    public Item removeLast() {
        checkNotEmpty();
        Item item = last.item;
        Node newLast = last.previous;
        last.previous = null;
        last = newLast;
        if (newLast!=null) {
            newLast.next = null;
        } else {
            first = null;
        }
        n--;
        return item;
    }

    public Iterator<Item> iterator() {
        return new ListIterator<>(first);
    }

    private void checkNotEmpty() {
        if (isEmpty()) {
            throw new NoSuchElementException("Collection is empty");
        }
    }


    private void nullCheck(Item item) {
        if (item == null) {
            throw new NullPointerException();
        }
    }

    private class ListIterator<Item> implements Iterator<Item> {
        private Node<Item> current;

        private ListIterator(Node<Item> first) {
            current = first;
        }

        public boolean hasNext() {
            return current != null;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            Item item = current.item;
            current = current.next;
            return item;
        }
    }

    private boolean check() {

        // check a few properties of instance variable 'first'
        if (n < 0) {
            return false;
        }
        if (n == 0) {
            if (first != null) return false;
        }
        else if (n == 1) {
            if (first == null)      return false;
            if (first.next != null) return false;
        }
        else {
            if (first == null)      return false;
            if (first.next == null) return false;
        }

        // check internal consistency of instance variable n
        int numberOfNodes = 0;
        for (Node x = first; x != null && numberOfNodes <= n; x = x.next) {
            numberOfNodes++;
        }
        if (numberOfNodes != n) return false;

        return true;
    }

    public static void main(String[] args) {
    }
}