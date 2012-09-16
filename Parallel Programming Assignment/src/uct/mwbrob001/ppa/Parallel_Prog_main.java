/* File: main.java
 * CSC2002S Java Parrallel assignment
 * Author: MWBROB001
 * Date : September 2012
 * 
 * Description:
 *  main method contained here 
 * 
 */

package uct.mwbrob001.ppa;


public class Parallel_Prog_main {

	public static final boolean DEBUG = true; // DEBUG define. .
	
	
	private static String params_file = "data/params.txt";  // parameter file path
	
	// This will hold all the ant data.. 
	private static int[][] antGrid = null; 
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// Step one, read parameter file
		// comprising, n, data files to be read
		// a list of the n files
		// two ints, k & m comprising the data bin dims
		
		
		
		
		PreProcess preProcess = new PreProcess(params_file);
		
		System.out.println("binning data, this could take a while... ");
		antGrid = preProcess.doBinning(antGrid);
		print();
		
	}
	
	private static void print(){
		for (int[] a : antGrid){ // array of coloums
			for(int b : a){ // elements in coloumn
				System.out.print(b);
			}
			System.out.println();
		}
	}
	
	
	// getters and setters for antGrid
	// used to pass the grid bettween classes
	/**
	 * antGrid Getter
	 * @return
	 */
	public int[][] getAntGrid(){
		return antGrid;
	}
	/**
	 * antGrid Setter
	 * @param newAntGrid
	 */
	public void setAntGrid(int[][] newAntGrid){
		antGrid = newAntGrid;
	}

}
