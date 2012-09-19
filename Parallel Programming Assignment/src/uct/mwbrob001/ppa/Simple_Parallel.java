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
		
		// since the Parallel SumArray class only takes a 1d array,
		// to keep things simple I will build a 1d array from the query first
		// and then time and run the parallel processing on that.. 
		int[] oneD_Arr = new int[((xMax-xMin)+1)*((yMax-yMin)+1)];
		
		int i = 0;
		try{
			for (int x = xMin; x <= xMax; x++){
				for (int y = yMin; y <= yMax; y++){
					oneD_Arr[i] = aGrid[x][y];
					i++;
					if (i > 22500){
						System.err.println("eish");
					}
				}
			}
		} catch(ArrayIndexOutOfBoundsException ex){
			System.out.println("Your query is out of bounds!! please try again.. ");
			// return the results as 0. 
			return new Results(0,0);
		}
		
		Timer t2Timer = new Timer();
		t2Timer.start_ns_timer();
		
		int dpCount = fjPool.invoke(new SumArray(oneD_Arr, 0, oneD_Arr.length));
		
		int numdp = antGrid.getCount();
		double dpPercentage = ((dpCount*100)/numdp);
	
		t2Timer.finish_ns_timer();
		
		return new Results(dpCount, dpPercentage);
	}
	
}
