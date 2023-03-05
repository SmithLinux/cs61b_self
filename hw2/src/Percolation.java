import edu.princeton.cs.algs4.WeightedQuickUnionUF;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


/**
 * all methods should take constant time plus a constant number of calls to the union-find methods union(), find(), connected(), and count()
 */
public class Percolation {
    private WeightedQuickUnionUF percolate;
    private boolean[][] site;
    private int openSites;
    private Set<Integer> sitePosition;
    private int virtualTop;
    private int virtualBottom;

    private WeightedQuickUnionUF mirror;
    private int mirrorTop;


    // create N-by-N grid, with all sites initially blocked
    /**
     * The constructor should take time proportional to N^2
     */
    public Percolation(int N) {
        if (N <= 0) {
            throw new IllegalArgumentException();
        }
        percolate = new WeightedQuickUnionUF(N * N + 2);
        mirror = new WeightedQuickUnionUF(N * N + 1);
        mirrorTop = mirror.count() - 1;
        // count() - 2 means top, count () - 1 means bottom
        virtualTop = percolate.count() - 2;
        virtualBottom = percolate.count() - 1;
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
                percolate.union(this.virtualTop, xyTo1D(row, col));
                mirror.union(this.mirrorTop, xyTo1D(row, col));
            }
            if (row == this.site[0].length - 1 && !percolates()) {
                percolate.union(xyTo1D(row, col), this.virtualBottom);

            } else if (!this.sitePosition.contains(xyTo1D(row, col))) {
                sitePosition.add(xyTo1D(row, col));
            }

            this.site[row][col] = true;



            this.unionAroundOpened(row, col);
            openSites++;

        }
    }


    // is the site (row, col) open? Completed
    public boolean isOpen(int row, int col) {

        return site[row][col];
    }

    /**
     * need refactor, need constant time
     */
    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        // if this sites is connected to the top row which is opened, then return true
        return this.mirror.connected(this.xyTo1D(row, col), mirrorTop);
    }

    // number of open sites Completed
    public int numberOfOpenSites() {
        return openSites;
    }

    /**
     * need refactor, and need constant time
     */
    // does the system percolate?
    public boolean percolates() {
        return this.percolate.connected(virtualBottom, virtualTop);
    }

    public int xyTo1D(int row, int col) {
        int side = this.site[0].length;
        if (row < 0 || row >= side || col < 0 || col >= side) {
            return -1;
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
            if (!this.percolate.connected(pos, otherPos)) {
                this.connectTwoSites(pos, otherPos);
            }
        }
    }


    public void connectTwoSites(int pos, int otherPos) {
        this.percolate.union(pos, otherPos);
        this.mirror.union(pos, otherPos);
    }

}
