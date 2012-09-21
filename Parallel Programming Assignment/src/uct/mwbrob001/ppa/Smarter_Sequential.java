// TODO comments
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
		Timer tTimer = new Timer();
		tTimer.start_ns_timer();
		
		int[][] aGrid = antGrid.getAntArr();
		
		// init the new grid
		v3Grid = new int[aGrid.length][aGrid[0].length];
		
		// build the new grid
		for (int x = 0; x < aGrid.length; x++){
			for (int y = aGrid[x].length-1 ; y >= 0; y--){ // start top left
				if (x > 0 & y < aGrid[x].length-1){ // niether first row or column
					v3Grid[x][y] = aGrid[x][y] + v3Grid[x-1][y] + v3Grid[x][y+1] - v3Grid[x-1][y+1];
					
				}else if (x > 0 & y == aGrid[x].length-1){ // first row only
					v3Grid[x][y] = aGrid[x][y] + v3Grid[x-1][y];
					
				}else if (x==0 & y < aGrid[x].length-1 ){ // first column only
					v3Grid[x][y] = aGrid[x][y] + v3Grid[x][y+1];
					
				}else if(x==0 & y == aGrid[x].length-1 ){ // first column first row 
					v3Grid[x][y] = aGrid[x][y];
				}
				
			}
		}
		
		System.out.println();
		System.out.println("v3Grid build time.. ");
		tTimer.finish_ns_timer();
		
		
		
	}

	
	public Results runTest(AntGrid antGrid, Query query){
		Timer tTimer = new Timer();
		tTimer.start_ns_timer();
		
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
		
		// finish the timer
		tTimer.finish_ns_timer();
		
		// return the result
		return new Results(dpCount, dpPercentage);
		
		
	}
	
	public void close(){
		v3Grid = null;
	}
	
}
