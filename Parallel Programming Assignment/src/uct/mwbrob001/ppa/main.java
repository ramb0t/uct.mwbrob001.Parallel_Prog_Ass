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

public class main {

	/**
	 * @param args
	 */
	private static String params_file = "params.txt";
	
	public static void main(String[] args) {
		// Step one, read parameter file
		// comprising, n, data files to be read
		// a list of the n files
		// two ints, k & m comprising the data bin dims
		
		PreProcess preProcess = new PreProcess(params_file);
		
		
		
	}

}
