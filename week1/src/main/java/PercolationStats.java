import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {

    private final int n;
    private final double[] tresholds;
    private final int trials;

    // perform trials independent experiments on an n-by-n grid
    // The constructor should throw a java.lang.IllegalArgumentException if either n ≤ 0 or trials ≤ 0.
    public PercolationStats(int n, int trials) {
        validatePositive(n);
        validatePositive(trials);

        this.trials = trials;
        this.n = n;
        tresholds = new double[trials];

        for (int trial = 0; trial < trials; trial++) {
            Percolation percolation = new Percolation(n);

            // open sites until precolates
            while (!percolation.percolates()) {
                openBlockedSite(percolation);
            }

            tresholds[trial] = calculateTresHold(percolation);

        }
    }

    // test client (described below)
    public static void main(String[] args) {
        if (args.length != 2) {
            throw new IllegalArgumentException("Please provide two numbers. Eg 200 100");
        }
        try {

            int size = Integer.parseInt(args[0]);
            int trials = Integer.parseInt(args[1]);

            new PercolationStats(size, trials);
            // System.out.printf("Performing %d trials on %dx%d grid", trials, size, size);
        } catch (NumberFormatException nfe) {
            throw new IllegalArgumentException("Please provide two numbers in correct format");
        }
    }

    private void openBlockedSite(Percolation percolation) {

        for (;;) {
            int row = StdRandom.uniform(1, n + 1);
            int col = StdRandom.uniform(1, n + 1);
            if (!percolation.isOpen(row, col)) {
                percolation.open(row, col);
                return;
            }
        }
    }

    private double calculateTresHold(Percolation percolation) {
        return (double) percolation.numberOfOpenSites() / ((double) n * (double) n);
    }


    // sample mean of percolation threshold
    public double mean() {
        return StdStats.mean(tresholds);
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return StdStats.stddev(tresholds);
    }

    // low  endpoint of 95% confidence interval
    public double confidenceLo() {
        return mean() - 1.96 * stddev() / Math.sqrt(trials);
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return mean() + 1.96 * stddev() / Math.sqrt(trials);
    }


    private void validatePositive(int val) {
        if (val <= 0)
            throw new IllegalArgumentException("Value should be positive but was: " + val);
    }
}