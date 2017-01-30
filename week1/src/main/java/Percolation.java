import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private final int topVirtualSiteIndex;
    private final int bottomVirtualSiteIndex;
    private final int topRowIndex;
    private final int bottomRowIndex;
    private boolean[][] grid;

    private final int size;
    private int numberOfOpenSites = 0;
    private WeightedQuickUnionUF union;
    // used only to check if sites are full.
    private WeightedQuickUnionUF union4Full;

    // create n-by-n grid, with all sites blocked
    public Percolation(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("size of the grid should be positive");
        }
        size = n;
        grid = new boolean[size][size];
        topVirtualSiteIndex = size * size;
        bottomVirtualSiteIndex = size * size + 1;
        topRowIndex = 1;
        bottomRowIndex = size;
        // the n*n index will hold the virtual top site
        // the n*n +1 index will hold the virtual bottom side
        union = new WeightedQuickUnionUF(size * size + 2);

        // this union won't have virtual bottom site
        union4Full = new WeightedQuickUnionUF(size * size + 1);

        for (int col = 1; col <= size; col++) {
            // connect all sites from top row to the virtual top site
            // in both union and union4Full
            int siteInTopRow = mapTo1D(topRowIndex, col);
            union(siteInTopRow, topVirtualSiteIndex);


        }
    }

    // test client (optional)
    public static void main(String[] args) {

    }

    // open site (row, col) if it is not open already
    public void open(int row, int col) {
        validateRowCol(row, col);
        if (isOpen(row, col)) {
            return;
        }

        // open given cell
        setGridTrue(row, col);
        numberOfOpenSites++;

        // open connections to open neighbours
        int indexOfSiteInUnion = mapTo1D(row, col);
        if (row > 1) {
            // top neighbour
            if (isOpen(row - 1, col)) {
                int topNeighbourIndex = mapTo1D(row - 1, col);
                union(topNeighbourIndex, indexOfSiteInUnion);
            }
        }

        if (row < size) {
            if (isOpen(row + 1, col)) {
                int bottomNeighbour = mapTo1D(row + 1, col);
                union(bottomNeighbour, indexOfSiteInUnion);
            }
        }

        if (col > 1) {
            if (isOpen(row, col - 1)) {
                int leftNeighbour = mapTo1D(row, col - 1);
                union(leftNeighbour, indexOfSiteInUnion);
            }
        }

        if (col < size) {
            if (isOpen(row, col + 1)) {
                int rightNeighbour = mapTo1D(row, col + 1);
                union(rightNeighbour, indexOfSiteInUnion);
            }
        }

        if (row == size) {
            // connect all sites from bottom row to the virtual bottom site
            // only in union
            int siteInBottomRow = mapTo1D(bottomRowIndex, col);
            union.union(siteInBottomRow, bottomVirtualSiteIndex);
        }
    }


    // is site (row, col) open?
    public boolean isOpen(int row, int col) {
        validateRowCol(row, col);
        return grid(row, col);
    }


    // is site (row, col) full?
    public boolean isFull(int row, int col) {
        validateRowCol(row, col);
        if (!isOpen(row, col)) {
            return false;
        }
        // is given site connected to the virtual top site?
        int currentIndex = mapTo1D(row, col);
        return union4Full.connected(topVirtualSiteIndex, currentIndex);
    }

    // number of open sites
    public int numberOfOpenSites() {
        return numberOfOpenSites;
    }

    // does the system percolate?
    public boolean percolates() {
        return union.connected(topVirtualSiteIndex, bottomVirtualSiteIndex);
    }


    private void validateRowCol(int row, int col) {
        if (areOutOfGridRange(row, col)) {
            throw new IndexOutOfBoundsException("Row and Col should be in range [1," + size + "]." +
                    " But the values are: " + row + ", " + col);
        }
    }

    private boolean areOutOfGridRange(int row, int col) {
        return isOutOfGridRange(row) || isOutOfGridRange(col);
    }

    private boolean isOutOfGridRange(int value) {
        return value < 1 || value > size;
    }

    private int mapTo1D(int i, int j) {
        i--;
        j--;
        return i * size + j;
    }

    private void setGridTrue(int row, int col) {
        grid[row - 1][col - 1] = true;
    }

    private boolean grid(int row, int col) {
        return grid[row - 1][col - 1];
    }

    private void union(int q, int p) {
        union.union(p, q);
        union4Full.union(p, q);
    }

}