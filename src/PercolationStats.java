
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.Stopwatch;
import edu.princeton.cs.algs4.StopwatchCPU;

public class PercolationStats {

    /*
     * 
     * 
     * public PercolationStats(int n, int trials) // perform trials independent
     * experiments on an n-by-n grid public double mean() // sample mean of
     * percolation threshold public double stddev() // sample standard deviation
     * of percolation threshold public double confidenceLo() // low endpoint of
     * 95% confidence interval public double confidenceHi() // high endpoint of
     * 95% confidence interval
     * 
     * 
     */
    private int gridSize;
    private int numberOfTrails;
    private double[] fractions;

    public PercolationStats(int n, int trials) {
        if (n <= 0 || trials <= 0)
            throw new IllegalArgumentException();

        numberOfTrails = trials;
        gridSize = n;
        fractions = new double[numberOfTrails];
        double totalOpenSites = 0;
        for (int i = 0; i < numberOfTrails; i++) {
            Percolation percolation = new Percolation(gridSize);
            // for (int k = 1; k <= gridSize * gridSize; i++) {
            while (!(percolation.percolates())) {
                int row = StdRandom.uniform(1, gridSize + 1);
                int col = StdRandom.uniform(1, gridSize + 1);
                percolation.open(row, col);
                // System.out.println(row + "-"+ col);

            }
            totalOpenSites = percolation.numberOfOpenSites();
            fractions[i] = totalOpenSites / (gridSize * gridSize);
            // System.out.println("Completed: "+ i + " With total Sites Open: "+
            // totalOpenSites);
        }

    }

    public double mean() {
        return StdStats.mean(fractions);
    }

    public double stddev() {
        return StdStats.stddev(fractions);
    }

    public double confidenceLo() {
        double lo = mean() - ((1.96 * (Math.sqrt(stddev())) / Math.sqrt(fractions.length)));
        return lo;
    }

    public double confidenceHi() {
        double hi = mean() + ((1.96 * (Math.sqrt(stddev())) / Math.sqrt(fractions.length)));
        return hi;
    }

    public static void main(String[] args) {

        /*
         * for (int i =0;i<30;i++){ System.out.println(StdRandom.uniform(1,
         * 100+1) + "--"+ StdRandom.uniform(1, 100+1));
         * 
         * }
         */
        Stopwatch watch = new Stopwatch();
        StopwatchCPU cpu = new StopwatchCPU();
        
        PercolationStats stats1 = new PercolationStats(200, 100);
        System.out.println("Means: " + stats1.mean());
        System.out.println("stdev: " + stats1.stddev());
        System.out.println("95% confidence interval: [" + stats1.confidenceLo() + ", " + stats1.confidenceHi() + " ]");

        PercolationStats stats2 = new PercolationStats(200, 100);
        System.out.println("Means: " + stats2.mean());
        System.out.println("stdev: " + stats2.stddev());
        System.out.println("95% confidence interval: [" + stats2.confidenceLo() + ", " + stats2.confidenceHi() + " ]");

        PercolationStats stats3 = new PercolationStats(2, 100000);
        System.out.println("Means: " + stats3.mean());
        System.out.println("stdev: " + stats3.stddev());
        System.out.println("95% confidence interval: [" + stats3.confidenceLo() + ", " + stats3.confidenceHi() + " ]");
        System.out.println(watch.elapsedTime());
        System.out.println(cpu.elapsedTime());

    }

}
