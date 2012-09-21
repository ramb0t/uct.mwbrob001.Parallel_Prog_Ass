/* File: Parallel_Prog_main.java
 * CSC2002S Java Parallel assignment
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
	public static final int DEBUG_LEVEL = 0; // DEBUG_LEVEL define gives me more debug flexablility . .
	
	// sets where the Query should come from
	//public static final String QUERY_SOURCE= "KEYBOARD"; 
	public static final String QUERY_SOURCE = "FILE"; 
	
	
	// file path defines
	private static final String params_file = "data/params.txt";  // parameter file path
	public static final String QUERY_FILE = "data/queries.txt";  // queries file path
	
	// This will hold all the ant data.. 
	private static AntGrid antGrid = null; 
	
	
	/**
	 * @param args
	 */
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		
		
		// Create an instance of the PreProcess class, and process the params file
		PreProcess preProcess = new PreProcess(params_file);
		
		// build the antGrid from the ant files 
		System.out.println("binning data, this could take a while... ");
		antGrid = preProcess.doBinning();
		
		// build the test 3 grid!
		// create an instance of the class
	    Smarter_Sequential test3 = new Smarter_Sequential(antGrid);
		
		// prints the grid out if debugging is enabled ...
		if(DEBUG && DEBUG_LEVEL == 1){   
			antGrid.print();
		}
		
		
		// Get the query/s
		//*********************************************************************
		QueryHandler QH = new QueryHandler(); 
		Query query = QH.getQuery();
		
		while(query != null){ // loop until queries done.. 
			
			
			System.out.println();
			System.out.println("************************************************");
			System.out.println("************************************************");
			System.out.println("Testing Query: " + query.toString());
		
		
			// Version1: Simple and Sequential
			//*********************************************************************
			
			// create an instance of the class
			Simple_Sequential test1 = new Simple_Sequential();
			
			// run the test
			System.out.println();
			System.out.println("************************************************");
			System.out.println("Running test 1, Simple and Sequential...");
			System.out.println();
			
			Results t1Result = test1.runTest(antGrid, query);
			System.out.println("The results are: " + t1Result.toString());
			
			// Version2: Simple and Parallel
			//*********************************************************************
			
			// create an instance of the class
			Simple_Parallel test2 = new Simple_Parallel();
			
			// run the test
			System.out.println();
			System.out.println("************************************************");
			System.out.println("Running test 2, Simple and Parallel...");
			System.out.println();
			
			Results t2Result = test2.runTest(antGrid, query);
			System.out.println("The results are: " + t2Result.toString());
			

			// Version3: Smarter and Sequential
			//*********************************************************************
			
			// run the test
			System.out.println();
			System.out.println("************************************************");
			System.out.println("Running test 3, Smarter and Sequential...");
			System.out.println();
			
			
			Results t3Result = test3.runTest(antGrid, query);
			System.out.println("The results are: " + t3Result.toString());
			
			

			// gets the next query
			query = QH.getQuery();
		} // end query loop
		
		test3.close(); // saves mem
		
		
		
		
		
	}
	
	
	
}
