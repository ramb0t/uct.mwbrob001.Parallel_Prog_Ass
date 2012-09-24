/* File: PreProcess.java
 * CSC2002S Java Parallel assignment
 * Author: MWBROB001
 * Date : September 2012
 * 
 * Description:
 *  PreProcess class to take parameters file, and then read the ant data files
 *  followed by finding the corners & bining the data
 * 
 */

package uct.mwbrob001.ppa;

import static uct.mwbrob001.ppa.Parallel_Prog_main.DEBUG;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
// get the DEBUG constant from main




/**
 * What needs to be done here? 
 * 
 * @author Rambo
 *
 */
public class PreProcess {
	
	//Global class variables
	//**********************
	private int n = 0; 					  // number of ant datafiles
	private String[] ant_datafiles_list;  // String array to store ant datafile names
	private int k,m = 0; 			      // x,y bin sizes
	
	//Arraylist for reading the file into and then binning
	ArrayList<String> dataPoints = new ArrayList<String>();
	
	// variables for storing the corners
	float minX = 0, maxX = 0;
	float minY = 0, maxY = 0;
	
	// vars for dealing with the offset in negative matricies
	int xOffset = 0;
	int yOffset = 0;
	
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
		System.out.println("Reading parameters file.. ");
		readParams(params_file);
		
		// get data from the ant files
		System.out.println("Searching for corners, this could take a while..");
		findCorners();
		
				
		
	}
	
	
	/**
	 * Reads the parameter file and sets global class variables
	 * @param params_file
	 */
	private void readParams(String params_file){
		//Read the file into global class parameters
		try{
			Scanner file_input = new Scanner (new FileInputStream(params_file));
			
			try{
				n = Integer.parseInt(file_input.nextLine()); 					// Get number of ant datafile;s
				ant_datafiles_list = new String[n];         // Init datafile array
				
				
				for(int i = 0 ; i<n ; i++){ 
					ant_datafiles_list[i] = file_input.nextLine();  // Grab the datafile names
				}
				
				k = Integer.parseInt(file_input.next()); 
				m = Integer.parseInt(file_input.next());   // Get the bin sizes
			} catch(Exception ex){
				System.out.println("Something went wrong when trying to parse the parameters file!");
				System.out.println(ex.toString());
				System.exit(1);
			}
			
			file_input.close(); 	   // Finally close the stream.. 
			
		} catch(Exception ex){
			System.out.println("File IO Error!! Cannot open, please check file names.");
			System.out.println(ex.toString());
            System.exit(1);
		}
		
	}
	
	/**
	 * reads the files and finds the min and max values for corners
	 * stores these in the global class variables
	 */
	private void findCorners(){
		/**
		 * Step 1:
		 * read all the files finding the corners, and read the file into an arraylist for faster binning 
		 */
		BufferedReader br = null; // init the reader var
		// try read the input files catching read errors
	    try {
	    	    	
	    	// vars for dealing with the input strings
	    	String line;
	    	String[] lineArr;
	    	
	    	
	    	if(DEBUG){	execTime.start_timer();	} // starts timing the following code.
	    	
	    	
	    	for (String path : ant_datafiles_list){ // for each datafile in the datafile list
	    		
		    	br = new BufferedReader(new FileReader("data/" + path)); // open a buffered reader of the file
		    	
		    	line = br.readLine(); // get a line
		    	
		    	while(line != null){  // loop until end of file
		    		
		    		// add the line to the datapoint arraylist
		    		dataPoints.add(line);
		    		
		    		// Deal with multiple space delimiters:
			    	String multiple_space_delim = "[ ]+";
		    		
			    	// Split the ant input line up
			    	lineArr = line.split(multiple_space_delim); 
			    	
			    	// try parse the input
			    	try{			    		
				    	// not used
			    		//int counter = Integer.parseInt((lineArr[0]));
				    	float x = Float.parseFloat((lineArr[1])); 
				    	float y = Float.parseFloat((lineArr[2]));
				    	
				    	// corner finding logic. 
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
			    	
			    	} catch(Exception ex){ // catching all errors
			    		System.out.println("File parsing Error!! " +
			    				"Something went wrong when trying to parse the data," +
			    				" please check your file format and fix the faulty line...");
			    		System.out.println("In file: " + path);
						System.out.println(ex.toString());
			            System.exit(1);
			    	}
			    	
			    	
			    	line = br.readLine();
		    	}
		    	
		        br.close();  // close the current ant datafile
		        
	    	}
	    	
	    	if(DEBUG){ // displays the time the code took to execute
	    		System.out.println("Corners: " + minX + ", " + maxX + ", " + minY + ", " + maxY );
	    		execTime.finish_timer_toSyso();
	    	}
	    	
	    } catch (IOException e) {
	    	System.out.println("File IO Error!! Cannot open, please check file names.");
			System.out.println(e.toString());
            System.exit(1);
	    }
	    	
	}

	/**
	 * Takes the 2d int array, reads datapoints from the datapoints arraylist and bin's data accordingly
	 * @param antGrid
	 * @return newAntGrid
	 */
	public AntGrid doBinning(){

		 
    	
    	/**
    	 * Step 2: 
    	 * Setup the array using corners and bin sizes
    	 * we need to work out the range of x and y vals
    	 * and the div by the bin sizes
    	 * to find the array size
    	 */
		
    	// init the temp ant array to work with now; and the counter to hold the datapoints
    	int[][] tempAntGrid = null;
    	int count = 0;
		
		try{
		
		
			if(DEBUG){	execTime.start_timer();	} // starts timing the following code.
			
			
	    	
	    	// get x,y ranges
	    	float xRange = maxX - minX; 
	    	float yRange = maxY - minY;
	    	
	    	// round the ranges up to the nearest whole int
	    	xRange = (float) Math.ceil(xRange);
	    	yRange = (float) Math.ceil(yRange);
	    	
	    	// div by the bin sizes to get final array block sizes
	    	int xBloc = (int) Math.ceil((xRange/k));
	    	int yBloc = (int) Math.ceil((yRange/m));
	    
	    	tempAntGrid = new int[xBloc+2][yBloc+2];
	    	
	    	// deal with offsets in the case of negative grid values
	    	if (minX < 0){ // x Offset
	    		xOffset = Math.abs((int) Math.floor(minX));
	    	}
	    	if (minY < 0){ // y Offset
	    		yOffset = Math.abs((int) Math.floor(minY));
	    	}
	    	
	    	if(DEBUG){
	    		System.out.println("Ranges: " + xRange + " " + yRange);
	    		System.out.println("k, m :" + k + " " + m);
	    		System.out.println("array size: " + xBloc + " " + yBloc);
	    	}
	    	
	    	
	    	
	    	/**
	    	 * Step 3: bin the data
	    	 */
	    	
	    		
    		// vars for dealing with the input strings
	    	String[] lineArr;

	    	
	    	for (String line : dataPoints){ // for each element in the arraylist
		    		
	    		// Deal with multiple space delimiters:
		    	String multiple_space_delim = "[ ]+";
	    		
		    	// Split the ant input line up
		    	lineArr = line.split(multiple_space_delim); 
		    	if (lineArr.length > 3){
		    	// try parse the input
		    	try{
		    		// also not used..
			    	//int counter = Integer.parseInt((lineArr[0]));
			    	float x = Float.parseFloat((lineArr[1])); 
			    	float y = Float.parseFloat((lineArr[2]));
			    	
			    	// binning logic
			    	// take datapoint / (xRange/k) gives the bin it's in? 
			    	
			    	
			    	double xbin = (x/k);
			    	double ybin = (y/m);
			    	
			    	
			    	tempAntGrid[xOffset + (int) Math.ceil(xbin)][yOffset + (int) Math.ceil(ybin)]++;
			    	count++;
			    	
		    	
		    	} catch(Exception ex){ // catching all errors
		    		System.out.println("File parsing Error!! " +
		    				"Something went wrong when trying to parse the data," +
		    				" please check your file format");
					System.out.println(ex.toString());
					for(String s : lineArr)
					System.out.println(s);
		            System.exit(1);
		    	}
		    	}
		        
	    	} // end for each
	    	
	    	// clean up, destroy the arraylist
	    	dataPoints.clear();
	    	
	 	
		    	

	        
	        
	        if(DEBUG){	
	        	execTime.finish_timer_toSyso();
	        	System.out.println("To bin the data.");
	        	} // displays the time the code took to execute
	        
		       
		        
		    
	        
		} catch(Exception ex){ // catches anything that may have gone wrong while building the array.. 
			System.out.println("Something went wrong while trying to bin the data... ");
			System.out.println(ex.toString());
            System.exit(1);
		}
		
		
    	
		// send the filled antGrid back :)     
        return new AntGrid(tempAntGrid, count, k, m, xOffset, yOffset);
       
		
        
        
		


		
	}
	
}
