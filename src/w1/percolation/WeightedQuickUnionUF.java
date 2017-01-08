package w1.percolation;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class WeightedQuickUnionUF {

    private int[] id;       // id[i] == parent of i
    private int[] size;     // number of elements in the subtree
    private int count;      // number of components

    /**
     * Initializes an empty union–find data structure with  n sites
     * 0 through n-1. Each site is initially in its own
     * component.
     *
     * @param n the number of sites
     * @throws IllegalArgumentException if n < 0}
     */
    public WeightedQuickUnionUF(int n) {
        count = n;
        id = new int[n];
        size = new int[n];
        for (int i = 0; i < n; i++) {
            id[i] = i;
        }
    }

    /**
     * Returns the number of components.
     *
     * @return the number of components (between <tt>1</tt> and <tt>n</tt>)
     */
    public int count() {
        return count;
    }

    /**
     * Returns true if the the two sites are in the same component.
     *
     * @param p the integer representing one site
     * @param q the integer representing the other site
     * @return <tt>true</tt> if the two sites <tt>p</tt> and <tt>q</tt> are in the same component;
     * <tt>false</tt> otherwise
     * @throws IndexOutOfBoundsException unless
     *                                   both <tt>0 &le; p &lt; n</tt> and <tt>0 &le; q &lt; n</tt>
     */
    public boolean connected(int p, int q) {
        return root(p) == root(q);
    }

    /**
     * Merges the component containing site <tt>p</tt> with the
     * the component containing site <tt>q</tt>.
     *
     * @param p the integer representing one site
     * @param q the integer representing the other site
     * @throws IndexOutOfBoundsException unless
     * both <tt>0 &le; p &lt; n</tt> and <tt>0 &le; q &lt; n</tt>
     */
    public void union(int p, int q) {
        int i = root(p);
        int j = root(q);
        if (i == j) return;
        if (size[i] > size[j]) {
            id[j] = i;
            size[i] += size[j];
        } else {
            id[i] = j;
            size[j] += size[i];
        }
        count--;
    }

    /**
     * Returns the component identifier for the component containing site <tt>p</tt>.
     *
     * @param p the integer representing one site
     * @return the component identifier for the component containing site <tt>p</tt>
     * @throws IndexOutOfBoundsException unless <tt>0 &le; p &lt; N</tt>
     */
    private int root(int p) {
        if (p < 0 || p >= id.length) throw new IndexOutOfBoundsException();
        while (p != id[p]) {
            id[p] = id[id[p]];
            p = id[p];
        }
        return p;
    }

    public static void main(String[] args) {
        int n = 20;
        WeightedQuickUnionUF uf = new WeightedQuickUnionUF(n);
        while (!StdIn.isEmpty()) {
            int p = StdIn.readInt();
            int q = StdIn.readInt();
            if (uf.connected(p, q)) continue;
            uf.union(p, q);
            StdOut.println(p + " " + q);
        }
        StdOut.println(uf.count() + " components");
    }

}
