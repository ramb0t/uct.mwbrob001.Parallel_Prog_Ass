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
		this.xMin = xMin;
		this.xMax = xMax;
		this.yMin = yMin;
		this.yMax = yMax;
	}
	

}
