package LetterRecognition;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import getImage.CardinalPixels;
import getImage.RGBValue;
import getImage.getImage;

public class Overlay3 {
	public static void main(String[] args) throws IOException
	{
		
		//https://stackoverflow.com/questions/29024456/image-overlay-comparison-and-pixel-color-change
		
		 
		getImage b = new getImage("circle.png");
		
		ImageResizer imageresizer = new ImageResizer();
		imageresizer.resize("heart.png", "heart.png", b.getWidth(), b.getHeight());
		
		getImage a = new getImage("heart.png"); 
		
		getImage aConverted = new getImage(a.getBW());
		getImage bConverted = new getImage(b.getBW());
		
		RGBValue[][] apix = aConverted.getPixelsArray();
		RGBValue[][] bpix = bConverted.getPixelsArray();
		int overlap = 0;
		int nolap = 0;
		
		CardinalPixels alocation = new CardinalPixels(aConverted);
		CardinalPixels blocation = new CardinalPixels(bConverted);
		
		System.out.println("heartNorth: " + alocation.getNorth().getxPos() + "," + alocation.getNorth().getyPos());
		System.out.println("heartSouth: " + alocation.getSouth().getxPos() + "," + alocation.getSouth().getyPos());
		System.out.println("heartLeft: " + alocation.getLeft().getxPos() + "," + alocation.getLeft().getyPos());
		System.out.println("heartRight: " + alocation.getRight().getxPos() + "," + alocation.getRight().getyPos());
		System.out.println("circleNorth: " + blocation.getNorth().getxPos() + "," + blocation.getNorth().getyPos());
		System.out.println("circleSouth: " + blocation.getSouth().getxPos() + "," + blocation.getSouth().getyPos());
		System.out.println("circleLeft: " + blocation.getLeft().getxPos() + "," + blocation.getLeft().getyPos());
		System.out.println("circleRight: " + blocation.getRight().getxPos() + "," + blocation.getRight().getyPos());
		
		int bWidth = blocation.getRight().getxPos()-blocation.getLeft().getxPos();
		int bHeight = blocation.getSouth().getyPos()-blocation.getNorth().getyPos();
		
		int aWidth = alocation.getRight().getxPos()-alocation.getLeft().getxPos();
		int aHeight = alocation.getSouth().getyPos()-alocation.getNorth().getyPos();
		System.out.println(bWidth + " " + bHeight + " : " + aWidth + " " + aHeight);
		
		scale(bWidth,bHeight,"heart.png");
		
		getImage test = new getImage(new getImage("heart.png").getBW());
		alocation = new CardinalPixels(test);
		
		aWidth = alocation.getRight().getxPos()-alocation.getLeft().getxPos();
		aHeight = alocation.getSouth().getyPos()-alocation.getNorth().getyPos();
		System.out.println(bWidth + " " + bHeight + " : " + aWidth + " " + aHeight);
		
		for(int ay = alocation.getNorth().getyPos(), by = blocation.getNorth().getyPos(); ay < alocation.getSouth().getyPos() && by < blocation.getSouth().getyPos(); ay++, by++)//y  apix[ycoord][xcoord]
		{
			for(int ax = alocation.getLeft().getxPos(), bx = blocation.getLeft().getxPos(); ax < alocation.getRight().getxPos() && bx < blocation.getRight().getxPos(); ax++, bx++)//x
			{
				//System.out.println(apix[j][i].getB() + "  " + bpix[j][i].getB());
				System.out.println("x:"+ ax + "  y:" + ay + "  " + "A:" + apix[ax][ay].getR() + ", " + bpix[bx][by].getR());
				if (isBlack(apix[ax][ay]) || isBlack(bpix[bx][by])) {
					if (isRGBEqual(apix[ax][ay], bpix[bx][by])) {
						overlap++;
					} else {
						nolap++;
					} 
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
	public static void scale(int bWidth, int bHeight, String filepath) throws IOException
	{
		getImage scale = null;
		CardinalPixels alocation = null;
		ImageResizer imageresizer = new ImageResizer();
		int aWidth = 0;
		int aHeight = 0;
		int xinc = 0;
		int yinc = 0;
		while(true)
		{
			scale = new getImage(filepath);
			alocation = new CardinalPixels(scale);
			aWidth = alocation.getRight().getxPos()-alocation.getLeft().getxPos();
			aHeight = alocation.getSouth().getyPos()-alocation.getNorth().getyPos();
			if(aWidth == bWidth && aHeight == bHeight )
			{
				break;
			}
			if(aWidth > bWidth)
			{
				xinc--;
			}
			if(aWidth < bWidth)
			{
				xinc++;
			}
			if(aHeight > bHeight)
			{
				yinc--;
			}
			if(aHeight < bHeight)
			{
				yinc++;
			}
			imageresizer.resize(filepath, filepath, scale.getWidth()+xinc, scale.getHeight()+yinc);
			xinc = 0;
			yinc = 0;
		}
		
	}
	public static boolean isBlack(RGBValue rgb)
	{
		return rgb.getR() == 0 && rgb.getG() == 0 && rgb.getB() == 0;
	}
}
