package gridfeatures;


//TODO write code to transform the input in lat lon into an x, y, such that x and y are the distances in km from some reference point. Perhaps I can do this in r?

//TODO write out data on how the file was generated and what the mapping is from features to feature ids.




import java.io.IOException;

public class Main {
	
	public static void main(String[] args) throws IOException, InvalidDataStoreException {
		
		
		DataLoader loader = new DataLoader();
		String outputID = "1kbg";
		
		String path = "/home/finn/phd/data/20140505/";
		
		String areaFile = path + "cells1000.txt";
		
		String dataFile = path + "events"+outputID+".txt";
		String outputName = "VW"+outputID;
		String detailsName = outputID+".details";
		
		
		DataI data = new Data();
		
		
		//loader.load(dataFile, areaFile,260, data, "crime","burglary","prem","RESIDENCE");
		loader.load(dataFile, areaFile,1826, data);
		
		System.out.println("DATA LOADED");
		System.out.println("INPUT TARGET TOTAL:"+data.getTargetTotal());
		data.checkConsistency();
		System.out.println("Data consistant");
		
		
		int[] periodsback = {1,7,14,28,365};
		int reportFrequency = 100000;
		double trainPer = 3/4d; // % of the data for training
		double validPer = 1/4d; // % of the data for validation - remaining % will be test
		
	
		
		//String[] areasToLabel = {"area","area5000","areaall"}; 
		//String[] areasToLabel = {"area"}; 
		String[] areasToLabel = {};
		
		FeatureWriter featureGenerator = new FeatureWriter(data,periodsback,1,areasToLabel);
		featureGenerator.setGroups(GridGroups.buildGridGroups(43, 47,false,false));
		
		featureGenerator.setReportFrequency(reportFrequency);
		
		System.out.println(featureGenerator);
		
		featureGenerator.write(trainPer, validPer, path, outputName,FeatureWriter.FORMAT_VW);
		
		System.out.println("DONE");
	}

}
