import edu.princeton.cs.algs4.WeightedQuickUnionUF;

/**
 * Models a percolation system.
 * <p>The model contains a grid of sites which can be either open or blocked.</p>
 */
public class Percolation {

    private static final int TOP_SITE = 0;
    private static final int BOTTOM_SITE = 1;

    private final int sideLength;
    private final WeightedQuickUnionUF uf;
    private final WeightedQuickUnionUF ufWithoutBottomSite;
    private final boolean[][] opened;
    private int numOpenedSites = 0;

    /**
     * Creates n-by-n grid, with all sites initially blocked.
     * @param n Number of rows and columns.
     */
    public Percolation(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException();
        }
        this.sideLength = n;
        uf = new WeightedQuickUnionUF(n * n + 2);
        ufWithoutBottomSite = new WeightedQuickUnionUF(n * n + 2);

        opened = new boolean[n][n];
    }

    /**
     * Open a site.
     * @param row The row of the site.
     * @param col The column of the site.
     */
    public void open(int row, int col) {
        if (!isOpen(row, col)) {
            opened[row - 1][col - 1] = true;
            numOpenedSites++;
            unionNeighborSites(row, col);
        }
    }

    /**
     * Check if a site has been previously opened.
     * @param row The row of the site.
     * @param col The column of the site.
     * @return true if site is open, false if blocked.
     */
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
        return row >= 1 && row <= sideLength && col >= 1 && col <= sideLength;
    }

    private void unionNeighborSites(int row, int col) {
        int site = getSite(row, col);
        if (row == 1) {
            uf.union(site, TOP_SITE);
            ufWithoutBottomSite.union(site, TOP_SITE);
        }
        if (row == sideLength) {
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
        return (row - 1) * sideLength + col + 1;
    }

    /**
     * Check if site is full.
     *
     *  <p>A full site is an open site that can be connected to an open site in the top row
     *  via a chain of neighboring (left, right, up, down) open sites.</p>
     * @param row The row of the site.
     * @param col The column of the site.
     * @return true if site is full.
     */
    public boolean isFull(int row, int col) {
        return ufWithoutBottomSite.connected(TOP_SITE, getSite(row, col));
    }

    /**
     * Return the number of opened sites.
     * @return Number of opened sites.
     */
    public int numberOfOpenSites() {
        return numOpenedSites;
    }

    /**
     * Check if system percolates.
     * The system percolates if there is a full site in the bottom row.
     * @return true if system percolates.
     */
    public boolean percolates() {
        return uf.connected(TOP_SITE, BOTTOM_SITE);
    }
}

