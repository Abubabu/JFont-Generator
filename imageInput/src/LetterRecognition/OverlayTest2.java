package LetterRecognition;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

public class OverlayTest2 {
	
	private static Map<String,Integer> position = new TreeMap<String,Integer>();
	
	public File[] listOfImages(String directoryName) throws IOException{
        File directory = new File(directoryName);
        //get all the files from a directory
       return directory.listFiles();
    }
	
	public static void compare(String target,String name) throws IOException
	{
		Map<String,Double> display = new TreeMap<String,Double>();
		OverlayTest2 test = new OverlayTest2();
		File[] library = test.listOfImages("Alphabet");
		
		Overlay3 comparator = new Overlay3(target);	
		
		for(File file: library)
		{
			System.out.print("starting overlay...");
			double d = comparator.overlay(file.getAbsolutePath());
			System.out.println("overlay finished");
			display.put(file.getName().substring(0,1), d);
		}
		
		position.put(name, numLarger(display,name));
	}
	
	public static int numLarger(Map<String,Double> data, String name)
	{
		double num = data.get(name);
		int counter = 0;
		for(char letter = 'A'; letter <= 'Z'; letter++)
		{
			if(data.get(String.valueOf(letter)) >= num)
				counter++;
		}
		return counter;
	}
	public static void main(String[] args) throws IOException
	{
		OverlayTest2 test = new OverlayTest2();
		
		File[] library = test.listOfImages("DrawnLetters");
		for(File x : library)
		{
			compare(x.getAbsolutePath(),x.getName().substring(5,6));
			System.out.println(x.getName().substring(5,6) + "\n");
		}
		System.out.println();
		for(char letter = 'A'; letter <= 'Z'; letter++)
		{
			System.out.println(letter + " : " + position.get(String.valueOf(letter)));
		}
	}

}
