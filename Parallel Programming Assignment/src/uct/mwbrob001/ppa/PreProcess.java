package uct.mwbrob001.ppa;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;


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
		try{
			Scanner file_input = new Scanner (new FileInputStream(params_file));
			
			n = file_input.nextInt(); 					// Get number of ant datafiles
			ant_datafiles_list = new String[n];         // Init datafile array
			
			
			for(int i = 0 ; i<n ; i++){ 
				ant_datafiles_list[i] = file_input.nextLine();  // Grab the datafile names
			}
			
			k = file_input.nextInt();
			m = file_input.nextInt();  // Get the bin sizes
			
			file_input.close(); 	   // Finally close the stream.. 
			
		} catch(Exception ex){
			System.out.println("File IO Error!! Cannot open, please check file names.");
			System.out.println(ex.toString());
            System.exit(1);
		}
		
		
		
		
	    
	    


		
		
	}

}
