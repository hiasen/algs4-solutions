import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    static private final int topSite = 0;
    static private final int bottomSite = 1;

    private final int n;
    private final WeightedQuickUnionUF uf;
    private final boolean[][] opened;
    private int numOpenedSites = 0;

    public Percolation(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException();
        }
        this.n = n;
        uf = new WeightedQuickUnionUF(n*n+2);
        opened = new boolean[n][n];
    }

    public void open(int row, int col) {
        if (!isOpen(row, col)) {
            opened[row-1][col-1] = true;
            numOpenedSites++;
            unionNeighborSites(row, col);
        }
    }
    public boolean isOpen(int row, int col) {
        checkRange(row, col);
        return opened[row-1][col-1];
    }

    private void checkRange(int row, int col) {
        if (row <= 0 || row > n || col <= 0 || col > n) {
            throw new IllegalArgumentException();
        }
    }

    private void unionNeighborSites(int row, int col) {
        int site = getSite(row, col);
        if (row == 1) {
            uf.union(site, topSite);
        } else if (isOpen(row-1, col)){
            uf.union(site, getSite(row-1, col));
        }
        if (row == n) {
            uf.union(site, bottomSite);
        } else if (isOpen(row+1, col)) {
            uf.union(site, getSite(row+1, col));
        }
        if (col != 1 && isOpen(row, col-1)) {
            uf.union(site, getSite(row, col-1));
        }
        if (col != n && isOpen(row, col+1)) {
            uf.union(site, getSite(row, col+1));
        }
    }

    private int getSite(int row, int col) {
        checkRange(row, col);
        return (row-1) * n +col+1;
    }

    public boolean isFull(int row, int col) { return uf.connected(topSite, getSite(row, col)); }
    public int numberOfOpenSites() { return numOpenedSites; }
    public boolean percolates() { return uf.connected(topSite, bottomSite); }
}

