import java.util.Arrays;

public class Percolation {
	
	private Boolean[][] grid;
			
	public Percolation(int n)	{	
	grid=new Boolean[n][n];
	Arrays.fill(grid, false);
	}

	public void open(int row, int col) {
		
		if(grid[row][col].equals(false)) {
			grid[row][col]=true;
		}
		
	}
	public boolean isOpen(int row, int col) {
		return grid[row][col]==true;
	}
	public boolean isFull(int row, int col) {
		
	}
	public int numberOfOpenSites() {
		
	}
	public boolean percolates() {
		
	}

	public static void main(String[] args) {
		Percolation test =new Percolation();
		System.out.println("Percolation Class");
	}

}
