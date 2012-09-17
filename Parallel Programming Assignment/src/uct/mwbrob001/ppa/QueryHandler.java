package uct.mwbrob001.ppa;

import java.util.Scanner;

public class QueryHandler {
	
	public QueryHandler(){
		
	}
	
	// gets a query string from the keyboard
	public Query getQueryKeyboard(){
		
		//variables needed in this function
		Scanner input = new Scanner(System.in);
		String line = "";
		String[] lineArr;
		
		int xMin=0, xMax=0;
		int yMin=0, yMax=0;
		
		
		
		// Get the first line
		System.out.println();
		System.out.println("Please enter the query: ");
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
			    		System.exit(0);
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
		
    	// done, close the input.. 
		input.close();
		
		// now create the query object
		Query query = new Query(xMin, xMax, yMin, yMax);
		
		// and send him on his way :) 
		return query; 
	}

}
