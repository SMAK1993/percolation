import java.util.Arrays;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    boolean[][] sites;
    WeightedQuickUnionUF myWeightedQuickUnionUF;
    int sitesLength;

    // create n-by-n grid, with all sites blocked
    public Percolation(int n) {
        if (n <= 0) throw new IllegalArgumentException(Integer.toString(n));
        sites = new boolean[n][n];
        myWeightedQuickUnionUF = new WeightedQuickUnionUF(n*n);
        sitesLength = n - 1;
        return;
    }

    // open site (row, col) if it is not open already
    public void open(int row, int col) {
        checkArgument(row);
        checkArgument(col);
        sites[row - 1][col - 1] = true;
        if (row == 0) {
            if (isOpen(row + 1, col)) myWeightedQuickUnionUF.union(indexValue(row, col), indexValue(row + 1, col));
            if (col == 0 && isOpen(row, col + 1)) myWeightedQuickUnionUF.union(indexValue(row, col), indexValue(row, col + 1));
            else if (col == sitesLength && isOpen(row ,col - 1)) myWeightedQuickUnionUF.union(indexValue(row, col), indexValue(row, col - 1));
            else {
                if (isOpen(row, col + 1)) myWeightedQuickUnionUF.union(indexValue(row, col), indexValue(row, col + 1));
                if (isOpen(row, col - 1)) myWeightedQuickUnionUF.union(indexValue(row, col), indexValue(row, col - 1));
            }
        }
        else if (row == sitesLength) {
            if (isOpen(row - 1, col)) myWeightedQuickUnionUF.union(indexValue(row, col), indexValue(row - 1, col));
            if (col == 0 && isOpen(row, col + 1)) myWeightedQuickUnionUF.union(indexValue(row, col), indexValue(row, col + 1));
            else if (col == sitesLength && isOpen(row, col - 1)) myWeightedQuickUnionUF.union(indexValue(row, col), indexValue(row, col - 1));
            else {
                if (isOpen(row, col + 1)) myWeightedQuickUnionUF.union(indexValue(row, col), indexValue(row, col + 1));
                if (isOpen(row, col - 1)) myWeightedQuickUnionUF.union(indexValue(row, col), indexValue(row, col - 1));
            }
        }
        else {
            if (isOpen(row, col + 1)) myWeightedQuickUnionUF.union(indexValue(row, col), indexValue(row, col + 1));
            if (isOpen(row, col - 1)) myWeightedQuickUnionUF.union(indexValue(row, col), indexValue(row, col - 1));
            if (isOpen(row + 1, col)) myWeightedQuickUnionUF.union(indexValue(row, col), indexValue(row + 1, col));
            if (isOpen(row - 1, col)) myWeightedQuickUnionUF.union(indexValue(row, col), indexValue(row - 1, col));
        }
        return;
    }

    // is site (row, col) open?
    public boolean isOpen(int row, int col) {
        checkArgument(row);
        checkArgument(col);
        return sites[row - 1][col - 1];
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

    public int indexValue(int row, int col) {
        return ((sitesLength * row) + col);
    }

    public void printSites() {
        // System.out.println(Arrays.deepToString(myPercolation.sites));
        System.out.println("Printing Sites");
        for (boolean[] site : sites) {
            System.out.println(Arrays.toString(site));
        }
    }

    // test client (optional)
    public static void main(String[] args) {
        Percolation myPercolation = new Percolation(3);
        System.out.println("N = " + myPercolation.sites.length);
        myPercolation.printSites();
        myPercolation.open(2, 2);
        myPercolation.printSites();
    }
 }

