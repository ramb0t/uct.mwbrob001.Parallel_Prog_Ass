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

import java.util.ArrayList;


public class Parallel_Prog_main {

	public static final boolean DEBUG = true; // DEBUG define. .
	public static final int DEBUG_LEVEL = 0; // DEBUG_LEVEL define gives me more debug flexablility . .
	
	
	//*****************************************************************************
	// NB NB!! set if you want to take queries from keyboard or file
	///        by uncommenting the corresponding line below
	//*****************************************************************************
	
	// sets where the Query should come from
	//public static final String QUERY_SOURCE= "KEYBOARD"; 
	public static final String QUERY_SOURCE = "FILE"; 
	
	
	// sets the number of tests to run and average
	public static final int TEST_NUM = 100;
	
	// sets the number of warmup cycles
	public static final int DEFINE_WARMUP_CYCLES = 10;
	
	
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
		
		
		// stores sets of results and prints them at the end as csv's 
		// for easy import into excel etc.
		ArrayList<Results[]> testResults = new ArrayList<Results[]>(); 
		
		
		// Create an instance of the PreProcess class, and process the params file
		PreProcess preProcess = new PreProcess(params_file);
		
		// build the antGrid from the ant files 
		System.out.println("binning data, this could take a while... ");
		antGrid = preProcess.doBinning();
		
		
		// prints the grid out if debugging is enabled ...
		if(DEBUG && DEBUG_LEVEL == 1){   
			antGrid.print();
		}

		
		// code warmup xD
		//*********************************************************************
		
		// create an instance of the test class's
		Simple_Sequential test1 = new Simple_Sequential();
		Simple_Parallel test2 = new Simple_Parallel();
		// build the test 3 grid!
	    Smarter_Sequential test3 = new Smarter_Sequential(antGrid);
	    
		// prints the v3grid out if debugging is enabled ...
		if(DEBUG && DEBUG_LEVEL == 2){   
			test3.print();
		}
		
	
		// set to simmer and cover for 10 cycles!
		Timer t = new Timer();
		t.start_timer(); //ms timer  ?
		
		warmup(test1, test2, test3, antGrid);
		
		System.out.println();
		System.out.println("Warmup time:");
		t.finish_timer_toSyso();
		
		
		// Get the query/s
		//*********************************************************************
		QueryHandler QH = new QueryHandler(); 
		Query query = QH.getQuery();
		
		while(query != null){ // loop until queries done.. 
			
			// calc the query ranges to be compatible with our ant array
			query.calcRanges(antGrid);
			
			
			System.out.println();
			System.out.println("************************************************");
			System.out.println("************************************************");
			System.out.println("Testing Query: " + query.toString());
		
		
			// Version1: Simple and Sequential
			//*********************************************************************
			
			// run the test
			System.out.println();
			System.out.println("************************************************");
			System.out.println("Running test 1, Simple and Sequential...");
			System.out.println();
			
			Results t1Final = new Results(0,0,0);
			for (int c = 0; c < TEST_NUM; c++){
				Results t1Result = test1.runTest(antGrid, query);
				t1Final.setDpCount(t1Final.getDpCount() + t1Result.getDpCount());
				t1Final.setDpPercentage(t1Final.getDpPercentage() + t1Result.getDpPercentage());
				t1Final.setExTime(t1Final.getExTime() + t1Result.getExTime());
			}
			// be mean.
			t1Final.setDpCount(t1Final.getDpCount() / TEST_NUM);
			t1Final.setDpPercentage(t1Final.getDpPercentage() / TEST_NUM);
			t1Final.setExTime(t1Final.getExTime() / TEST_NUM);
			System.out.println("The results are: " + t1Final.toString());
			System.out.println(t1Final.exTime());
			
			// save for later
			Results[] testSet = new Results[3];
			testSet[0] = t1Final;
			
			// Version2: Simple and Parallel
			//*********************************************************************
			
			
			// run the test
			System.out.println();
			System.out.println("************************************************");
			System.out.println("Running test 2, Simple and Parallel...");
			System.out.println();
			
			Results t2Final = new Results(0,0,0);
			for (int c = 0; c < TEST_NUM; c++){
				Results t2Result = test2.runTest(antGrid, query);
				t2Final.setDpCount(t2Final.getDpCount() + t2Result.getDpCount());
				t2Final.setDpPercentage(t2Final.getDpPercentage() + t2Result.getDpPercentage());
				t2Final.setExTime(t2Final.getExTime() + t2Result.getExTime());
			}
			// be mean.
			t2Final.setDpCount(t2Final.getDpCount() / TEST_NUM);
			t2Final.setDpPercentage(t2Final.getDpPercentage() / TEST_NUM);
			t2Final.setExTime(t2Final.getExTime() / TEST_NUM);
			System.out.println("The results are: " + t2Final.toString());
			System.out.println(t2Final.exTime());
			
			double perc = ((double)t2Final.getExTime() / (double)t1Final.getExTime())*100;
			System.out.println("percentage " + perc);
			
			// save for later
		    testSet[1] = t2Final;
			

			// Version3: Smarter and Sequential
			//*********************************************************************
			
			// run the test
			System.out.println();
			System.out.println("************************************************");
			System.out.println("Running test 3, Smarter and Sequential...");
			System.out.println();
			
			
			Results t3Final = new Results(0,0,0);
			for (int c = 0; c < TEST_NUM; c++){
				Results t3Result = test3.runTest(antGrid, query);
				t3Final.setDpCount(t3Final.getDpCount() + t3Result.getDpCount());
				t3Final.setDpPercentage(t3Final.getDpPercentage() + t3Result.getDpPercentage());
				t3Final.setExTime(t3Final.getExTime() + t3Result.getExTime());
			}
			// be mean.
			t3Final.setDpCount(t3Final.getDpCount() / TEST_NUM);
			t3Final.setDpPercentage(t3Final.getDpPercentage() / TEST_NUM);
			t3Final.setExTime(t3Final.getExTime() / TEST_NUM);
			System.out.println("The results are: " + t3Final.toString());
			System.out.println(t3Final.exTime());
			
			double perc3 = ((double)t3Final.getExTime() / (double)t1Final.getExTime())*100;
			System.out.println("percentage " + perc3);
			
			// save for later
			testSet[2] = t3Final;
			testResults.add(testSet);
			

			// gets the next query
			query = QH.getQuery();
		} // end query loop
		
		
		System.out.println();
		System.out.println("***************************************************");
		System.out.println("test results.");
		
		// flip all the results so the order is correct
		ArrayList<Results[]> flipResults = new ArrayList<Results[]>();
		for(Results[] r : testResults){
			flipResults.add(r);
		}
		// clean up. 
		testResults.clear();
		
		// display the results
		for(Results[] r : flipResults){
			System.out.println(r[0].getExTime() + ", " + r[1].getExTime() + ", " + r[2].getExTime());
		}
		
		System.out.println();
		System.out.println("Done!! :D ");
		
		test3.close(); // saves mem
		
		
		
		
		
	}
	
	
	/**
	 * runs the code for each test through a few cycles to get it 'warmed up'
	 * cycles set by the global constant DEFINE_WARMUP_CYCLES
	 * @param t1
	 * @param t2
	 * @param t3
	 */
	public static void warmup(Simple_Sequential t1, Simple_Parallel t2, Smarter_Sequential t3, AntGrid antGrid){
		// create a 'fake' query
		Query query = new Query(-100, -100, 100, 100);
		query.calcRanges(antGrid);
		
		// run each test a couple times to let java optimise the code :D 
		for (int i = 0; i < DEFINE_WARMUP_CYCLES; i++){
			t1.runTest(antGrid, query);
			t2.runTest(antGrid, query);
			t3.runTest(antGrid, query);			
		}
	}
	
	
	
}
