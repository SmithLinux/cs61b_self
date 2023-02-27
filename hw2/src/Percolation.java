import edu.princeton.cs.algs4.WeightedQuickUnionUF;

import java.util.ArrayList;
import java.util.List;


public class Percolation {
    WeightedQuickUnionUF percolate;
    boolean[] site;
    int openSites;

    // create N-by-N grid, with all sites initially blocked
    public Percolation(int N) {
        percolate = new WeightedQuickUnionUF(N * N);
        site = new boolean[N * N];
        openSites = 0;
    }

    /**
     *  open the site (row, col) if it is not open already.
     */
    public void open(int row, int col) {
        if (!isOpen(row, col)) {
            this.site[xyTo1D(row, col)] = true;
        }
        aroundSiteOpenedUnion(row, col);
        openSites++;
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {

        return site[xyTo1D(row, col)];
    }



    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        // if this sites is connected to the top row which is opened, then return true
        int pos = this.xyTo1D(row, col);
        for (int i = 0; i < Math.sqrt(this.site.length); i++) {
            if (percolate.connected(pos, i) && isOpen(row, col)) {
                return true;
            }
        }
        return false;
    }

    // number of open sites
    public int numberOfOpenSites() {
        return openSites;
    }

    // does the system percolate?
    public boolean percolates() {
        int bottomRow = (int) Math.sqrt(this.site.length) - 1;
        for (int i = 0; i < bottomRow + 1; i++) {
            if (isFull(bottomRow, i)) {
                return true;
            }
        }
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

    /**
     * determine some around sites which is open or not open.
     */
    private void aroundSiteOpenedUnion(int row, int col) {
        List<Integer> around = aroundSites(row, col);
        for (int pos : around) {
            if (this.site[pos]) {
                if (!this.percolate.connected(xyTo1D(row, col), pos)) {
                    this.percolate.union(xyTo1D(row, col), pos);
                }
            }
        }
    }

    /**
     * return the around sites.
     */
    public List<Integer> aroundSites(int row, int col) {
        List<Integer> sites = new ArrayList<>();
        if (isCorner(row, col)) {
            if (col == row) { //top left, bottom right corner
                if (col == 0) { //top left
                    topLeftCorner(sites, xyTo1D(row, col));
                } else {
                    bottomRightCorner(sites, xyTo1D(row, col));
                }
            } else {
                if (row < col) {//top right
                    topRightCorner(sites, xyTo1D(row, col));
                } else {
                    bottomLeftCorner(sites, xyTo1D(row, col));
                }
            }
        }
        else if (row == 0){ // top row
            topRow(sites, xyTo1D(row, col));
        } else if (row == (int) Math.sqrt(this.site.length) - 1) {
            bottomRow(sites, xyTo1D(row, col));
        }
        else {
           otherSites(sites, xyTo1D(row, col));
        }
        return sites;
    }

    private void topRow(List<Integer> sites, int pos) {
        int sideLength = (int) Math.sqrt(this.site.length);
        sites.add(pos - 1);
        sites.add(pos + 1);
        sites.add(pos + sideLength);
    }

    private void otherSites(List<Integer> sites, int pos) {
        int sideLength = (int) Math.sqrt(this.site.length);
        sites.add(pos - 1);
        sites.add(pos + 1);
        sites.add(pos + sideLength);
        sites.add(pos - sideLength);
    }

    private void bottomRow(List<Integer> sites, int pos) {
        int sideLength = (int) Math.sqrt(this.site.length);
        sites.add(pos - 1);
        sites.add(pos + 1);
        sites.add(pos - sideLength);
    }

    private void bottomRightCorner(List<Integer> sites, int pos) {
        int sideLength = (int) Math.sqrt(this.site.length);
        sites.add(pos - 1);
        sites.add(pos - sideLength);
    }

    private void topLeftCorner(List<Integer> sites, int pos) {
        int sideLength = (int) Math.sqrt(this.site.length);
        sites.add(pos + 1);
        sites.add(pos + sideLength);
    }

    private void topRightCorner(List<Integer> sites, int pos) {
        int sideLength = (int) Math.sqrt(this.site.length);
        sites.add(pos - 1);
        sites.add(pos + sideLength);
    }

    private void bottomLeftCorner(List<Integer> sites, int pos) {
        int sideLength = (int) Math.sqrt(this.site.length);
        sites.add(pos + 1);
        sites.add(pos - sideLength);
    }

    /**
     * connect two site.
     */
    private void siteConnect(int pos, int other) {
        if (!this.percolate.connected(pos, other)) {
            percolate.union(pos, other);
        }
    }


    public boolean isCorner(int row, int col) {
        int sideLength = (int) Math.sqrt(this.site.length) - 1;
        if (row == 0 && (col == 0 || col == sideLength)) {
          return true;
        }
        return row == sideLength && (col == 0 || col == sideLength);
    }

}
