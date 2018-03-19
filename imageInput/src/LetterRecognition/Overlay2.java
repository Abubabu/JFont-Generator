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
		
		getImage aConverted = new getImage(a.getBW(a.getFilepath()));
		getImage bConverted = new getImage(b.getBW(b.getFilepath()));
		
		RGBValue[][] apix = aConverted.getArrayRGB();
		RGBValue[][] bpix = bConverted.getArrayRGB();
		int overlap = 0;
		int nolap = 0;
		
		
		System.out.println(aConverted.getRightMost().getxPos() + " " + aConverted.getLeftMost().getxPos());
		System.out.println(aConverted.getRightMost().getyPos()+ " " +aConverted.getLeftMost().getyPos());
		
		
		//A LOOP
		for(int i = aConverted.getLeftMost().getxPos() ; i < aConverted.getRightMost().getxPos(); i++)
		{
			for(int j = aConverted.southMost().getyPos(); j < aConverted.locateVertex().getyPos(); j++)
			{
				//System.out.println(apix[j][i].getB() + "  " + bpix[j][i].getB());
				//System.out.println("x:"+ i + "  y:" + j + "  " + "A:" + a.getRGB(j,i).getR() + ", " + aplus.getRGB(j,i).getR());
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
		
		//B LOOP
		/*for(int i = (int) (aScaled.getLeftMost().getxPos()*1.5); i < aScaled.getRightMost().getxPos()*1.5; i++)
		{
			for(int j = (int) (aScaled.getLeftMost().getyPos()*1.5); j < aScaled.getRightMost().getyPos()*1.5; j++)
			{
				//System.out.println(apix[j][i].getB() + "  " + bpix[j][i].getB());
				//System.out.println("x:"+ i + "  y:" + j + "  " + "A:" + a.getRGB(j,i).getR() + ", " + aplus.getRGB(j,i).getR());
				if(isRGBEqual(apix[j][i],bpix[j][i]))
				{
					overlap++;		
				}
				else
				{
					nolap++;
				}
			}
		}*/
		System.out.println(overlap + " " + nolap);
		System.out.print(((double) overlap/ (double) (nolap+overlap)));
	}
	public static boolean isRGBEqual(RGBValue one, RGBValue two)
	{
		return (one.getR() == two.getR() && one.getG() == two.getG() && one.getB() == two.getB());
	}
}
