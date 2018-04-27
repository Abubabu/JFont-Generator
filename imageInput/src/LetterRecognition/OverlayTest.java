package LetterRecognition;

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
		OverlayTest test = new OverlayTest();
		File[] library = test.listOfImages("Alphabet");
		
		//Overlay3 comparator = new Overlay3("Alphabet/B.jpg");  //here  goes the name of your handdrawn letter
		Overlay4 comparator = new Overlay4("Alphabet/B.jpg");	//here  goes the name of your handdrawn letter	
		
		for(File x : library)
		{
			double d = comparator.overlay(x.getAbsolutePath());
			display.put(x.getName(), d);
		}
		
		
		Set<String> keys = display.keySet();
    	for(String key: keys){
    		double value = display.get(key);
    		String split = key.substring(0,(key.indexOf('.')));
    		System.out.println("Letter " + split + " Accuracy: " + value);
    	}
	}
	
}
