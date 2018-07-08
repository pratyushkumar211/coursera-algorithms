import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
	
	// n is the grid size
	private int n;
	
	// grid is the WeightedQuickUnion Data Structure to facilitate 
	// open, isOpen operations etc
	private WeightedQuickUnionUF grid;
	private boolean[][] oc;
	private int numOpen;
	
	/** Constructor*/ 
	public Percolation(int size)	{
		
		// Throw an exception if n <0
		if (size<=0) {
			throw new IllegalArgumentException ();
		}
		
	n=size;
	grid=new WeightedQuickUnionUF(n*n+2);
	oc=new boolean[n][n];
	
	// Creating a Virtual top and bottom site and connecting
	for(int i=1;i<=n;i++) {
		grid.union(n*n, to1D(1,i));
		grid.union(n*n+1, to1D(n,i));
	}
	
	}
	
	/**
	 * Converts 2D coordinates into a unique 1D representation
	 * 
	 * @param x: x coordinate
	 * @param y: y coordinate
	 * @return: 1D representation of 2D coordinates
	 */
	private int to1D(int x, int y) {	
		
		if (x>1) {
		return (x-1)*n+y-1;
		}
		else {
			return y-1;
		}
	}
		
	/**
	 * Checks if a pair of x and y are valid
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
	 * 
	 * Opens the grid with these coordinates and connects to neighboring 
	 * open coordinates
	 */
	public void open(int row, int col) {
		
		if (assertInd(row,col)==false) {
		throw new IllegalArgumentException();
		}
		
		// Marking the site as open
		if(oc[row-1][col-1]==false) {
		oc[row-1][col-1]=true;
		numOpen++;
		}
		//System.out.println("row:"+row+" col:"+col);
		//System.out.println(to1D(row, col));


		
		// Checking the neighbors, if they are open. Connect!
		if(assertInd(row-1,col)==true) {
		if (this.isOpen(row-1, col)==true){
			//System.out.println("row:"+row+" col:"+col);
			//System.out.println(to1D(row-1, col));
		grid.union(to1D(row-1, col), to1D(row,col));
		}
		}
		
		if(assertInd(row+1,col)==true) {
		if (this.isOpen(row+1, col)==true){
			//System.out.println(to1D(row+1, col));
		grid.union(to1D(row+1, col), to1D(row,col));
		}
		}

		if(assertInd(row,col-1)==true) {
		if (this.isOpen(row, col-1)==true){
			//System.out.println(to1D(row, col-1));
		grid.union(to1D(row, col-1), to1D(row,col));
		}
		}
		
		if(assertInd(row,col+1)==true) {
		if (this.isOpen(row, col+1)==true){
			//System.out.println(to1D(row, col+1));
		grid.union(to1D(row, col+1), to1D(row,col));
		}
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
		throw new IllegalArgumentException ();
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
		throw new IllegalArgumentException ();
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
		
		return grid.connected(n*n, n*n+1);
	}

	public static void main(String[] args) {
		 
		Percolation test =new Percolation(5);
		test.open(1, 1);
		test.open(1, 2);
		test.open(2, 1);
		test.open(3, 1);
		test.open(4, 1);
		test.open(4, 2);
		test.open(4, 3);
		test.open(4, 4);
		test.open(4, 5);
		test.open(5, 4);

		System.out.println(test.numOpen);
		System.out.println(test.percolates());

		
	}

}
