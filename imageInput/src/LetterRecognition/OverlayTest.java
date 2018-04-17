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
		Map<Double,String> display = new TreeMap<Double,String>();
		OverlayTest test = new OverlayTest();
		File[] library = test.listOfImages("Alphabet");
		
		Overlay3 comparator = new Overlay3("Alphabet/B.jpg");  //here  goes the name of your handdrawn letter
		
		for(File x : library)
		{
			double d = comparator.overlay(x.getAbsolutePath());
			display.put(d, x.getName());
		}
		
		
		Set<Double> keys = display.keySet();
    	for(Double key: keys){
    		String filename = display.get(key);
    		String split = filename.substring(0,(filename.indexOf('.')));
    		String percent = ((key.toString().length()>=4) ? key.toString().substring(2, 4) : key.toString().substring(2));
    		percent = ((key == 1) ? "100" : percent);
    		System.out.println("Letter " + split + " Accuracy:  " + percent + "%");
    	}
	}
	
}
