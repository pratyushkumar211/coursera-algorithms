import java.util.Arrays;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
	
	// n is the grid size
	private int n;
	
	// grid is the WeightedQuickUnion Data Structure to facilitate 
	// open, isOpen operations etc
	private WeightedQuickUnionUF grid;
	private boolean[][] oc;
	private int numOpen;
	
	public Percolation(int size)	{	
		
	n=size;
	grid=new WeightedQuickUnionUF(n);
	oc=new boolean[n][n];	
	}
	
	/**
	 * 
	 * @param x: x coordinate
	 * @param y: y coordinate
	 * @return: 1D representation of 2D coordinates
	 */
	private int to1D(int x, int y) {	
		return (y-1)*n+x-1;
	}
		
	/**
	 * 
	 * @param x: x coordinate
	 * @param y: y coordinate
	 * @return: true if indices are valid
	 */
	private boolean assertInd(int x, int y) {
		
		if (x<1 || x>n) {
			return false;
		}
		
		if(y<1 || y>n) {
			return false;
		}
		
		return true;
	}
	
	/**
	 * 
	 * @param row: x coordinate
	 * @param col: y coordinate
	 * Opens the grid with these coordinates and connects to neighboring 
	 * open coordinates
	 */
	public void open(int row, int col) {
		
		if (assertInd(row,col)==false) {
		throw new IndexOutOfBoundsException("row index i out of bounds");
		}
		
		// Marking the site as open
		oc[row-1][col-1]=true;
		numOpen++;
		
		// Checking the neighbors, if they are open. Connect!
		if (this.isOpen(row-1, col)==true){
		grid.union(to1D(row-1, col), to1D(row-1,col));
		}
		
		if (this.isOpen(row+1, col)==true){
		grid.union(to1D(row+1, col), to1D(row-1,col));
		}

		if (this.isOpen(row, col-1)==true){
		grid.union(to1D(row, col-1), to1D(row-1,col));
		}

		if (this.isOpen(row, col+1)==true){
		grid.union(to1D(row, col+1), to1D(row-1,col));
		}

	}
	
	/**
	 * 
	 * @param row: x coordinate
	 * @param col: y coordinate
	 * @return: true if the site is open
	 */
	public boolean isOpen(int row, int col) {
		
		if (assertInd(row,col)==false) {
		throw new IndexOutOfBoundsException("row index i out of bounds");
		}
		
		return oc[row-1][col-1];
		
	}
	
	/**
	 * 
	 * @param row: x coordinate
	 * @param col: y coordinate
	 * @return: true if the site is full
	 */
	public boolean isFull(int row, int col) {
		
		if (assertInd(row,col)==false) {
		throw new IndexOutOfBoundsException("row index i out of bounds");
		}
		
		// A site is not full if it is not open
		if (isOpen(row,col)==false) {
			return false;
		}
		
		//Looping through the open sites on the top row and checking 
		for (int i=1; i<=n; i++) {
			
			if(isOpen(1,i)==true) {
				
				if (grid.connected(to1D(row,col), to1D(1,i))==true) {
					return true;
				}
				
			}
			
		}
		
		return false;
	}
	
	/** returns number of open sites */
	public int numberOfOpenSites() {
		return numOpen;
	}
	
	/** returns true if percolates */
	public boolean percolates() {
		
		//Looping through the open sites on the top row and checking 
		for (int i=1; i<=n; i++) {
							
				if (this.isFull(n, i)==true) {
					return true;				
			}
			
		}

		return true;
	}

	public static void main(String[] args) {
		 
		int n=5;
		Percolation test =new Percolation(n);
		System.out.println("Percolation Class");
		
		int x=3;
		int y=2;
		int z=test.to1D(x, y);
		
		//System.out.print(z);
		
		boolean[][] oc =new boolean[5][2];
		//Arrays.fill(oc, false);
		oc[0][0]=true;
		System.out.print(oc[0][0]);
		
	}

}
