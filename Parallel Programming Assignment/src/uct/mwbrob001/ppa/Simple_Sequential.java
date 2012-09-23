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
		int xMin = query.getqxMin();
		int xMax = query.getqxMax();
		
		int yMin = query.getqyMin();
		int yMax = query.getqyMax();

		
		// now count the datapoints
		int dpCount = 0;
		
		try{
			for (int x = xMin; x <= xMax; x++){
				for (int y = yMin; y <= yMax; y++){
					dpCount = dpCount + aGrid[x][y];  // counts all the bins inside the query rectangle
				}
			}
		} catch(ArrayIndexOutOfBoundsException ex){
			System.out.println("Something went wrong!!, might be array bounds.. please try again.. ");
			// return the results as 0. 
			return new Results(0,0,0);
		}
		
		// work out the percentage
		double dpPercentage = ((double)(dpCount * 100))/(double)(antGrid.getCount());
				
		// return the results and finish the timer
		return new Results(dpCount, dpPercentage, tTimer.finish_ns_timer()); 
		
		
	}
	

}
