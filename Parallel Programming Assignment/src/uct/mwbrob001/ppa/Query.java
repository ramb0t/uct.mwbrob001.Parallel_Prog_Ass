package uct.mwbrob001.ppa;

// Query type to make things cleaner.. 
public class Query {
	// need variables to hold the co-ords of the query boxxes
	private int xMin = 0; private int xMax = 0;
	private int yMin = 0; private int yMax = 0;
	
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
	
	public String toString(){
		return xMin + " " + yMin + " " + xMax + " " + yMax;
	}

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
	

}
