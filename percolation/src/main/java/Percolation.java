import edu.princeton.cs.algs4.WeightedQuickUnionUF;

/**
 * Implements the Percolation data type.
 */
public class Percolation {

    private static final int TOP_SITE = 0;
    private static final int BOTTOM_SITE = 1;

    private final int n;
    private final WeightedQuickUnionUF uf;
    private final WeightedQuickUnionUF ufWithoutBottomSite;
    private final boolean[][] opened;
    private int numOpenedSites = 0;

    public Percolation(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException();
        }
        this.n = n;
        uf = new WeightedQuickUnionUF(n * n + 2);
        ufWithoutBottomSite = new WeightedQuickUnionUF(n * n + 2);

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
        if (!isInRange(row, col)) {
            throw new IllegalArgumentException();
        }
    }
    private boolean isInRange(int row, int col) {
        return row >= 1 && row <= n && col >= 1 && col <= n;
    }

    private void unionNeighborSites(int row, int col) {
        int site = getSite(row, col);
        if (row == 1) {
            uf.union(site, TOP_SITE);
            ufWithoutBottomSite.union(site, TOP_SITE);
        }
        if (row == n) {
            uf.union(site, BOTTOM_SITE);
        }
        unionIfInRangeAndOpen(site, row - 1, col);
        unionIfInRangeAndOpen(site, row + 1, col);
        unionIfInRangeAndOpen(site, row, col - 1);
        unionIfInRangeAndOpen(site, row, col + 1);
    }
    private void unionIfInRangeAndOpen(int site, int row, int col) {
        if (isInRange(row, col) && isOpen(row, col)) {
            uf.union(site, getSite(row, col));
            ufWithoutBottomSite.union(site, getSite(row, col));
        }
    }
    private int getSite(int row, int col) {
        checkRange(row, col);
        return (row - 1) * n + col + 1;
    }

    public boolean isFull(int row, int col) {
        return ufWithoutBottomSite.connected(TOP_SITE, getSite(row, col));
    }
    public int numberOfOpenSites() {
        return numOpenedSites;
    }
    public boolean percolates() {
        return uf.connected(TOP_SITE, BOTTOM_SITE);
    }
}

