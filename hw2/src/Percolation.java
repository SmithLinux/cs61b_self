import edu.princeton.cs.algs4.WeightedQuickUnionUF;
// TODO: Add any other necessary imports.

public class Percolation {
    // TODO: Add any necessary instance variables.
    WeightedQuickUnionUF grid;

    // create N-by-N grid, with all sites initially blocked
    public Percolation(int N) {
        // TODO: Fill in this constructor.
        grid = new WeightedQuickUnionUF(N);

    }

    // open the site (row, col) if it is not open already
    public void open(int row, int col) {
        // TODO: Fill in this method.
        if(!isOpen(row, col)) {
            grid.union(row, col);
        }
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        // TODO: Fill in this method.
        return grid.connected(row, col);
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

    // TODO: Add any useful helper methods (we highly recommend this!).
    // TODO: Remove all TODO comments before submitting.

}
