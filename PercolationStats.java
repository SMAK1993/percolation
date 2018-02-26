import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private final double[] trialResults;
    private final int totalTrials;
    private final double CONFIDENCE_95 = 1.96;

    // perform trials independent experiments on an n-by-n grid
    public PercolationStats(int n, int trials) {
        if (n <= 0) throw new IllegalArgumentException("Argument n is out of bounds");
        if (trials <= 0) throw new IllegalArgumentException("Argument trials is out of bounds");
        trialResults = new double[trials];
        totalTrials = trials;
        for (int i = 0; i < trials; i++) {
            Percolation percolationTrial = new Percolation(n);
            while (!percolationTrial.percolates()) {
                int row = StdRandom.uniform(1, n + 1);
                int col = StdRandom.uniform(1, n + 1);

                if (!percolationTrial.isOpen(row, col)) {
                    percolationTrial.open(row, col);
                }
            }
            trialResults[i] = (double) percolationTrial.numberOfOpenSites() / (n*n);
        }
    }

    // sample mean of percolation threshold
    public double mean() {
        return StdStats.mean(trialResults);
    }
    // sample standard deviation of percolation threshold
    public double stddev() {
        return StdStats.stddev(trialResults);
    }
    // low  endpoint of 95% confidence interval
    public double confidenceLo() {
        return mean() - ((CONFIDENCE_95 * stddev()) / Math.sqrt(totalTrials));
    }
    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return mean() + ((CONFIDENCE_95 * stddev()) / Math.sqrt(totalTrials));
    }

    // test client (described below)
    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        int trials = Integer.parseInt(args[1]);
        PercolationStats myPercolationStats = new PercolationStats(n, trials);

        String confidence = myPercolationStats.confidenceLo() + ", " + myPercolationStats.confidenceHi();
        StdOut.println("mean                    = " + myPercolationStats.mean());
        StdOut.println("stddev                  = " + myPercolationStats.stddev());
        StdOut.println("95% confidence interval = " + confidence);
    }
 }
