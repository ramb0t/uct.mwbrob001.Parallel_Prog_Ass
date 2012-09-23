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
		int xMin = query.getqxMin();
		int xMax = query.getqxMax();
		
		int yMin = query.getqyMin();
		int yMax = query.getqyMax();

		
		int dpCount = fjPool.invoke(new SumArray(aGrid, xMin, xMax, yMin, yMax));
		
		// calculate the percentage
		double dpPercentage = ((double)(dpCount * 100))/(double)(antGrid.getCount());
		
		// return the results and the compute time.
		return new Results(dpCount, dpPercentage, t2Timer.finish_ns_timer());
	}
	
}
