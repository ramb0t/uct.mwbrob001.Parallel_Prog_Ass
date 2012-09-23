package uct.mwbrob001.ppa;

// Query type to make things cleaner.. 
public class Query {
	// need variables to hold the co-ords of the query boxxes
	private int xMin = 0; private int xMax = 0;
	private int yMin = 0; private int yMax = 0;
	
	// stores the correct query grid values
	private int qxMin = 0; private int qxMax = 0;
	private int qyMin = 0; private int qyMax = 0;
	
	/**
	 * Constructor
	 * pass the lower and upper bounds to create the object. 
	 * @param xMin
	 * @param xMax
	 * @param yMin
	 * @param yMax
	 */
	public Query(int xMin, int xMax, int yMin, int yMax){
		this.setxMin(xMin);
		this.setxMax(xMax);
		this.setyMin(yMin);
		this.setyMax(yMax);
		
	}
	
	// uses the bin sizes etc to work out the offset and bounded queries 
	public void calcRanges(AntGrid antGrid){

		// get the bin sizes
		int k 	 = antGrid.getK();
		int m	 = antGrid.getM();
		
    	
		// find the total number of datapoints in the query grid
	    // first going to have to find the min & max bin locations
		
		// start by getting the corresponding bins of the data.. 
		// take datapoint / (xRange/k) gives the bin it's in? 
    	
    	qxMin = (int) Math.ceil((xMin/k));
    	qxMax = (int) Math.floor((xMax/k));

    	qyMin = (int) Math.ceil((yMin/m));
    	qyMax = (int) Math.floor((yMax/m));  // only selects the bins within the query rectangle
		
		
		qxMin = qxMin + antGrid.getxOffset();
		qxMax = qxMax + antGrid.getxOffset();
		
		qyMin = qyMin + antGrid.getyOffset();
		qyMax = qyMax + antGrid.getyOffset(); // add the offsets to be compatible with 0 centered array
	
		// make sure query isnt out of bounds.. (ignore out of bound areas?)
		// X
		if (qxMin < 0){
			qxMin = 0;
		}
		if (qxMin > antGrid.getXLength()-1){
			qxMin = antGrid.getXLength()-1;
		}
		
		if (qxMax < 0){
			qxMax = 0;
		}
		if (qxMax > antGrid.getXLength()-1){
			qxMax = antGrid.getXLength()-1;
		}

		// Y
		if (qyMin < 0){
			qyMin = 0;
		}
		if (qyMin > antGrid.getYLength()-1){
			qyMin = antGrid.getYLength()-1;
		}
		
		if (qyMax < 0){
			qyMax = 0;
		}
		if (qyMax > antGrid.getYLength()-1){
			qyMax = antGrid.getYLength()-1;
		}
		
		
		
		
	}
	
	
	public String toString(){
		return xMin + " " + yMin + " " + xMax + " " + yMax;
	}
	
	
	// getters / setters 

	public int getxMin() {
		return xMin;
	}

	public void setxMin(int xMin) {
		this.xMin = xMin;
	}

	public int getxMax() {
		return xMax;
	}

	public void setxMax(int xMax) {
		this.xMax = xMax;
	}

	public int getyMin() {
		return yMin;
	}

	public void setyMin(int yMin) {
		this.yMin = yMin;
	}

	public int getyMax() {
		return yMax;
	}

	public void setyMax(int yMax) {
		this.yMax = yMax;
	}
	


	
	public int getqxMin() {
		return qxMin;
	}

	public void setqxMin(int qxMin) {
		this.qxMin = qxMin;
	}

	public int getqxMax() {
		return qxMax;
	}

	public void setqxMax(int qxMax) {
		this.qxMax = qxMax;
	}

	public int getqyMin() {
		return qyMin;
	}

	public void setqyMin(int qyMin) {
		this.qyMin = qyMin;
	}

	public int getqyMax() {
		return qyMax;
	}

	public void setqyMax(int qyMax) {
		this.qyMax = qyMax;
	}
	

}
