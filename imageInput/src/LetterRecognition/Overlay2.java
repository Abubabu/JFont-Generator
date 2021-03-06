package LetterRecognition;

import java.io.IOException;

import getImage.RGBValue;
import getImage.getImage;
 
public class Overlay2 {
	public static void main(String[] args) throws IOException
	{
		
		//https://stackoverflow.com/questions/29024456/image-overlay-comparison-and-pixel-color-change
		
		 
		getImage b = new getImage("letters.jpg");
		
		ImageResizer.resize("lettertest.jpg", "lettertest.jpg", b.getWidth(), b.getHeight());
		
		getImage a = new getImage("lettertest.jpg"); 
		
		getImage aConverted = new getImage(a.getBW());
		getImage bConverted = new getImage(b.getBW());
		
		RGBValue[][] apix = aConverted.getPixelsArray();
		RGBValue[][] bpix = bConverted.getPixelsArray();
		int overlap = 0;
		int nolap = 0;
		
		
		System.out.println(bConverted.getRightMost().getxPos() + " " + bConverted.getRightMost().getyPos());
	System.out.println(bConverted.getLeftMost().getxPos() + " " +bConverted.getLeftMost().getyPos());
		System.out.println(bConverted.southMost().getxPos() + " " + bConverted.southMost().getyPos());
		System.out.println(bConverted.northMost().getxPos() + " " + bConverted.northMost().getyPos());
		
		//A LOOP
		for(int i = 0; i < a.getHeight(); i++)//y  apix[ycoord][xcoord]
		{
			for(int j = 0; j < a.getWidth(); j++)//x
			{
				//System.out.println(apix[j][i].getB() + "  " + bpix[j][i].getB());
				System.out.println("x:"+ j + "  y:" + i + "  " + "A:" + apix[j][i].getR() + ", " + bpix[j][i].getR());
				if(isRGBEqual(apix[j][i],bpix[j][i]))
				{
					overlap++;		
				}
				else
				{
					nolap++;
				}
			}
		}
		
		System.out.println(overlap + " " + nolap);
		System.out.print(((double) overlap/ (double) (nolap+overlap)));
	}
	public static boolean isRGBEqual(RGBValue one, RGBValue two)
	{
		return (one.getR() == two.getR() && one.getG() == two.getG() && one.getB() == two.getB());
	}
}
