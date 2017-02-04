import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;


public class Permutation {

    private Permutation(int size) {
        RandomizedQueue<String> queue = new RandomizedQueue<>();
        readData(queue);

        for (int i = 0; i < size; i++) {
            StdOut.println(queue.dequeue());
        }

    }

    private void readData(RandomizedQueue<String> queue) {

        while (!StdIn.isEmpty()) {
            String s = StdIn.readString();
            queue.enqueue(s);
        }
    }

    public static void main(String[] args) {
        if (args.length != 1) {
            throw new IllegalArgumentException("Please provide number");
        }
        try {

            int size = Integer.parseInt(args[0]);

            new Permutation(size);


        } catch (NumberFormatException nfe) {
            throw new IllegalArgumentException("Please provide a number in correct format");
        }
    }
}