/* File: Simple_Sequential.java
 * CSC2002S Java Parallel assignment
 * Author: MWBROB001
 * Date : September 2012
 * 
 * Description:
 *  Version 1 class, quick n dirty sequential query processing. 
 * 
 */


package uct.mwbrob001.ppa;

// what do I want to pass here? 
// the ant grid - easy
// and the query, what form? 
public class Simple_Sequential {
	
	/**
	 * Constructor.. 
	 */
	public Simple_Sequential(){
		
	}
	
	/**
	 * Runs the test on the passed query object and antGrid
	 * @param query
	 */
	public Results runTest(AntGrid antGrid, Query query){
		// first up, going to need timer to see how long this takes
		Timer tTimer = new Timer();
		tTimer.start_ns_timer();
		
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
		
		// now count the datapoints
		int dpCount = 0;
		
		try{
			for (int x = xMin; x <= xMax; x++){
				for (int y = yMin; y <= yMax; y++){
					dpCount = dpCount + aGrid[x][y];  // counts all the bins inside the query rectangle
				}
			}
		} catch(ArrayIndexOutOfBoundsException ex){
			System.out.println("Your query is out of bounds!! please try again.. ");
			// return the results as 0. 
			return new Results(0,0);
		}
		
		// finish the timer
		tTimer.finish_ns_timer();
		
		int numdp = antGrid.getCount();
		double dpPercentage = ((dpCount*100)/numdp);
				
		return new Results(dpCount, dpPercentage); 
		
		
	}
	

}
