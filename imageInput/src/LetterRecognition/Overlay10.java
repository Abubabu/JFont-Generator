package LetterRecognition;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import getImage.CardinalPixels;
import getImage.RGBValue;
import getImage.getImage;

public class Overlay10 {
	/* Ok so here me out. We have to bypass the inherent difference in the size of the drawn letter even in the crop. So what I propose is that we crop the initial letter using cardinal pixels to remove as much
	 * white space as possible and then we crop it with increment into a 100x100 buffered image and then do a 1:1 comparisons with the cropped test cases
	 */
	private final getImage testCase;
	public Overlay10(String filepath) throws IOException
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
		
		
		int staticHeight = staticlocation.getSouth().getyPos()- staticlocation.getNorth().getyPos();
		int staticWidth = staticlocation.getRight().getxPos() - staticlocation.getLeft().getxPos();
		int userHeight = userlocation.getSouth().getyPos() - userlocation.getNorth().getyPos();
		int userWidth = userlocation.getRight().getxPos() - userlocation.getLeft().getxPos();
		
		int userXInc = getIncrement(userWidth);
		int userYInc = getIncrement(userHeight);
		int staticXInc = getIncrement(staticWidth);
		int staticYInc = getIncrement(staticHeight);
		
		//System.out.println(userWidth + ":" + userXInc + " " + userHeight + ":" +  userYInc);
		//System.out.println(staticWidth  + ":" + staticXInc + " " + staticHeight + ":" + staticYInc);
		
		int overlap = 0;
		int nolap = 0;
		
		BufferedImage userCopy = new BufferedImage(100,100,BufferedImage.TYPE_INT_RGB);
		BufferedImage staticCopy = new BufferedImage(100,100,BufferedImage.TYPE_INT_RGB);
		
		//System.out.println(staticlocation.getLeft().getxPos() + " " + staticlocation.getLeft().getyPos());
		//System.out.println(staticlocation.getNorth().getxPos() + " " + staticlocation.getNorth().getyPos());
		
		for(int userPosX = userlocation.getLeft().getxPos(), staticPosX = staticlocation.getLeft().getxPos(), xinc = 0; xinc < 100; userPosX += userXInc, staticPosX += staticXInc, xinc++)
		{
			for(int userPosY = userlocation.getNorth().getyPos(), staticPosY = staticlocation.getNorth().getyPos(), yinc= 0; yinc < 100; userPosY += userYInc, staticPosY += staticYInc, yinc++)
			{
				int userRed = userpix[userPosX][userPosY].getR();
				int userGreen = userpix[userPosX][userPosY].getG();
				int userBlue = userpix[userPosX][userPosY].getB();
				Color userColor = new Color(userRed,userGreen,userBlue);
				userCopy.setRGB(xinc, yinc,userColor.getRGB());
				
				int staticRed = staticpix[staticPosX][staticPosY].getR();
				int staticGreen = staticpix[staticPosX][staticPosY].getG();
				int staticBlue = staticpix[staticPosX][staticPosY].getB();
				Color staticColor = new Color(staticRed,staticGreen,staticBlue);
				staticCopy.setRGB(xinc, yinc,staticColor.getRGB());
				
			}
		}
		ImageIO.write(userCopy, "bmp",new File(/*"C:\\Users\\BT_1N3_23\\git\\JFont-Generator\\imageInput\\TestCropout.bmp"));*/"C:\\Users\\Salehaakter\\Desktop\\userCropout.bmp"));
		getImage userCropCopy = new getImage(/*"C:\\\\Users\\\\BT_1N3_23\\\\git\\\\JFont-Generator\\\\imageInput\\\\TestCropout.bmp");*/"C:\\Users\\Salehaakter\\Desktop\\userCropout.bmp");
		ImageIO.write(staticCopy, "bmp",new File(/*"C:\\Users\\BT_1N3_23\\git\\JFont-Generator\\imageInput\\staticCropout.bmp"));*/"C:\\Users\\Salehaakter\\Desktop\\staticCropout.bmp"));
		getImage staticCropCopy = new getImage(/*"C:\\\\Users\\\\BT_1N3_23\\\\git\\\\JFont-Generator\\\\imageInput\\\\staticCropout.bmp");*/"C:\\Users\\Salehaakter\\Desktop\\staticCropout.bmp");
		RGBValue[][] croppedUserPixels = userCropCopy.getPixelsArray();
		RGBValue[][] croppedStaticPixels = staticCropCopy.getPixelsArray();
		CardinalPixels userCroppedCardinals = new CardinalPixels(userCropCopy);
		CardinalPixels staticCroppedCardinals = new CardinalPixels(staticCropCopy);
		
		int counter = 0;
		for(int x = 0; x < 100; x+=5) {
			for(int y = 0; y < 100; y+=5) {
				if (checkNearbyPixels(x,y,croppedUserPixels,croppedStaticPixels)) {
					overlap++;
				} else {
					nolap++;
				} 	
			}
			
		}
		//System.out.println(overlap + " " + nolap);
		//System.out.print(((double) overlap/ (double) (nolap+overlap)));
		return ((double) overlap/ (double) (nolap+overlap));
	}
	public static void main(String[] args) throws IOException
	{
		
		Overlay10 comparator = new Overlay10("Alphabet/Q.png");
		comparator.overlay("Random/MYA.png");
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
	
	public static boolean isBlack(RGBValue rgb)
	{
		return rgb.getR() == 0 && rgb.getG() == 0 && rgb.getB() == 0;
	}
	
	public static boolean checkNearbyPixels(int x, int y, RGBValue[][] userPix, RGBValue[][] staticPix)
	{
		int blackpixelsUser = 0;
		int blackPixelsStatic = 0;
		int match = 0;
		int noMatch = 0;
		for(int i = x-1; i < x+2; i++)
		{
			for(int j = y-1; j < y +2; j++)
			{
				if( (j <0 || j > 99) || (i < 0 || i > 99)  )
				{
					break;
				}
				
				if (isBlack(userPix[x][y])) {
					blackpixelsUser++;
				}
				if(isBlack(staticPix[x][y]))
					blackPixelsStatic++;
			}
		}
		if(blackpixelsUser > blackPixelsStatic ) {
			if(blackPixelsStatic + 1 >= blackpixelsUser) {
				return true;
			}
			else 
				return false;
		}
		else if(blackPixelsStatic > blackpixelsUser ) {
			if(blackpixelsUser + 1 >= blackPixelsStatic) {
				return true;
			}
			else 
				return false;
		}
		else
			return true;
	}
}
