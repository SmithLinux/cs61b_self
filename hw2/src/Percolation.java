import edu.princeton.cs.algs4.WeightedQuickUnionUF;


import java.util.TreeMap;
// TODO: Add any other necessary imports.

public class Percolation {
    // TODO: Add any necessary instance variables.
    WeightedQuickUnionUF grid;
    boolean[] site;

    // create N-by-N grid, with all sites initially blocked
    public Percolation(int N) {
        // TODO: Fill in this constructor.
        grid = new WeightedQuickUnionUF(N * N);
        site = new boolean[N * N];
    }

    /**
     *  open the site (row, col) if it is not open already.
     */
    public void open(int row, int col) {
        if (!isOpen(row, col)) {
            this.site[xyTo1D(row, col)] = true;
        }
    }

    // is the site (row, col) open?
    /**
     * row 1 col 2 stand for
     */
    public boolean isOpen(int row, int col) {

        return site[xyTo1D(row, col)];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        // TODO: Fill in this method.
        return false;
    }

    // number of open sites
    public int numberOfOpenSites() {
        // TODO: Fill in this method.
        return 0;
    }

    // does the system percolate?
    public boolean percolates() {
        // TODO: Fill in this method.
        return false;
    }


    private int xyTo1D(int row, int col) {
        int side = (int) Math.sqrt((double) this.site.length);
        if (row == 0) { //if row is 0, then return col.
            return col;
        }
        if (col == 0) {
            return row * side;
        }
        return row + col * side;
    }
}
