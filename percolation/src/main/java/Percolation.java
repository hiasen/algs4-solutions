import edu.princeton.cs.algs4.WeightedQuickUnionUF;

/**
 * Implements the Percolation data type.
 */
public class Percolation {

    private static final int topSite = 0;
    private static final int bottomSite = 1;

    private final int n;
    private final WeightedQuickUnionUF uf;
    private final boolean[][] opened;
    private int numOpenedSites = 0;

    public Percolation(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException();
        }
        this.n = n;
        uf = new WeightedQuickUnionUF(n * n + 2);
        opened = new boolean[n][n];
    }

    public void open(int row, int col) {
        if (!isOpen(row, col)) {
            opened[row - 1][col - 1] = true;
            numOpenedSites++;
            unionNeighborSites(row, col);
        }
    }
    public boolean isOpen(int row, int col) {
        checkRange(row, col);
        return opened[row - 1][col - 1];
    }

    private void checkRange(int row, int col) {
        if (row <= 0 || row > n || col <= 0 || col > n) {
            throw new IllegalArgumentException();
        }
    }

    private void unionNeighborSites(int row, int col) {
        int site = getSite(row, col);
        if (row <= 1) {
            uf.union(site, topSite);
        }
        if (row >= n) {
            uf.union(site, bottomSite);
        }
        tryUnion(site, row - 1, col);
        tryUnion(site, row + 1, col);
        tryUnion(site, row, col - 1);
        tryUnion(site, row, col + 1);
    }
    private void tryUnion(int site, int row, int col) {
        try {
            if (isOpen(row, col)) {
                uf.union(site, getSite(row, col));
            }
        } catch (IllegalArgumentException ignored) {
        }
    }
    private int getSite(int row, int col) {
        checkRange(row, col);
        return (row - 1) * n + col + 1;
    }

    public boolean isFull(int row, int col) {
        return uf.connected(topSite, getSite(row, col));
    }
    public int numberOfOpenSites() {
        return numOpenedSites;
    }
    public boolean percolates() {
        return uf.connected(topSite, bottomSite);
    }
}

