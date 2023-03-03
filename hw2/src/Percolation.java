import edu.princeton.cs.algs4.WeightedQuickUnionUF;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class Percolation {
    private WeightedQuickUnionUF percolate;
    private boolean[][] site;
    private int openSites;

    private Set<Integer> sitePosition;

    private int top;
    private int bottom;

    // create N-by-N grid, with all sites initially blocked
    public Percolation(int N) {
        percolate = new WeightedQuickUnionUF(N * N + 2); // count() - 1 means top, count () means bottom
        top = N * N;
        bottom = N * N + 1;
        site = new boolean[N][N];
        openSites = 0;
        sitePosition = new HashSet<>();
    }

    /**
     * open the site (row, col) if it is not open already.
     */
    public void open(int row, int col) {
        if (!isOpen(row, col)) {
            if (row == 0) {
                percolate.union(this.top, xyTo1D(row, col));
            }
            if (row == this.site[0].length - 1 && !percolates()) {
                percolate.union(this.bottom, xyTo1D(row, col));
            }
            this.site[row][col] = true;


            if (!this.sitePosition.contains(xyTo1D(row, col))) {
                sitePosition.add(xyTo1D(row, col));
            }

            this.unionAroundOpened(row, col);
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
        return this.isConnected(this.xyTo1D(row, col), top);
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
        return this.isConnected(top, bottom);
    }

    public int xyTo1D(int row, int col) {
        int side = this.site[0].length;
        if (row == 0) { //if row is 0, then return col.
            return col;
        }
        if (col == 0) {
            return row * side;
        }
        return row * side + col;
    }


    public void unionAroundOpened(int row, int col) {
        int pos = xyTo1D(row, col);
        int upperPos = xyTo1D(row + 1, col);
        int lowerPos = xyTo1D(row - 1, col);
        int leftPos = xyTo1D(row, col - 1);
        int rightPos = xyTo1D(row, col + 1);


        checkUnion(pos, upperPos);
        checkUnion(pos, lowerPos);
        checkUnion(pos, leftPos);
        checkUnion(pos, rightPos);
    }


    public void checkUnion(int pos, int otherPos) {
        if (this.sitePosition.contains(otherPos)) {
            if (!this.isConnected(pos, otherPos)) {
                this.connectTwoSites(pos, otherPos);
            }
        }
    }

    public boolean isConnected(int pos, int otherPos) {
        return this.percolate.connected(pos, otherPos);
    }

    public void connectTwoSites(int pos, int otherPos) {
        this.percolate.union(pos, otherPos);
    }

    private int fullPos() {
        return top;
    }
}
