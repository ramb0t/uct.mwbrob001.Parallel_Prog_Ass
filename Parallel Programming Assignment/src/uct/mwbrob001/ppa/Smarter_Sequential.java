/* File: Smarter_Sequential.java
 * CSC2002S Java Parallel assignment
 * Author: MWBROB001
 * Date : September 2012
 * 
 * Description:
 *  Processes queries in O(1) time using some clever maths :P
 * 
 */
package uct.mwbrob001.ppa;


/**
 * performs preprocessing on the grid, then makes O(1) queries :D
 * @author Rambo
 *
 */
public class Smarter_Sequential {
	
	int[][] v3Grid;
	
	/**
	 * takes the antgrid and creates a new grid from it, that can be easily queried
	 * @param antGrid
	 */
	public Smarter_Sequential(AntGrid antGrid){
		// start your engines! 
		Timer tTimer = new Timer();
		tTimer.start_ns_timer();
		
		// get the grid
		int[][] aGrid = antGrid.getAntArr();
		
		// init the new grid
		// needs to be bigger on each side by 1? 
		v3Grid = new int[aGrid.length+1][aGrid[0].length+1];
		
		// build the new grid
		// random 1's offset the grid by 1 xD 
		// this is do so the array is 'surrounded' by zero's on 
		// allowing the algorithm to process queries 'on the edges' 
		for (int x = 1; x <= aGrid.length; x++){
			for (int y = aGrid[x-1].length-1 ; y >= 0; y--){ // start top left
				if (x > 1 & y < aGrid[x-1].length-1){ // niether first row or column
					v3Grid[x][y] = aGrid[x-1][y] + v3Grid[x-1][y] + v3Grid[x][y+1] - v3Grid[x-1][y+1];
					
				}else if (x > 1 & y == aGrid[x-1].length-1){ // first row only
					v3Grid[x][y] = aGrid[x-1][y] + v3Grid[x-1][y];
					
				}else if (x==1 & y < aGrid[x-1].length-1 ){ // first column only
					v3Grid[x][y] = aGrid[x-1][y] + v3Grid[x][y+1];
					
				}else if(x==1 & y == aGrid[x-1].length-1 ){ // first column first row 
					v3Grid[x][y] = aGrid[x-1][y];
				}
				
			}
		}
		
		System.out.println();
		System.out.println("v3Grid build time.. ");
		tTimer.finish_ns_timer_toSyso();
		
		
		
	}
	
	public void print(){
		int count = 0;
		for (int[] x : v3Grid){
			System.out.print(count++ + "_ ");
			for (int y : x){
				System.out.print(y+" | ");
			}
			System.out.println();
			
		}
	}

	
	public Results runTest(AntGrid antGrid, Query query){
		Timer tTimer = new Timer();
		tTimer.start_ns_timer();
		
		// get the query co-ords
		// +1's should center ?
		int xMin = query.getqxMin() +1;
		int xMax = query.getqxMax() +1;
		
		int yMin = query.getqyMin() ;
		int yMax = query.getqyMax() ;
		
		// now count the datapoints
		int dpCount = 0;
		
		// bottom right
		dpCount = v3Grid[xMax][yMin];
		
		// above top right
		dpCount = dpCount - v3Grid[xMax][yMax+1];
		
		// left of bottom left
		dpCount = dpCount - v3Grid[xMin-1][yMin];
		
		// above and left upper left
		dpCount = dpCount + v3Grid[xMin-1][yMax+1];
		
		// work the percentage out.. 
		double dpPercentage = ((double)(dpCount * 100))/(double)(antGrid.getCount());
		
		// return the result and finish the timer
		return new Results(dpCount, dpPercentage, tTimer.finish_ns_timer());
		
		
	}
	
	public void close(){
		v3Grid = null;
	}
	
}
