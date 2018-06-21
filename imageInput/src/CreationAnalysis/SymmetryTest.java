package CreationAnalysis;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;


import LetterRecognition.Overlay3;

import getImage.getImage;

public class SymmetryTest {
	public static void main(String[] args)
	{
		File directory = new File("DrawnLetters");
		for(File x : directory.listFiles())
		{
			double[] y = test(x.getAbsolutePath());
			System.out.println(y[0] + " " + y[1] + "  " + x.getName());
		}
		
	}
	public static double[] test(String filename)
	{
		double[] data = new double[2];
			
		String path = new File(".").getAbsolutePath();
		String relativeFilePath = path.substring(0, path.length()-2)+ "\\";
		getImage image = null;
		try {
			image = new getImage((new getImage(filename).getBW()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		BufferedImage YFlip = Reflection.reflectorYAxis(image);
		BufferedImage XFlip = Reflection.reflectorXAxis(image);
		
		try {
			ImageIO.write(YFlip, "bmp",new File(relativeFilePath + "YFlip.bmp"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		try {
			ImageIO.write(XFlip, "bmp",new File(relativeFilePath + "XFlip.bmp"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		
		
		Overlay3 symmetry = null;
		try {
			symmetry = new Overlay3(filename);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			data[0] = symmetry.overlay("YFlip.bmp");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			data[1] = symmetry.overlay("XFlip.bmp");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return data;
	}
}
