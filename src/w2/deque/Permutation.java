package w2.deque;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/**
 * Created by Daniel on 1/9/2017.
 */


//Randomized queue test client
public class Permutation {
    public static void main(String[] args) {
        int k = Integer.parseInt(args[0]);
        RandomizedQueue<String> randQueue = new RandomizedQueue<>();

        while (!StdIn.isEmpty()) {
            randQueue.enqueue(StdIn.readString());
        }

        for (int i = 0; i < k; i++) {
            StdOut.printf("%s\n", randQueue.dequeue());
        }
    }
}
