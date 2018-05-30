package LetterRecognition;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import getImage.CardinalPixels;
import getImage.RGBValue;
import getImage.getImage;

public class Overlay9 {
private final getImage testCase;
	
	public Overlay9(String filepath) throws IOException
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
		
		int staticHeight = staticlocation.getSouth().getyPos()- staticlocation.getNorth().getyPos();
		int staticWidth = staticlocation.getRight().getxPos() - staticlocation.getLeft().getxPos();
		int userHeight = userlocation.getSouth().getyPos() - userlocation.getNorth().getyPos();
		int userWidth = userlocation.getRight().getxPos() - userlocation.getLeft().getxPos();
		
		int staticSquareSideLength = ((staticHeight > staticWidth) ? staticHeight : staticWidth);
		int userSquareSideLength = ((userHeight > userWidth) ? userHeight: userWidth);
		
		int staticCenterX = staticlocation.getRight().getxPos() - (staticWidth/2);
		int staticCenterY = staticlocation.getSouth().getyPos() - (staticHeight/2);
		int userCenterX = userlocation.getRight().getxPos() - (userWidth/2);
		int userCenterY = userlocation.getSouth().getyPos() - (userHeight/2);
		
		
		
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
				int userRed = userpix[userPosX][userPosY].getR();
				int userGreen = userpix[userPosX][userPosY].getG();
				int userBlue = userpix[userPosX][userPosY].getB();
				Color userColor = new Color(userRed,userGreen,userBlue);
				userCopy.setRGB(xinc, yinc,userColor.getRGB());
				
			}
		}
		ImageIO.write(userCopy, "bmp",new File("C:\\Users\\BT_1N3_23\\git\\JFont-Generator\\imageInput\\TestCropout.bmp"));
		getImage userCropCopy = new getImage("C:\\Users\\BT_1N3_23\\git\\JFont-Generator\\imageInput\\TestCropout.bmp");
		RGBValue[][] croppedUserPixels = userCropCopy.getPixelsArray();
		CardinalPixels userCroppedCardinals = new CardinalPixels(userCropCopy);
		CardinalPixels staticCroppedCardinals = new CardinalPixels(staticCase);
		int largestNorth = 0;
		int largestSouth = 0;
		int largestEast = 0;
		int largestWest = 0;
		
		if(userCroppedCardinals.getLeft().getxPos() < staticCroppedCardinals.getLeft().getxPos()){
			largestEast = userCroppedCardinals.getLeft().getxPos();
		}
		else
			largestEast = staticCroppedCardinals.getLeft().getxPos();
		
		if(userCroppedCardinals.getRight().getxPos() < staticCroppedCardinals.getRight().getxPos()){
			largestWest = staticCroppedCardinals.getRight().getxPos();
		}
		else
			largestWest = userCroppedCardinals.getRight().getxPos();
		
		if(userCroppedCardinals.getLeft().getxPos() < staticCroppedCardinals.getLeft().getxPos()){
			largestEast = userCroppedCardinals.getLeft().getxPos();
		}
		else
			largestEast = staticCroppedCardinals.getLeft().getxPos();
		
		if(userCroppedCardinals.getLeft().getxPos() < staticCroppedCardinals.getLeft().getxPos()){
			largestEast = userCroppedCardinals.getLeft().getxPos();
		}
		else
			largestEast = staticCroppedCardinals.getLeft().getxPos();
		for(int x = 0; x < 100; x++) {
			for(int y = 0; y < 100; y++) {
				if(x > userCroppedCardinals.getLeft().getxPos() && x < userCroppedCardinals.getRight().getxPos()) {
					if(y < userCroppedCardinals.getNorth().getyPos() && y >userCroppedCardinals.getSouth().getyPos()) {
						if (isRGBEqual(croppedUserPixels[x][y], staticpix[x][y])) {
							overlap++;
						} else {
							nolap++;
						} 	
					}
				}
			}
		}
		System.out.println(overlap + " " + nolap);
		System.out.print(((double) overlap/ (double) (nolap+overlap)));
		return ((double) overlap/ (double) (nolap+overlap));
	}
	public static void main(String[] args) throws IOException
	{
		Overlay9 comparator = new Overlay9("Alphabet/Q.png");
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
	
	public static boolean isBlack(RGBValue rgb)
	{
		return rgb.getR() == 0 && rgb.getG() == 0 && rgb.getB() == 0;
	}
}
