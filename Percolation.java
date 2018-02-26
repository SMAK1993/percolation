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
    private int openSiteCount = 0;

    // create n-by-n grid, with all sites blocked
    public Percolation(int n) {
        if (n <= 0) throw new IndexOutOfBoundsException("grid size out of bounds");
        sites = new boolean[n + 1][n + 1];
        myWeightedQuickUnionUF = new WeightedQuickUnionUF(n*n);
        sitesLength = n;
    }

    // open site (row, col) if it is not open already
    public void open(int row, int col) {
        checkArgument(row);
        checkArgument(col);
        sites[row][col] = true;
        openSiteCount++;
        if (col > 1 && sites[row][col - 1]) myWeightedQuickUnionUF.union(xyTo1D(row, col), xyTo1D(row, col - 1));
        if (col < sitesLength && sites[row][col + 1]) myWeightedQuickUnionUF.union(xyTo1D(row, col), xyTo1D(row, col + 1));
        if (row > 1 && sites[row - 1][col]) myWeightedQuickUnionUF.union(xyTo1D(row, col), xyTo1D(row - 1, col));
        if (row < sitesLength && sites[row + 1][col]) myWeightedQuickUnionUF.union(xyTo1D(row, col), xyTo1D(row + 1, col));
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
        return false;
    }

    private void checkArgument(int n) {
        if (n <= 0 || n > sites.length) {
            System.out.println("n = " + n);
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
    }

    private int xyTo1D(int row, int col) {
        row -= 1;
        col -= 1;
        return ((sitesLength * row) + col);
    }

    private void printSites() {
        // System.out.println(Arrays.deepToString(myPercolation.sites));
        System.out.println("Printing Sites");
        for (boolean[] site : sites) {
            System.out.println(Arrays.toString(site));
        }
    }

    // test client (optional)
    public static void main(String[] args) {
        Percolation myPercolation = new Percolation(3);
        System.out.println("N = " + (myPercolation.sitesLength));
        myPercolation.printSites();

        myPercolation.open(1, 1);
        myPercolation.open(1, 2);
        myPercolation.open(1, 3);

        myPercolation.open(2, 1);
        myPercolation.open(2, 2);
        myPercolation.open(2, 3);

        myPercolation.open(3, 1);
        myPercolation.open(3, 2);
        myPercolation.open(3, 3);

        myPercolation.printSites();
        
        System.out.println(myPercolation.myWeightedQuickUnionUF.connected(0, 1));
        System.out.println(myPercolation.myWeightedQuickUnionUF.connected(0, 3));
        System.out.println(myPercolation.myWeightedQuickUnionUF.connected(1, 4));
        System.out.println(myPercolation.myWeightedQuickUnionUF.connected(3, 4));
        // System.out.println(myPercolation.myWeightedQuickUnionUF.count());
        System.out.println(myPercolation.numberOfOpenSites());
        for (int i = 0; i < 3*3; i++) {
            System.out.println("Find(" + i + ") = " + myPercolation.myWeightedQuickUnionUF.find(i));
        }
    }
 }

