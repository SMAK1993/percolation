import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    boolean[][] sites;
    WeightedQuickUnionUF myWeightedQuickUnionUF;
    int sitesLength;
    private int openSiteCount = 0;

    // create n-by-n grid, with all sites blocked
    public Percolation(int n) {
        if (n <= 0) throw new IndexOutOfBoundsException("Grid size out of bounds");
        sites = new boolean[n + 1][n + 1];
        myWeightedQuickUnionUF = new WeightedQuickUnionUF((n*n) + 2);
        sitesLength = n;
    }

    // open site (row, col) if it is not open already
    public void open(int row, int col) {
        checkArgument(row);
        checkArgument(col);
        sites[row][col] = true;
        openSiteCount++;
        if (row == 1) myWeightedQuickUnionUF.union(xyTo1D(row, col), 0);
        if (row > 1 && sites[row - 1][col]) myWeightedQuickUnionUF.union(xyTo1D(row, col), xyTo1D(row - 1, col));
        if (row < sitesLength && sites[row + 1][col]) myWeightedQuickUnionUF.union(xyTo1D(row, col), xyTo1D(row + 1, col));
        if (row == sitesLength) myWeightedQuickUnionUF.union(xyTo1D(row, col), (sitesLength * sitesLength) + 1);
        if (col > 1 && sites[row][col-1]) myWeightedQuickUnionUF.union(xyTo1D(row, col), xyTo1D(row, col - 1));
        if (col < sitesLength && sites[row][col+1]) myWeightedQuickUnionUF.union(xyTo1D(row, col), xyTo1D(row, col + 1));
    }

    // is site (row, col) open?
    public boolean isOpen(int row, int col) {
        checkArgument(row);
        checkArgument(col);
        return sites[row][col];
    }

    // is site (row, col) full?
    public boolean isFull(int row, int col) {
        checkArgument(row);
        checkArgument(col);
        return myWeightedQuickUnionUF.connected(xyTo1D(row, col), 0);
    }

    // number of open sites
    public int numberOfOpenSites() {
        return openSiteCount;
    }

    // does the system percolate?
    public boolean percolates() {
        return myWeightedQuickUnionUF.connected(0, (sitesLength * sitesLength) + 1);
    }

    private void checkArgument(int n) {
        if (n <= 0 || n > sites.length) {
            System.out.println("n = " + n);
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
    }

    private int xyTo1D(int row, int col) {
        return ((sitesLength * (row-1)) + col);
    }

    // test client (optional)
    public static void main(String[] args) {}
 }

