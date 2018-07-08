import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
	
	private double[] expts;
	private double mean;
	private double stddev;
	private double cl;
	private double ch;
	private int T;
	
	public PercolationStats(int n, int trials) {
		
		// Throw an exception if n <0
		if (n<=0) {
			throw new IllegalArgumentException ();
		}

		// Throw an exception if n <0
		if (trials<=0) {
			throw new IllegalArgumentException ();
		}

		
		expts=new double[trials];
		T=trials;
		int row;
		int col;
		
		Percolation grid;
		
		for (int i=1;i<=trials;i++) {
			
			
			grid = new Percolation(n);
			
			while (grid.percolates()==false) {
				
				row=StdRandom.uniform(1, n+1);
				col=StdRandom.uniform(1,n+1);
						
				grid.open(row, col);
				
				
			}
			
			expts[i-1]=((double)grid.numberOfOpenSites()/(n*n));

		}
			
		mean=StdStats.mean(expts);
		stddev=StdStats.stddev(expts);
		cl=mean-(1.96*stddev/Math.sqrt(T));
		ch=mean+(1.96*stddev/Math.sqrt(T));
	
	}
	
	public double mean() {
		
		return mean;
		
		
	}
	public double stddev() {
		
		return stddev;

	}
	public double confidenceLo() {
		
		return cl;
		
	}
	public double confidenceHi() {
		
		return ch;
	}

	public static void main(String[] args) {
		
	     int n = StdIn.readInt();
	     int T =StdIn.readInt();
	     
		 System.out.println(n);
		 System.out.println(T);

	     	     
	     // Creating a PercolationStats object and calculation statistics
	     PercolationStats PS=new PercolationStats(n,T);
	     double mean=PS.mean();
	     double stddev = PS.stddev();
	     double cl=PS.confidenceLo();
	     double ch=PS.confidenceHi();
	     
	     // Printing the statistics
	     //System.out.println("Trial 1 ="+ PS.expts[0]);
	     System.out.println("mean = "+ mean);
	     System.out.println("stddev = " + stddev);
	     System.out.println("95% confidence interval = ["+cl + "," + ch +"]");

		
	}

}
