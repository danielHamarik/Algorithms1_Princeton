package w2.deque;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.StdRandom;

/**
 * Created by Daniel on 1/8/2017.
 */
public class RandomizedQueue<Item> implements Iterable<Item> {
    private static final int DEFAULT_SIZE = 20;

    private Item[] queue;
    private int n;

    /**
     * Construct an empty Randomized queue.
     */
    public RandomizedQueue() {
        queue = (Item[]) new Object[DEFAULT_SIZE];
        n = 0;
    }

    /**
     * Is the RandomizedQueue empty?.
     *
     * @return return true if queue is empty.
     */
    public boolean isEmpty() {
        return n == 0;
    }

    /**
     * Return the number of items on the queue.
     *
     * @return return number of items.
     */
    public int size() {
        return n;
    }

    /**
     * Add an item to the end of the queue.
     *
     * @throws NullPointerException if item == null.
     */
    public void enqueue(Item item) {
        if (item == null) throw new NullPointerException();
        if (n == queue.length) resize(queue.length * 2);
        queue[n++] = item;
    }

    /**
     * Remove and return a random item.
     *
     * @throws NoSuchElementException if isEmpty().
     */
    public Item dequeue() {
        if (isEmpty()) throw new NoSuchElementException();
        int random = StdRandom.uniform(n);
        Item item = queue[random];
        queue[random] = queue[--n];
        queue[n] = null;
        if (n > 0 && n == queue.length / 4) resize(queue.length / 2);
        return item;
    }

    private void resize(int capacity) {
        Item[] copy = (Item[]) new Object[capacity];
        for (int i = 0; i < n; i++)
            copy[i] = queue[i];
        queue = copy;
    }

    /**
     * Return (but do not remove) a random item.
     *
     * @throws NoSuchElementException if isEmpty().
     */
    public Item sample() {
        if (isEmpty()) throw new NoSuchElementException();
        return queue[StdRandom.uniform(n)];
    }

    /**
     * Return an independent iterator over items in random order.
     *
     * @return return Iterator in random order.
     */
    public Iterator<Item> iterator() {
        return new RandomizedQueueIterator();
    }

    private class RandomizedQueueIterator implements Iterator {

        private Item[] copy = Arrays.copyOf(queue, n);
        private int index = n;

        @Override
        public boolean hasNext() {
            return index > 0;
        }

        @Override
        public Object next() {
            if (index <= 0) throw new NoSuchElementException();
            int pos = StdRandom.uniform(index);
            Item i = copy[pos];
            copy[pos] = copy[--index];
            copy[index] = null;
            return i;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
}
