package LetterRecognition;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import getImage.RGBValue;
import getImage.getImage;

public class Overlay3 {
	public static void main(String[] args) throws IOException
	{
		
		//https://stackoverflow.com/questions/29024456/image-overlay-comparison-and-pixel-color-change
		
		 
		getImage b = new getImage("square2.png");
		
		ImageResizer imageresizer = new ImageResizer();
		imageresizer.resize("square.png", "square.png", b.getWidth(), b.getHeight());
		
		getImage a = new getImage("square.png"); 
		
		getImage aConverted = new getImage(a.getBW());
		getImage bConverted = new getImage(b.getBW());
		
		RGBValue[][] apix = aConverted.getPixelsArray();
		RGBValue[][] bpix = bConverted.getPixelsArray();
		int overlap = 0;
		int nolap = 0;
		
		
//		System.out.println(bConverted.getRightMost().getxPos() + " " + bConverted.getRightMost().getyPos());
//		System.out.println(bConverted.getLeftMost().getxPos() + " " +bConverted.getLeftMost().getyPos());
//		System.out.println(bConverted.southMost().getxPos() + " " + bConverted.southMost().getyPos());
//		System.out.println(bConverted.locateVertex().getxPos() + " " + bConverted.locateVertex().getyPos());
//		
		//A LOOP
		for(int ay = 0, by = 0; ay < a.getHeight(); ay++, by++)//y  apix[ycoord][xcoord]
		{
			for(int ax = 0, bx = 0; ax < a.getWidth() && bx < b.getWidth(); ax++, bx++)//x
			{
				//System.out.println(apix[j][i].getB() + "  " + bpix[j][i].getB());
				System.out.println("x:"+ ax + "  y:" + ay + "  " + "A:" + apix[ax][ay].getR() + ", " + bpix[ax][ay].getR());
				if(isRGBEqual(apix[ax][ay],bpix[ax][ay]))
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
