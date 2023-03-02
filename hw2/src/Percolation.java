import edu.princeton.cs.algs4.WeightedQuickUnionUF;

import java.util.ArrayList;
import java.util.List;


public class Percolation {
    private WeightedQuickUnionUF percolate;
    private boolean[][] site;
    private int openSites;

    // create N-by-N grid, with all sites initially blocked
    public Percolation(int N) {
        percolate = new WeightedQuickUnionUF(N * N);
        site = new boolean[N][N];
        openSites = 0;
    }

    /**
     *  open the site (row, col) if it is not open already.
     */
    public void open(int row, int col) {
        if (!isOpen(row, col)) {
            this.site[row][col] = true;
            openSites++;
        }
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {

        return site[row][col];
    }

    /**
     * need refactor, need constant time
     */
    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        // if this sites is connected to the top row which is opened, then return true
        return false;
    }

    // number of open sites
    public int numberOfOpenSites() {
        return openSites;
    }

    /**
     * need refactor, and need constant time
     */
    // does the system percolate?
    public boolean percolates() {
        return false;
    }

    public int xyTo1D(int row, int col) {
        int side = (int) Math.sqrt((double) this.site.length);
        if (row == 0) { //if row is 0, then return col.
            return col;
        }
        if (col == 0) {
            return row * side;
        }
        return row * side + col;
    }


    public boolean isCorner(int row, int col) {
        if (row == 0 & (col == 0 || this.site[0].length - 1 == col)) {
            return true;
        }
        if (row == this.site[0].length - 1 && (col == this.site[0].length - 1 || col == 0)) {
            return true;
        }

        return false;
    }
}
