/* File: QueryHandler.java
 * CSC2002S Java Parallel assignment
 * Author: MWBROB001
 * Date : September 2012
 * 
 * Description:
 *  Responsible for getting queries from the keyboard or file
 *  parsing them correctly and returning query objects.
 * 
 */
package uct.mwbrob001.ppa;

import static uct.mwbrob001.ppa.Parallel_Prog_main.QUERY_FILE;
import static uct.mwbrob001.ppa.Parallel_Prog_main.QUERY_SOURCE;

import java.io.FileInputStream;
import java.util.Scanner;
// get the query file path

public class QueryHandler {
	// Variables for storing an array of querys for multiple tests
	private Query[] qArr;
	private int qCounter = 0;
	private int qArrSize = 0;
	
	
	/**
	 * Constructor
	 * If the source is set to file then
	 *  gets all the querys from the file,
	 *  if it exists, and stores them in an array
	 * 
	 */
	public QueryHandler(){
		if (QUERY_SOURCE.equals("FILE")){
			getQueryFile();
		}
		
	}
	
	/**
	 * If the source is set to keyboard, gets the next keyboard input
	 * Otherwise returns the next query from the file thats in the query array
	 */
	public Query getQuery(){
		if (QUERY_SOURCE.equals("KEYBOARD")){
			return getQueryKeyboard();
			
		} else if (QUERY_SOURCE.equals("FILE")){
			
			if (qCounter < qArrSize){ // returns the next query
				Query query = qArr[qCounter];
				qCounter++;
				return query;
			} else { // end of querys
				return null;
			}
				
			
		} else {
			System.out.println("Something is wrong with your QUERY_SOURCE !");
			System.exit(1);
		}
		return null;
			
	}
	
	/**
	 *  gets a query string from the keyboard
	 *  returns null if exiting
	 * @return
	 */	
	private Query getQueryKeyboard(){
		
		//variables needed in this function
		@SuppressWarnings("resource")
		Scanner input = new Scanner(System.in);
		String line = "";
		String[] lineArr;
		
		int xMin=0, xMax=0;
		int yMin=0, yMax=0;
		
		
		
		// Get the first line
		System.out.println();
		System.out.println("Please enter the query: ");
		while(!input.hasNextLine()){ // prevent input errors?
		}
		line = input.nextLine();
		
		

    	// do some logic on the values. 
    	// must be exactly 4 vals, anything else and program quits. 
    	// must be logical, xMax < xMin should trigger a reprompt
    	
    	boolean flag = false; // checks if processing is correct..
    	
    	while(!flag){
   
    		// do some processing on the line
			try{
				
				// Deal with multiple space delimiters:
		    	String multiple_space_delim = "[ ]+";
	    		
		    	// Split the input line up
		    	lineArr = line.split(multiple_space_delim);
		    	
	
			    	// wrong number
			    	if(lineArr.length != 4){
			    		System.out.println("Not 4 ints! exiting.. ");
			    		return null;
			    	}
			    	
			    	// should now have 4 ints, time to convert..
			    	
			    	try{
			    		xMin = Integer.parseInt(lineArr[0]);
			    		yMin = Integer.parseInt(lineArr[1]);		
			    		xMax = Integer.parseInt(lineArr[2]);
			    		yMax = Integer.parseInt(lineArr[3]);
			    		
			    		
			    	} catch(Exception ex){
			    		// something went wrong during conversion.. 
			    		System.out.println("Cant read your values! please make sure they are 4 integers.. ");
			    		// get another line from the keyboard
			    		line = input.nextLine();
			    	}
			    	
			    	// final logic
			    	if (xMax < xMin | yMax < yMin){
			    		System.out.println("values out of range, please make sure xMax > xMin etc. ");
			    		// get another line from the keyboard
			    		line = input.nextLine();
			    	} else{
			    		// conversion should be done, set flag
			    		flag = true;
			    	}
		    	
			} catch(Exception ex){ // outer try catch.. 
				System.out.println("Something horrible has gone wrong! I have no idea what.. maybe you can figure it out ;) : ");
				System.out.println(ex.toString());
				System.exit(1);
			}
    	} // end while
		
		// now create the query object
		Query query = new Query(xMin, xMax, yMin, yMax);
		
		// and send him on his way :) 
		return query; 
	}
	
	private void getQueryFile(){
			
			//variables needed in this function
			Scanner file_input;
			String line = "";
			String[] lineArr;
			
			int xMin=0, xMax=0;
			int yMin=0, yMax=0;
			
			
			try{
				
				// open file
				file_input =  new Scanner(new FileInputStream(QUERY_FILE));
				
				// first get the number of lines
				while(file_input.hasNextLine()){
					line = file_input.nextLine();
					qArrSize++;
				}
				file_input.close();
				
				// init the Query Array
				qArr = new Query[qArrSize];
				
				// now reopen file
				file_input =  new Scanner(new FileInputStream(QUERY_FILE));
				
				
				while(file_input.hasNextLine()){ //loop to end of file
					// Get the next line
					line = file_input.nextLine();
			    	// do some logic on the values. 
			    	// do some processing on the line
						try{
							
							// Deal with multiple space delimiters:
					    	String multiple_space_delim = "[ ]+";
				    		
					    	// Split the input line up
					    	lineArr = line.split(multiple_space_delim);
					    						    	
						    	// should now have 4 ints, time to convert..
						    	
						    	try{
						    		xMin = Integer.parseInt(lineArr[0]);
						    		yMin = Integer.parseInt(lineArr[1]);		
						    		xMax = Integer.parseInt(lineArr[2]);
						    		yMax = Integer.parseInt(lineArr[3]);
						    		
						    		
						    	} catch(Exception ex){
						    		// something went wrong during conversion.. 
						    		System.out.println("Cant parse query file correctly, please make sure they are 4 integers per line only!.. ");
						    		System.exit(1);
						    	}
						    	
						    	// final logic
						    	if (xMax < xMin | yMax < yMin){
						    		System.out.println("values out of range, please make sure xMax > xMin etc. ");
						    		System.exit(1);
						    	}
					    	
						} catch(Exception ex){ // outer try catch.. 
							System.out.println("Something horrible has gone wrong! please check your query file for correctness! : ");
							System.out.println(ex.toString());
							System.exit(1);
						}
					
					
					// now create the query object in the array + inc the counter
					qArr[qCounter] = new Query(xMin, xMax, yMin, yMax); 
					qCounter++;
					
					
					
				} // end while / input loop
				
				// done, close the input.. 
				file_input.close();
				// and reset the counter 
				qCounter = 0;
			
			} catch(Exception ex){ // catch file io errors
				System.out.println("Problem opening the querys file! check the path.. " + QUERY_FILE);
				System.out.println(ex.toString());
				System.exit(1);
			}
		}

}
