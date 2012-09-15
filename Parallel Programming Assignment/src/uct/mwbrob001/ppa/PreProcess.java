package uct.mwbrob001.ppa;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import static uct.mwbrob001.ppa.Parallel_Prog_main.DEBUG; 				// get the DEBUG constant from main



/**
 * What needs to be done here? 
 * 
 * @author Rambo
 *
 */
public class PreProcess {
	
	//Global class variables
	private int n = 0; 					  // number of ant datafiles
	private String[] ant_datafiles_list;  // String array to store ant datafile names
	private int k,m = 0; 			      // x,y bin sizes
	
	//Timer class for timing stuff yo.. 
	Timer execTime = new Timer();
	
	
	/**
	 * constructor to deal with the params file
	 * 
	 * read the parameters file, get:
	 *  n, ant data files to be read
     *  a list of the n files
     *  two ints, k & m comprising the data bin dims
     *  	
	 * @param params_file
	 * 
	 */
	public PreProcess(String params_file){
		
		
		
		//Read the file into global class parameters
		readParams(params_file);
		
		readAntFiles();
		
				
		
	}
	
	
	/**
	 * Reads the parameter file and sets global class variables
	 * @param params_file
	 */
	private void readParams(String params_file){
		//Read the file into global class parameters
		try{
			Scanner file_input = new Scanner (new FileInputStream(params_file));
			
			/* TODO
			 * textfile input error handling
			 */
			n = Integer.parseInt(file_input.nextLine()); 					// Get number of ant datafile;s
			ant_datafiles_list = new String[n];         // Init datafile array
			
			
			for(int i = 0 ; i<n ; i++){ 
				ant_datafiles_list[i] = file_input.nextLine();  // Grab the datafile names
			}
			
			k = Integer.parseInt(file_input.next()); 
			m = Integer.parseInt(file_input.next());   // Get the bin sizes
			
			file_input.close(); 	   // Finally close the stream.. 
			
		} catch(Exception ex){
			System.out.println("File IO Error!! Cannot open, please check file names.");
			System.out.println(ex.toString());
            System.exit(1);
		}
		
	}

	/**
	 * Reads the ant location files, detects corners, bins data etc.
	 */
	private void readAntFiles(){
		// whats the plan here?
		// check corners then bin data? 
		// else bin and expand as bigger corers are found?
		// what data structure to use? arraylist maybe? 
		
		
		BufferedReader br = null;
	    try {
	    	
	    	float minX = 0, maxX = 0;
	    	float minY = 0, maxY = 0;
	    	String line;
	    	String[] lineArr;
	    	
	    	if(DEBUG){	execTime.start_timer();	} // starts timing the following code.
	    	
	    	
	    	br = new BufferedReader(new FileReader("data/" + ant_datafiles_list[1]));
	    	
	    	line = br.readLine();
	    	
	    	while(line != null){
		    	lineArr = line.split(" ");
		    	
		    	float x = Float.parseFloat((lineArr[1])); 
		    	float y = Float.parseFloat((lineArr[2]));
		    	
		    	if (x > maxX){
		    		maxX = x;
		    	} else if(x < minX){
		    		minX = x;
		    	}
		    	
		    	if (y > maxY){
		    		maxY = y;
		    	} else if(y < minY){
		    		minY = y;
		    	}
		    	
		    	line = br.readLine();
	    	}
	    	
	        br.close();  // close the current ant datafile
	        
	        
	        
	        if(DEBUG){	execTime.finish_timer();   	} // displays the time the code took to execute
	        
	       
	        
	        System.out.println(minX + ", " + maxX + ", " + minY + ", " + maxY );
	    } catch (IOException e) {
	    	System.out.println("File IO Error!! Cannot open, please check file names.");
			System.out.println(e.toString());
            System.exit(1);
	    }
	    

		
	}
	
}
