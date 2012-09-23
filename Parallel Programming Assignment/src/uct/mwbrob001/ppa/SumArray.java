// TODO comments.. 

package uct.mwbrob001.ppa;

import java.util.concurrent.RecursiveTask;

public class SumArray extends RecursiveTask<Integer> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	static int SEQUENTIAL_CUTOFF = 33; // point at which the algorithm switches to sequential 
	
	// stores the array and the bounds for the current thread
    int xlo, ylo; 
    int xhi, yhi ;
    int[][] arr;
    
    /**
     * new thread constructor
     * @param a
     * @param xl
     * @param xh
     * @param yl
     * @param yh
     */
    public SumArray(int[][] a, int xl, int xh, int yl, int yh){
    	xlo = xl;
    	xhi = xh;
    	ylo = yl; 
    	yhi = yh;
    	arr = a; 
    }
    
    /**
     * first going to split on x, then on y? 
     */
    protected Integer compute(){ // inherited compute method for the thread
    	if( xhi - xlo  <= SEQUENTIAL_CUTOFF){
    		if (yhi - ylo <= SEQUENTIAL_CUTOFF){
    			
    			// sum the points.. 
    			int ans = 0;
        		for (int x = xlo; x <= xhi; x++){
        			for(int y = ylo; y <= yhi; y++){
        				ans += arr[x][y];
        			}
        		}
        		return ans;
        		
        		
    		} else { // split on y? 
    			// setup the threads
        		SumArray left = new SumArray(arr, xlo, xhi, ylo, (yhi+ylo)/2); 
        		SumArray right = new SumArray(arr, xlo, xhi, ((yhi+ylo)/2)+1, yhi);
        		
        		// fork left
        		left.fork();
        		
        		// compute right in current thread
        		int rightAns = right.compute();
        		// join ans
        		int leftAns = left.join();
        		return leftAns + rightAns;

    			
    		}
    		
    	} else { // split on x?
    		// setup the threads
    		SumArray left = new SumArray(arr, xlo, (xhi+xlo)/2, ylo, yhi); 
    		SumArray right = new SumArray(arr, ((xhi+xlo)/2)+1, xhi, ylo, yhi);
    		
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
