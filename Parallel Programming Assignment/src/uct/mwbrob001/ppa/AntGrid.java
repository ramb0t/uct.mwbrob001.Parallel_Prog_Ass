package uct.mwbrob001.ppa;

/**
 * AntGrid class to store the histogram
 * @author RobThePyro
 *
 */
public class AntGrid {
	
	// global class variables
	private int[][] antArr; // used to store the ant histogram
	private int count;      // how many data points there are in the grid
	
	private int k, m;       // store the bin sizes
	private int xOffset;
	private int yOffset;    // data offsets
	
	
	
	/**
	 * Constructor for the AntGrid
	 * @param xbloc
	 * @param ybloc
	 * @param count
	 */
	public AntGrid(int xbloc, int ybloc){
		//init the ant grid.
		this.setAntArr(new int[xbloc][ybloc]);
		this.setCount(0);
		
	}
	
	/**
	 * Constructor for the antGrid taking a predefined int[][]
	 * @param antArr
	 * @param count
	 */
	public AntGrid(int[][] antArr, int count){
		//init the ant grid.
		this.setAntArr(antArr);
		this.setCount(count); 	
	}
	
	/**
	 * Constructor for the antGrid taking a predefined int[][], counter and bin sizes and offsets
	 * @param antArr
	 * @param count
	 * @param k
	 * @param m
	 */
	public AntGrid(int[][] antArr, int count, int k, int m, int xOff, int yOff){
		//init the ant grid.
		this.setAntArr(antArr);
		this.setCount(count);
		this.setK(k);
		this.setM(m);
		this.setxOffset(xOff);
		this.setyOffset(yOff);
	}
	
	// printing functions
	public void print(){
		for (int[] a : antArr){ // array of coloums
			for(int b : a){ // elements in coloumn
				System.out.print(b);
			}
			System.out.println();
		}
	}
	
	
	// getters and setters:
	// for count
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}

	
	// for the grid.
	public int[][] getAntArr() {
		return antArr;
	}
	public void setAntArr(int[][] antArr) {
		this.antArr = antArr;
	}
	
	
	

	public int getK() {
		return k;
	}
	public void setK(int k) {
		this.k = k;
	}

	public int getM() {
		return m;
	}
	public void setM(int m) {
		this.m = m;
	}

	public int getxOffset() {
		return xOffset;
	}

	public void setxOffset(int xOffset) {
		this.xOffset = xOffset;
	}

	public int getyOffset() {
		return yOffset;
	}

	public void setyOffset(int yOffset) {
		this.yOffset = yOffset;
	}
	
	
	
	
	

}
