package w2.deque;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Created by Daniel on 1/8/2017.
 */
public class Deque<Item> implements Iterable<Item> {

    private class Node {
        private Item value;
        private Node prev;
        private Node next;
    }

    private Node head;
    private Node tail;
    private int count;

    /**
     * Construct an empty deque.
     */
    public Deque() {
        head = null;
        tail = null;
        count = 0;
    }

    /**
     * Is the deque empty?
     *
     * @return return true if Deque is empty.
     */
    public boolean isEmpty() {
        return count == 0;
    }

    /**
     * Return the number of items on the deque.
     *
     * @return return size of Deque.
     */
    public int size() {
        return count;
    }

    /**
     * Add item to the beginning of Deque.
     *
     * @param item an item value.
     * @throws NullPointerException if item == null.
     */
    public void addFirst(Item item) {
        if (item == null)
            throw new NullPointerException();
        Node node = new Node();
        node.value = item;
        node.prev = null;
        node.next = head;
        if (isEmpty()) tail = node;
        else head.prev = node;
        head = node;
        count++;
    }

    /**
     * Add item to the end of Deque.
     *
     * @param item an item value.
     * @throws NullPointerException if item == null.
     */
    public void addLast(Item item) {
        if (item == null)
            throw new NullPointerException();
        Node node = new Node();
        node.value = item;
        node.prev = tail;
        node.next = null;
        if (isEmpty()) head = node;
        else tail.next = node;
        tail = node;
        count++;
    }

    /**
     * Remove and return first item from the front.
     *
     * @return return removed value.
     * @throws NoSuchElementException if size == 0.
     */
    public Item removeFirst() {
        if (isEmpty())
            throw new NoSuchElementException("Empty Deque");
        Item res = head.value;
        head = head.next;
        if (size() == 1)
            tail = null;
        else
            head.prev = null;
        count--;
        return res;
    }

    /**
     * Remove and return last item from the end.
     *
     * @return return removed value.
     * @throws NoSuchElementException if size == 0.
     */
    public Item removeLast() {
        if (isEmpty())
            throw new NoSuchElementException("Empty Deque");
        Item res = tail.value;
        tail = tail.prev;
        if (size() == 1)
            head = null;
        else
            tail.next = null;
        count--;
        return res;
    }

    /**
     * Return an iterator over items in order from front to end.
     *
     * @return return iterator.
     */
    public Iterator<Item> iterator() {
        return new DequeIterator();
    }

    private class DequeIterator implements Iterator<Item> {

        private Node node = head;

        @Override
        public boolean hasNext() {
            return node != null;
        }

        @Override
        public Item next() {
            if (node == null) throw new NoSuchElementException();
            Item it = node.value;
            node = node.next;
            return it;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
}