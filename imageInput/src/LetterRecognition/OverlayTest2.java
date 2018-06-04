package LetterRecognition;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class OverlayTest2 {
	
	private static ArrayList<Integer> positions = new ArrayList<Integer>();
	public File[] listOfImages(String directoryName) throws IOException{
        File directory = new File(directoryName);
        //get all the files from a directory
       return directory.listFiles();
    }
	public static void compare(String target,String file) throws IOException
	{
		Map<String,Double> display = new TreeMap<String,Double>();
		Map<Double,String> sortByValue = new TreeMap<Double,String>();
		OverlayTest2 test = new OverlayTest2();
		File[] library = test.listOfImages("Alphabet");
		
		Overlay10 comparator = new Overlay10("Random/MYA.png");	
		
		for(File x : library)
		{
			System.out.print(x.getAbsolutePath());
			double d = comparator.overlay(x.getAbsolutePath());
			display.put(x.getName(), d);
			sortByValue.put(d, x.getName());
			System.out.print(".");
		}
		System.out.println();
		
    	
		int counter = 0;
    	Set<Double> keys = sortByValue.keySet();
    	for(Double key : keys)
    	{
    			counter++;
    			String name = sortByValue.get(key);
        		String split = name.substring(0,(name.indexOf('.')));
    			if(split.equals(target))
    			{
    				positions.add(counter);
    				break;
    			}
    	}
    	
	}
	public static void main(String[] args) throws IOException
	{
		OverlayTest2 test = new OverlayTest2();
		File[] library = test.listOfImages("Alphabet");
		for(File x : library)
		{
			compare("A",x.getAbsolutePath());
		}
		
		for(int i = 0; i < positions.size(); i++)
		{
			System.out.println(positions.get(i));
		}
	}
}
