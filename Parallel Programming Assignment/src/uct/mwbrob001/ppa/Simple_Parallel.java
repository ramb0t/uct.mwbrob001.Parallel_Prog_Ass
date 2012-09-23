/* File: Simple_Parallel.java
 * CSC2002S Java Parallel assignment
 * Author: MWBROB001
 * Date : September 2012
 * 
 * Description:
 *  Version 2 class, using the forkjoin framework to reduce the span of the query to O(log n) 
 * 
 */

package uct.mwbrob001.ppa;

import java.util.concurrent.ForkJoinPool;

public class Simple_Parallel {

	// forkjoin stuff
	static final ForkJoinPool fjPool = new ForkJoinPool();
	
	
	public Simple_Parallel(){
		
	}
	
	public Results runTest(AntGrid antGrid, Query query){
		// start timing
		Timer t2Timer = new Timer();
		t2Timer.start_ns_timer();
		
		
		// fetch the ant grid
		int[][] aGrid = antGrid.getAntArr();

		
		// get the query co-ords
		int xMin = query.getxMin();
		int xMax = query.getxMax();
		
		int yMin = query.getyMin();
		int yMax = query.getyMax();

		// get the bin sizes
		int k 	 = antGrid.getK();
		int m	 = antGrid.getM();
		
		// find the total number of datapoints in the query grid
	    // first going to have to find the min & max bin locations
		
		// start by getting the corresponding bins of the data.. 
		// take datapoint / (xRange/k) gives the bin it's in? 
    	
    	xMin = (int) Math.ceil((xMin/k));
    	xMax = (int) Math.floor((xMax/k));

    	yMin = (int) Math.ceil((yMin/m));
    	yMax = (int) Math.floor((yMax/m));  // only selects the bins within the query rectangle
		
		
		xMin = xMin + antGrid.getxOffset();
		xMax = xMax + antGrid.getxOffset();
		
		yMin = yMin + antGrid.getyOffset();
		yMax = yMax + antGrid.getyOffset(); // add the offsets to be compatible with 0 centered array
				
		int dpCount = fjPool.invoke(new SumArray(aGrid, xMin, xMax, yMin, yMax));
		
		// calculate the percentage
		double dpPercentage = ((double)(dpCount * 100))/(double)(antGrid.getCount());
		
		// return the results and the compute time.
		return new Results(dpCount, dpPercentage, t2Timer.finish_ns_timer());
	}
	
}
