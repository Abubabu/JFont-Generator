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
		
		
		getImage a = new getImage("a2.png"); 
		getImage b = new getImage("b2.png");
		
		//System.out.println(a.getHeight() + " " + a.getWidth());
		
		ImageResizer imageresizer = new ImageResizer();
		imageresizer.resize("a2.png", "a2.png", b.getWidth(), b.getHeight());
		
		getImage aScaled = new getImage("a2.png"); 
		getImage bScaled = new getImage("b2.png");
		
		RGBValue[][] apix = aScaled.getArrayRGB();
		RGBValue[][] bpix = bScaled.getArrayRGB();
		int overlap = 0;
		int nolap = 0;
		
		int aRange =  aScaled.getRightMost().getxPos() - aScaled.getLeftMost().getxPos();
		int bRange =  bScaled.getRightMost().getxPos() - bScaled.getLeftMost().getxPos();
		
		//A LOOP
		for(int i = (int) (aScaled.getLeftMost().getxPos()*1.5); i < aScaled.getRightMost().getxPos()*1.5; i++)
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
		}
		
		//B LOOP
		for(int i = (int) (aScaled.getLeftMost().getxPos()*1.5); i < aScaled.getRightMost().getxPos()*1.5; i++)
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
		}
		System.out.println(overlap + " " + nolap);
		System.out.print(((double) overlap/ (double) (nolap+overlap)));
	}
	public static boolean isRGBEqual(RGBValue one, RGBValue two)
	{
		return (one.getR() == two.getR() && one.getG() == two.getG() && one.getB() == two.getB());
	}
}
