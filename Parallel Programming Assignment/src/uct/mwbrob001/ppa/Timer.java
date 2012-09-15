package uct.mwbrob001.ppa;
/*  Timer.java
	 Simple class to abstract a simple timing algorithm, finishes by printing a string with the elapsed time..  
	 MWBROB001
	 16/04/2011
*/


public class Timer {
	long startTime;
	long endTime;
	
	public Timer(){
	}
	
	public void start_timer(){ // starts the timer 
		startTime = System.currentTimeMillis(); 
	}
	
	public void finish_timer(){ //completes the timer
		endTime = System.currentTimeMillis();
		System.out.println("Execution time: " + (endTime - startTime) + "ms");
	}
}
