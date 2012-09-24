/* File: Timer.java
 * CSC2002S Java Parallel assignment
 * Author: MWBROB001
 * Date : September 2012
 * 
 * Description:
 *  Quick little class to abstract some timing functions.
 * 
 */
package uct.mwbrob001.ppa;



public class Timer {
	
	long nsstartTime;
	long nsendTime;
	
	long startTime;
	long endTime;
	
	public Timer(){
	}
	
	public void start_ns_timer(){ // starts nano second timer 
		nsstartTime = System.nanoTime();
	}
	
	public long finish_ns_timer(){ // completes the nano second timer
		nsendTime = System.nanoTime();
		return (nsendTime - nsstartTime);
	}
	
	public void finish_ns_timer_toSyso(){ // completes the nano second timer
		nsendTime = System.nanoTime();
		System.out.println("Execution time: " + (nsendTime - nsstartTime) + "ns");
	}
	
	public void start_timer(){ // starts the timer 
		startTime = System.currentTimeMillis(); 
	}
	
	public void finish_timer_toSyso(){ //completes the timer
		endTime = System.currentTimeMillis();
		System.out.println("Execution time: " + (endTime - startTime) + "ms");
	}
}
