package gridfeatures;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import au.com.bytecode.opencsv.CSVReader;

/*** Reads in raw events and creats a file suitable for DataLoader
 * That is :
 * lat,lon,period,area, other higher level areas, any categories.
 *  ***/
public class EventReader {
	private int startYear;
	
	
	
	
	// need to read csv here...
	public void read(File file) throws IOException{
		CSVReader reader = new CSVReader(new FileReader(file));
	    String [] nextLine;
	    while ((nextLine = reader.readNext()) != null) {
	        // nextLine[] is an array of values from the line
	        
	    	
	    	
	    }
	}
	
	//I may want to set the grid size, the type of crime to include, how many years worth of data.
	
	

}
