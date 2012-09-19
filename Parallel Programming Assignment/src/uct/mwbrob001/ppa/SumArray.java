// TODO comments.. 

package uct.mwbrob001.ppa;

import java.util.concurrent.RecursiveTask;

public class SumArray extends RecursiveTask<Integer> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	static int SEQUENTIAL_CUTOFF = 500; // point at which the algorithm switches to sequential 
	
	// stores the array and the bounds for the current thread
    int lo; 
    int hi;
    int[] arr;
    
    SumArray(int[] a, int l, int h){ lo = l; hi = h; arr = a; } // constructor for new threads?
    
    protected Integer compute(){ // inherited compute method for the thread
    	if(hi - lo <= SEQUENTIAL_CUTOFF){
    		int ans = 0;
    		for (int i = lo; i < hi; ++i){
    			ans += arr[i];
    		}
    		return ans;
    	} else {
    		// setup the threads
    		SumArray left = new SumArray(arr, lo, (hi+lo)/2); 
    		SumArray right = new SumArray(arr, (hi+lo)/2, hi);
    		
    		// fork left
    		left.fork();
    		
    		// compute right in current thread
    		int rightAns = right.compute();
    		// join ans
    		int leftAns = left.join();
    		return leftAns + rightAns;
    	}
    	
    }
	
}
