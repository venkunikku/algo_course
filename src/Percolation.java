import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private boolean[][] grid;
    private int top = 0;
    private int bottom;
    private WeightedQuickUnionUF weightedQU;
    private int maxSizeOfArray;

    public Percolation(int arraySize) {

        if (arraySize < 1)
            throw new IllegalArgumentException();

        bottom = arraySize * arraySize + 1;
        maxSizeOfArray = arraySize;
        grid = new boolean[arraySize][arraySize];
        weightedQU = new WeightedQuickUnionUF((arraySize * arraySize) + 2);

    }

    public void open(int row, int col) {

        if (row < 1 || col < 1)
            throw new IllegalArgumentException();
        if (!isOpen(row, col)) {
            // first row
            grid[row - 1][col - 1] = true;

            if ((row - 1) == 0) {
                // linking to the top virtual index.
                weightedQU.union(xyTo1D(row, col), top);
            }
            // last row
            if (row == maxSizeOfArray) {
                // linking to the bottom virtual index
                weightedQU.union(xyTo1D(row, col), bottom);

            }

            if (isLeft(row, col) && isOpen(row, col - 1)) {
                weightedQU.union(xyTo1D(row, col), xyTo1D(row, col - 1));
            }
            if (isRight(row, col) && isOpen(row, col + 1)) {
                weightedQU.union(xyTo1D(row, col), xyTo1D(row, col + 1));
            }
            if (isTop(row, col) && isOpen(row - 1, col)) {
                weightedQU.union(xyTo1D(row, col), xyTo1D(row - 1, col));
            }
            if (isBottom(row, col) && isOpen(row + 1, col)) {
                weightedQU.union(xyTo1D(row, col), xyTo1D(row + 1, col));
            }
        }

    }

    private boolean isLeft(int row, int col) {
        return col - 2 >= 0;
    }

    private boolean isRight(int row, int col) {
        return col < maxSizeOfArray;
    }

    private boolean isTop(int row, int col) {
        return row - 2 >= 0;
    }

    private boolean isBottom(int row, int col) {
        return row < maxSizeOfArray;
    }

    public boolean isOpen(int row, int col) {
        return grid[row - 1][col - 1];
    }

    public boolean isFull(int row, int col) {
        if (row < 0 || col > maxSizeOfArray || row > maxSizeOfArray || col < 0) {
            throw new IndexOutOfBoundsException();
        }

        /*
         * boolean flag = false; if (isLeft(row, col) && isOpen(row, col - 1)) {
         * flag = true; } if (isRight(row, col) && isOpen(row, col + 1)) { flag
         * = true; } if (isTop(row, col) && isOpen(row - 1, col)) { flag = true;
         * } if (isBottom(row, col) && isOpen(row + 1, col)) { flag = true; }
         * return flag;
         */
        if (isOpen(row, col)) {
            return weightedQU.connected(top, xyTo1D(row, col));
        } else {
            return false;
        }
    }

    public int numberOfOpenSites() {
        int total = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid.length; j++) {
                if (grid[i][j]) {
                    total += 1;
                }
            }
        }
        return total;
    }

    public boolean percolates() {

        return weightedQU.connected(top, bottom);
    }

    private int xyTo1D(int row, int col) {
        return (maxSizeOfArray * (row - 1)) + (col - 1);
    }

    public static void main(String[] args) {
        System.out.println("Hello World!");
        System.out.println(System.getProperty("path.separator"));
        System.out.println((20 * 17) + 1);
        
        byte a = 0;
        byte b = 1;
        byte c = 2;
        byte d = 4;
        byte e = 8;
        System.out.println(b&c&d);
        
    }

}
