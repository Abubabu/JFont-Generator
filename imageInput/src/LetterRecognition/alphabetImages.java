package LetterRecognition;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import getImage.RGBValue;
import getImage.getImage;

public class alphabetImages {
	
	private static int num = 0;
	
	public static void main(String[] args) throws IOException
	{
		OverlayTest test = new OverlayTest();
		File[] library = test.listOfImages("Alphabet");
		
		for(File x : library)
		{
			overlay(x.getAbsolutePath(), "Alphabet/R.png");
		}
	}
	public static double overlay(String filepath, String filepath2) throws IOException
	{
		getImage userImage = new getImage(filepath);
		getImage staticCase = new getImage(filepath2); 
		
		getImage userConverted = new getImage(userImage.getBW());
		getImage staticConverted = new getImage(staticCase.getBW());
		
		int userNativeHeight = userConverted.getHeight();
		int userNativeWidth = userConverted.getWidth();
		int staticNativeHeight = staticConverted.getHeight();
		int staticNativeWidth = staticConverted.getWidth();
		
		RGBValue[][] userpix = userConverted.getPixelsArray();
		RGBValue[][] staticpix = staticConverted.getPixelsArray();
		
		
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
		ImageIO.write(userCopy, "bmp",new File("C:\\Users\\BT_1N3_23\\git\\JFont-Generator\\imageInput\\" + num + ".bmp"));
		num++;
		
		System.out.println(overlap + " " + nolap);
		System.out.print(((double) overlap/ (double) (nolap+overlap)));
		return ((double) overlap/ (double) (nolap+overlap));
	}
	
	private static int getIncrement(double measurement) {
		return (int) (measurement * .01);
	}
	
	public static boolean isRGBEqual(RGBValue one, RGBValue two)
	{
		return (one.getR() == two.getR() && one.getG() == two.getG() && one.getB() == two.getB());
	}
	
	public static boolean isBlack(RGBValue rgb)
	{
		return rgb.getR() == 0 && rgb.getG() == 0 && rgb.getB() == 0;
	}
}
