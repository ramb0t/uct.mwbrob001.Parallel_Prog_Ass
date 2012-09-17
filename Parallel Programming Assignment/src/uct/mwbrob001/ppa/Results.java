package uct.mwbrob001.ppa;

/**
 * just a quick little object to make returning results easier.. 
 * @author RobThePyro
 *
 */
public class Results {
	// Two result variables
	private int dpCount;
	private double dpPercentage;
	
	/**
	 * constructor taking the counts
	 * @param dpCount
	 * @param dpPercentage
	 */
	public Results(int dpCount, double dpPercentage){
		this.setDpCount(dpCount);
		this.setDpPercentage(dpPercentage);
	}
	
	// toString override for printing
	public String toString(){
		return dpCount + " " + dpPercentage;
	}

	
	// Getters and Setters.. 
	public int getDpCount() {
		return dpCount;
	}
	public void setDpCount(int dpCount) {
		this.dpCount = dpCount;
	}

	public double getDpPercentage() {
		return dpPercentage;
	}
	public void setDpPercentage(double dpPercentage) {
		this.dpPercentage = dpPercentage;
	}
	

}
