import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

/**
 * Represents a Monte Carlo simulation used to estimate the percolation threshold
 * in a percolation system.
 * <p>
 *     The simulation is carried out by creating a number of trials as independent Percolation objects of equal size.
 *     In each trial, random sites are opened until the system percolates,
 *     and the number of opened sites when the percolation happens are saved.
 * </p>
 * <p>
 *     The mean value and standard deviation of the percolation threshold is also computed.
 * </p>
 */
public class PercolationStats {
    private static final double CONFIDENCE_95 = 1.96;
    private final int sideLength;
    private final int trials;
    private final double meanValue;
    private final double standardDeviation;

    /**
     * Creates and runs the simulation.
     * @param n The number of rows and columns in each percolation grid.
     * @param trials The number of independent trials to do.
     */
    public PercolationStats(int n, int trials) {
        if (n <= 0 || trials <= 0) {
            throw new IllegalArgumentException();
        }
        this.sideLength = n;
        this.trials = trials;
        int[] results = new int[trials];
        for (int i = 0; i < trials; i++) {
            results[i] = doTrial();
        }

        meanValue = StdStats.mean(results) / (n * n);
        standardDeviation = StdStats.stddev(results) / (n * n);
    }

    private int doTrial() {
        Percolation p = new Percolation(sideLength);
        while (!p.percolates()) {
            int row = StdRandom.uniform(sideLength) + 1;
            int col = StdRandom.uniform(sideLength) + 1;
            p.open(row, col);
        }
        return p.numberOfOpenSites();
    }


    /**
     * Runs the simulation and prints the results.
     * @param args {n, trials}
     */
    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        int trials = Integer.parseInt(args[1]);
        PercolationStats ps = new PercolationStats(n, trials);
        StdOut.printf("mean                    = %f\n", ps.mean());
        StdOut.printf("stddev                  = %f\n", ps.stddev());
        StdOut.printf("95%% confidence interval = [%f, %f]\n", ps.confidenceLo(), ps.confidenceHi());
    }

    /**
     * Return computed mean value of percolation threshold.
     * @return Mean value of percolation threshold.
     */
    public double mean() {
        return meanValue;
    }

    /**
     * Return computed standard deviation of percolation threshold.
     * @return Standard deviation of percolation threshold.
     */
    public double stddev() {
        return standardDeviation;
    }

    /**
     * Lower bound of 95% confidence interval for percolation threshold.
     * @return Lower bound of confidence interval.
     */
    public double confidenceLo() {
        return meanValue - CONFIDENCE_95 * standardDeviation / Math.sqrt(trials);
    }

    /**
     * Upper bound of 95% confidence interval for percolation threshold.
     * @return Upper bound of confidence interval.
     */
    public double confidenceHi() {
        return meanValue + CONFIDENCE_95 * standardDeviation / Math.sqrt(trials);
    }
}
