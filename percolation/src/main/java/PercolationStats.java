import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

/** Runs an experiment to estimate percolation probability.
 *
 */
public class PercolationStats {
    private static final double CONFIDENCE_95 = 1.96;
    private final int n;
    private final int trials;
    private final double xbar;
    private final double s;

    public PercolationStats(int n, int trials) {
        if (n <= 0 || trials <= 0) {
            throw new IllegalArgumentException();
        }
        this.n = n;
        this.trials = trials;
        int[] results = new int[trials];
        for (int i = 0; i < trials; i++) {
            results[i] = doTrial();
        }

        xbar = StdStats.mean(results)/(n*n);
        s = StdStats.stddev(results)/(n*n);
    }

    private int doTrial() {
        Percolation p = new Percolation(n);
        while (!p.percolates()) {
            int row = StdRandom.uniform(n) + 1;
            int col = StdRandom.uniform(n) + 1;
            p.open(row, col);
        }
        return p.numberOfOpenSites();
    }

    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        int trials = Integer.parseInt(args[1]);
        PercolationStats ps = new PercolationStats(n, trials);
        StdOut.printf("mean                    = %f\n", ps.mean());
        StdOut.printf("stddev                  = %f\n", ps.stddev());
        StdOut.printf("95%% confidence interval = [%f, %f]\n", ps.confidenceLo(), ps.confidenceHi());
    }

    public double mean() {
        return xbar;
    }

    public double stddev() {
        return s;
    }

    public double confidenceLo() {
        return xbar - CONFIDENCE_95*s/Math.sqrt(trials);
    }

    public double confidenceHi() {
        return xbar + CONFIDENCE_95*s/Math.sqrt(trials);
    }
}
