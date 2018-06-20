package LetterRecognition;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class OverlayTest3 {
	
	public static ArrayList<Double> getPercentages(String filepath)
	{
		ArrayList<Double> display = new ArrayList<Double>();
		OverlayTest test = new OverlayTest();
		File[] library = null;
		try {
			library = test.listOfImages("Alphabet");
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		Overlay3 comparator = null;
		try {
			comparator = new Overlay3(filepath);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		for(File x : library)
		{
			double d = 0;
			try {
				d = comparator.overlay(x.getAbsolutePath());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			display.add(d);
		}
		
		return display;
	}
}
