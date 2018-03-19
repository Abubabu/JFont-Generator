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
		
		 
		getImage b = new getImage("2.jpg");
		
		ImageResizer imageresizer = new ImageResizer();
		imageresizer.resize("1.jpg", "1.jpg", b.getWidth(), b.getHeight());
		
		getImage a = new getImage("1.jpg"); 
		
		getImage aConverted = new getImage(a.getBW(a.getFilepath()));
		getImage bConverted = new getImage(b.getBW(b.getFilepath()));
		
		RGBValue[][] apix = aConverted.getArrayRGB();
		RGBValue[][] bpix = bConverted.getArrayRGB();
		int overlap = 0;
		int nolap = 0;
		
		/*int aRange =  aScaled.getRightMost().getxPos() - aScaled.getLeftMost().getxPos();
		int bRange =  b.getRightMost().getxPos() - b.getLeftMost().getxPos();*/
		
		
//		System.out.println(aScaled.getRightMost().getxPos() + " " + aScaled.getLeftMost().getxPos());
//		System.out.println(bRange);
		//A LOOP
		for(int i = 0; i < aScaled.getWidth(); i++)
		{
			for(int j = 0; j < aScaled.getHeight(); j++)
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
