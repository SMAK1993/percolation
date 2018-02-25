import java.util.Arrays;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    boolean[][] sites;

    // create n-by-n grid, with all sites blocked
    public Percolation(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException(Integer.toString(n));
        }
        sites = new boolean[n][n];
        return;
    }

    // open site (row, col) if it is not open already
    public void open(int row, int col) {
        checkArgument(row);
        checkArgument(col);
        // if (row < 1 || row > sites.length) {
        //     throw new IllegalArgumentException(Integer.toString(row));
        // }
        // if (col < 1 || col > sites.length) {
        //     throw new IllegalArgumentException(Integer.toString(col));
        // }
        sites[row-1][col-1] = true;
        return;
    }

    // is site (row, col) open?
    public boolean isOpen(int row, int col) {
        checkArgument(row);
        checkArgument(col);
        // if (row < 1 || row > sites.length) {
        //     throw new IllegalArgumentException(Integer.toString(row));
        // }
        // if (col < 1 || col > sites.length) {
        //     throw new IllegalArgumentException(Integer.toString(col));
        // }
        return sites[row-1][col-1];
    }

    // is site (row, col) full?
    public boolean isFull(int row, int col) {
        return false;
    }

    // number of open sites
    public int numberOfOpenSites() {
        return 0;
    }

    // does the system percolate?
    public boolean percolates() {
        return false;
    }

    public void checkArgument(int n) {
        if (n <= 0 || n > sites.length) {
            throw new IllegalArgumentException(Integer.toString(n));
        }
    }

    public void printSites() {
        // System.out.println(Arrays.deepToString(myPercolation.sites));
        for (boolean[] site : sites) {
            System.out.println(Arrays.toString(site));
        }
        System.out.println();
    }

    // test client (optional)
    public static void main(String[] args) {
        System.out.println("Starting Percolation");
        Percolation myPercolation = new Percolation(3);
        myPercolation.printSites();
        myPercolation.open(2, 2);
        myPercolation.printSites();
    }
 }

