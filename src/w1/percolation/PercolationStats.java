package w1.percolation;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

/**
 * Created by Daniel on 1/5/2017.
 */
public class PercolationStats {

    private double[] thresholds;        // store probability for every trial

    /**
     * Perform trials independent experiments on n*n grid
     * using Monte Carlo Simulation.
     * Store probability of every percolation to the array.
     *
     * @param n      size of the grid.
     * @param trials number of times to repeat .
     * @throws IllegalArgumentException if n <= 0 || trials <=0.
     */
    // perform trials independent experiments on an n-by-n grid
    public PercolationStats(int n, int trials) {
        if (n <= 0 || trials <= 0)
            throw new IllegalArgumentException();
        thresholds = new double[trials];
        for (int i = 0; i < trials; i++) {
            Percolation percolation = new Percolation(n);
            while (!percolation.percolates()) {
                percolation.open(StdRandom.uniform(n) + 1, StdRandom.uniform(n) + 1);
            }
            thresholds[i] = percolation.numberOfOpenSites() / (double) (n * n);
        }
    }

    /**
     * Sample mean of percolation threshold.
     *
     * @return return mean of percolation.
     */
    public double mean() {
        return StdStats.mean(thresholds);
    }

    /**
     * Sample standard deviation of percolation threshold.
     *
     * @return return standard deviation.
     */
    public double stddev() {
        return StdStats.stddev(thresholds);
    }

    /**
     * Low endpoint of 95% confidence interval
     *
     * @return return confidence low.
     */
    public double confidenceLo() {
        return mean() - ((1.96 * stddev()) / Math.sqrt(thresholds.length));
    }

    /**
     * High endpoint of 95% confidence interval
     *
     * @return return confidence high.
     */
    public double confidenceHi() {
        return mean() + ((1.96 * stddev()) / Math.sqrt(thresholds.length));
    }

    // test client
    public static void main(String[] args) {
        PercolationStats ps = new PercolationStats(200, 100);
        StdOut.printf("%-24s = %f\n", "mean", ps.mean());
        StdOut.printf("%-24s = %f\n", "stddev", ps.stddev());
        StdOut.printf("%-24s = %f, %f\n", "95% confidence interval", ps.confidenceLo(), ps.confidenceHi());
    }

}
