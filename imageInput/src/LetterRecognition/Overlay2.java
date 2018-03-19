package LetterRecognition;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import getImage.RGBValue;
import getImage.getImage;

public class Overlay2 {
	public static void main(String[] args) throws IOException
	{
		
		//https://stackoverflow.com/questions/29024456/image-overlay-comparison-and-pixel-color-change
		
		 
		getImage b = new getImage("MYB.png");
		
		ImageResizer imageresizer = new ImageResizer();
		imageresizer.resize("MYA.png", "MYA.png", b.getWidth(), b.getHeight());
		
		getImage a = new getImage("MYA.png"); 
		
		getImage aConverted = new getImage(a.getBW());
		getImage bConverted = new getImage(b.getBW());
		
		RGBValue[][] apix = aConverted.getArrayRGB();
		RGBValue[][] bpix = bConverted.getArrayRGB();
		int overlap = 0;
		int nolap = 0;
		
		
		System.out.println(aConverted.getRightMost().getxPos() + " " + aConverted.getRightMost().getyPos());
		System.out.println(aConverted.getLeftMost().getxPos() + " " +aConverted.getLeftMost().getyPos());
		System.out.println(aConverted.southMost().getxPos() + " " + aConverted.southMost().getyPos());
		System.out.println(aConverted.locateVertex().getxPos() + " " + aConverted.locateVertex().getyPos());
		
		//A LOOP
		for(int i = aConverted.locateVertex().getyPos(); i < aConverted.southMost().getyPos(); i++)//y  apix[ycoord][xcoord]
		{
			for(int j = aConverted.getLeftMost().getxPos(); j < aConverted.getRightMost().getxPos(); j++)//x
			{
				//System.out.println(apix[j][i].getB() + "  " + bpix[j][i].getB());
				//System.out.println("x:"+ i + "  y:" + j + "  " + "A:" + apix[j][i].getR() + ", " + bpix[j][i].getR());
				if(isRGBEqual(apix[i][j],bpix[i][j]))
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
