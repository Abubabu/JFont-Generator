package Main;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class OverlayTest {
	public File[] listOfImages(String directoryName) throws IOException{
        File directory = new File(directoryName);
        //get all the files from a directory
       return directory.listFiles();
    }
	public static void main(String[] args) throws IOException
	{
		Map<String,Double> display = new TreeMap<String,Double>();
		Map<Double,String> sortByValue = new TreeMap<Double,String>();
		OverlayTest test = new OverlayTest();
		File[] library = test.listOfImages("Alphabet");
		
		//Overlay3 comparator = new Overlay3("Alphabet/B.jpg");  //here  goes the name of your handdrawn letter
		Overlay3 comparator = new Overlay3("Random/MYA.png");	//here  goes the name of your handdrawn letter	
		
		for(File x : library)
		{
			System.out.print(x.getAbsolutePath());
			double d = comparator.overlay(x.getAbsolutePath());
			display.put(x.getName(), d);
			sortByValue.put(d, x.getName());
			System.out.print(".");
		}
		System.out.println();
		
		
	/*	Set<String> keys = display.keySet();
    	for(String key: keys){
    		double value = display.get(key);
    		String split = key.substring(0,(key.indexOf('.')));
    		System.out.println("Letter " + split + " Accuracy: " + value);
    	}	*/
    	
    	Set<Double> keys = sortByValue.keySet();
    	for(Double key: keys){
    		String name = sortByValue.get(key);
    		String split = name.substring(0,(name.indexOf('.')));
    		System.out.println("Letter " + split + " Accuracy: " + key);
    	}
	}
	
}
