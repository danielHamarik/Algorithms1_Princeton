package w1.percolation;

/**
 * Created by Daniel on 1/5/2017.
 */
public class Percolation {

    private WeightedQuickUnionUF unionFind; // underlying data structure
    private WeightedQuickUnionUF backWash;
    private boolean[] opened;               // keeps track of sites state
    private int openNo;                     // number of opened sites
    private int size;                       // total size of all sites + virtual top and bottom
    private int n;                          // size of the row/column

    /**
     * Initializes an empty union–find data structure with
     * n * n + 2 sites and boolean array with all sites closed
     * by default.
     *
     * @param n row/column size.
     * @throws IllegalArgumentException if n < 0.
     */
    public Percolation(int n) {
        if (n <= 0) throw new IllegalArgumentException();
        size = n * n + 2;
        this.n = n;
        openNo = 0;
        unionFind = new WeightedQuickUnionUF(size);      // +2 because of the virtual top and bottom
        backWash = new WeightedQuickUnionUF(size -1);
        opened = new boolean[size];
        for (int i = 1; i <= n; i++) {
            unionFind.union(0, i);                      // connect top row with virtual Top and
            backWash.union(0,i);
            unionFind.union(size - 1, size - 1 - i);    // connect bottom row with virtual bottom
        }
    }

    /**
     * Open site (row, col) if it is not open already and connect
     * with top, right, bottom and left site if opened/available.
     *
     * @param row the integer representing row position
     * @param col the integer representing column position
     * @throws IndexOutOfBoundsException if row/column is less than 1 or more than n.
     */
    public void open(int row, int col) {
        int pos = getPosition(row, col);
        if (!opened[pos]) {
            opened[pos] = true;
            if (row > 1) {
                if (opened[pos - n]){
                    unionFind.union(pos, pos - n);
                    backWash.union(pos, pos - n);
                }
            }
            if (row < n) {
                if (opened[pos + n]){
                    unionFind.union(pos, pos + n);
                    backWash.union(pos, pos + n);
                }

            }
            if (col > 1) {
                if (opened[pos - 1]){
                    unionFind.union(pos, pos - 1);
                    backWash.union(pos, pos - 1);
                }

            }
            if (col < n) {
                if (opened[pos + 1]){
                    unionFind.union(pos, pos + 1);
                    backWash.union(pos, pos + 1);
                }
            }
            openNo++;
        }
    }

    /**
     * Returns true if the site is open.
     *
     * @param row the integer representing row position
     * @param col the integer representing column position
     * @return return true if site is open otherwise return false
     * @throws IndexOutOfBoundsException if row/column is less than 1 or more than n.
     */
    public boolean isOpen(int row, int col) {
        return opened[getPosition(row, col)];
    }

    /**
     * Returns true if the selected grid is opened and connected to the virtual top.
     * @param row the integer representing row <1, n>.
     * @param col the integer representing column<1, n>.
     *
     * @return return true if site is connected with virtual top.
     * @throws IndexOutOfBoundsException if row/column is less than 1 or more than n.
     */
    public boolean isFull(int row, int col) {
        int pos = getPosition(row, col);
        if (opened[pos]) {
            return backWash.connected(0, pos);
        }
        return false;
    }

    /**
     * Returns the number of open sites.
     *
     * @return return number of open sites in the grid.
     */
    public int numberOfOpenSites() {
        return openNo;
    }

    /**
     * Returns true if the grid percolates (top and bottom is interconnected).
     *
     * @return return true if virtual top is connected with virtual bottom.
     */
    public boolean percolates() {
        if(n == 1 && openNo < 1)
            return false;
        return unionFind.connected(0, size - 1);
    }
    /**
     * Returns the corresponding position in 1D array.
     *
     * @param row the integer representing row <1, n>.
     * @param col the integer representing column<1, n>.
     * @return return valid position in the array.
     * @throws IndexOutOfBoundsException if param < 1 || param > n
     * */
    private int getPosition(int row, int col) {
        if (row < 1 || col < 1 || row > n || col > n)
            throw new IndexOutOfBoundsException();
        return (row - 1)*n + col;
    }
}
