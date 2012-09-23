package uct.mwbrob001.ppa;

/**
 * just a quick little object to make returning results easier.. 
 * @author RobThePyro
 *
 */
public class Results {
	// Two result variables
	private long dpCount;
	private double dpPercentage;
	private long exTime;
	
	/**
	 * constructor taking the counts
	 * @param dpCount
	 * @param dpPercentage
	 */
	public Results(long dpCount, double dpPercentage, long exTime){
		this.setDpCount(dpCount);
		this.setDpPercentage(dpPercentage);
		this.setExTime(exTime);
		
	}
	
	// toString override for printing
	public String toString(){
		return dpCount + " " + dpPercentage + "%";
	}
	public String exTime(){
		return exTime + "ns Execution time!";
	}

	
	// Getters and Setters.. 
	public long getDpCount() {
		return dpCount;
	}
	public void setDpCount(long dpCount) {
		this.dpCount = dpCount;
	}

	public double getDpPercentage() {
		return dpPercentage;
	}
	public void setDpPercentage(double dpPercentage) {
		this.dpPercentage = dpPercentage;
	}

	public long getExTime() {
		return exTime;
	}

	public void setExTime(long exTime) {
		this.exTime = exTime;
	}
	

}
