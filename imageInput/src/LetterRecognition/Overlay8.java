package LetterRecognition;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import getImage.CardinalPixels;
import getImage.RGBValue;
import getImage.getImage;

public class Overlay8 {
	
	private final getImage testCase;
	
	public Overlay8(String filepath) throws IOException
	{
		getImage testcase = new getImage(filepath);
		this.testCase = testcase;
	}
	
	public double overlay(String filepath) throws IOException
	{
		getImage userImage = getTestCase();
		getImage staticCase = new getImage(filepath); 
		
		getImage userConverted = new getImage(userImage.getBW());
		getImage staticConverted = new getImage(staticCase.getBW());
		
		int userNativeHeight = userConverted.getHeight();
		int userNativeWidth = userConverted.getWidth();
		int staticNativeHeight = staticConverted.getHeight();
		int staticNativeWidth = staticConverted.getWidth();
		
		RGBValue[][] userpix = userConverted.getPixelsArray();
		RGBValue[][] staticpix = staticConverted.getPixelsArray();
		
		CardinalPixels userlocation = new CardinalPixels(userConverted);
		CardinalPixels staticlocation = new CardinalPixels(staticConverted);
		
		int userXInc = getIncrement(userNativeWidth);
		int userYInc = getIncrement(userNativeHeight);
		int staticXInc = getIncrement(staticNativeWidth);
		int staticYInc = getIncrement(staticNativeHeight);
		
		int overlap = 0;
		int nolap = 0;
		System.out.println(userYInc);
		System.out.println(userNativeHeight);
		BufferedImage userCopy = new BufferedImage(100,100,BufferedImage.TYPE_INT_RGB);
		System.out.println("im running here");
		
		for(int userPosX = 0, staticPosX = 0, xinc = 0; xinc < 100; userPosX += userXInc, staticPosX += staticXInc, xinc++)
		{
			System.out.println("im running here first loop");
			for(int userPosY = 0, staticPosY = 0, yinc= 0; yinc < 100; userPosY += userYInc, staticPosY += staticYInc, yinc++)
			{
				System.out.println(xinc + "," + yinc);
				if (isRGBEqual(userpix[userPosX][userPosY], staticpix[staticPosX][staticPosY])) {
					overlap++;
				} else {
					nolap++;
				} 
				int userRed = userpix[userPosX][userPosY].getR();
				int userGreen = userpix[userPosX][userPosY].getG();
				int userBlue = userpix[userPosX][userPosY].getB();
				Color userColor = new Color(userRed,userGreen,userBlue);
				userCopy.setRGB(xinc, yinc,userColor.getRGB());
				
			}
		}
		ImageIO.write(userCopy, "bmp",new File("C:\\Users\\BT_1N3_23\\git\\JFont-Generator\\imageInput\\TestCropout.bmp"));
		
		System.out.println(overlap + " " + nolap);
		System.out.print(((double) overlap/ (double) (nolap+overlap)));
		return ((double) overlap/ (double) (nolap+overlap));
	}
	public static void main(String[] args) throws IOException
	{
		Overlay8 comparator = new Overlay8("Alphabet/Q.png");
		comparator.overlay("Alphabet/R.png");
	}
	
	public getImage getTestCase() {
		return testCase;
	}
	
	private static int getIncrement(double measurement) {
		return (int) (measurement * .01);
	}
	
	public static boolean isRGBEqual(RGBValue one, RGBValue two)
	{
		return (one.getR() == two.getR() && one.getG() == two.getG() && one.getB() == two.getB());
	}
}
