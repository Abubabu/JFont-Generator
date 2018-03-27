package LetterRecognition;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import javax.imageio.ImageIO;

public class OverlayTest {
	public File[] listOfImages(String directoryName) throws IOException{
        File directory = new File(directoryName);
        //get all the files from a directory
       return directory.listFiles();
    }
	public static void main(String[] args) throws IOException
	{
		OverlayTest test = new OverlayTest();
		File[] library = test.listOfImages("Alphabet");
		
		Overlay3 comparator = new Overlay3("TEST.jpg");  //here goes the name of your handdrawn letter
		
		double[] accuracy = new double[library.length];
		for(int i = 0; i < library.length; i++)
		{
			accuracy[i] = comparator.overlay(library[i].getAbsolutePath());
			System.out.println("W");
		}
		
		/*double greatest = accuracy[0];
		int index = 0;
		for(int i = 0; i < accuracy.length; i++)
		{
			if(greatest < accuracy[i])
			{
				greatest = accuracy[i];
				index = i;
			}
		}*/
		
		//System.out.println(library[index].getName() + " " + index + " " + comparator.overlay(library[index].getAbsolutePath()));
		
		Arrays.sort(accuracy);
		System.out.println();
		for(int i = 0; i < accuracy.length; i++)
		{
			System.out.println(library[i].getName() + "  " + comparator.overlay(library[i].getAbsolutePath()));
		}
	}
}
