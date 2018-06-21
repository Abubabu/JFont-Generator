package CreationAnalysis;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

import LetterRecognition.Overlay10;
import LetterRecognition.Overlay3;
import LetterRecognition.OverlayTest;
import getImage.RGBValue;
import getImage.getImage;
import getImage.pixelPosition;

public class ScannerHorizontal {
	/*private static BufferedImage Left;
	private static BufferedImage Right;*/
	
	public static void main(String[] args) throws IOException
	{
		
		OverlayTest test = new OverlayTest();
		File[] library = test.listOfImages("Alphabet");
		
		for(File x : library)
		{
			BufferedImage Left = scannerHLeft(x.getAbsolutePath());
			BufferedImage Right = scannerHRight(x.getAbsolutePath());
			BufferedImage leftFlip = Reflection.reflectorXAxis(new getImage(Left));
			BufferedImage rightFlip = Reflection.reflectorXAxis(new getImage(Right));
			
			ImageIO.write(leftFlip, "bmp",new File("C:\\Users\\Salehaakter\\git\\JFont-Generator\\imageInput\\HorizontalFLeft.bmp"));	
			ImageIO.write(rightFlip, "bmp",new File("C:\\Users\\Salehaakter\\git\\JFont-Generator\\imageInput\\HorizontalFRight.bmp"));	

			Overlay3 symmetry = new Overlay3(Left);
			System.out.print("O3  " + symmetry.overlay(rightFlip) + "  ");
			
			Overlay3 symmetry2 = new Overlay3(Right);
			System.out.println(symmetry2.overlay(leftFlip));
		}
		
		/*ScannerHorizontal.setLeft(Left);
		ScannerHorizontal.setRight(Right);
		CleanCrops();*/
		
	}
	
	
	public static BufferedImage scannerHLeft(String filepath) throws IOException
	{
		getImage image = new getImage(filepath); 	
		getImage imageConverted = new getImage(image.getBW());	
		RGBValue[][] imagePix = imageConverted.getPixelsArray();		
		pixelPosition[][] foundPix = new pixelPosition[imageConverted.getWidth()][imageConverted.getHeight()];
		
		for(int y = 0; y < imageConverted.getHeight(); y++)
		{
			for(int x = 0; x < imageConverted.getWidth(); x++)
			{
				if(isBlack(imagePix[x][y]))
				{
					foundPix[x][y] = new pixelPosition(x,y);
					break;
				}
			}
		}
		
		BufferedImage HorLeft = new BufferedImage(imageConverted.getWidth(),imageConverted.getHeight(),BufferedImage.TYPE_INT_RGB);
		
		
		for(int v = 0; v < imageConverted.getWidth(); v++)
		{
			for(int w = 0; w < imageConverted.getHeight();w++) {
				if(foundPix[v][w] != null) {
					Color staticColor = new Color(0,0,0);
					HorLeft.setRGB(v,w,staticColor.getRGB());
				}
				else
				{
					Color staticColor = new Color(255,255,255);
					HorLeft.setRGB(v,w,staticColor.getRGB());
				}
			}
		}
		ImageIO.write(HorLeft, "bmp",new File("C:\\Users\\Salehaakter\\git\\JFont-Generator\\imageInput\\HorizontalLeft.bmp"));	
		return HorLeft;
	}
	
	public static BufferedImage scannerHRight(String filepath) throws IOException
	{
		getImage image = new getImage(filepath); 	
		getImage imageConverted = new getImage(image.getBW());	
		RGBValue[][] imagePix = imageConverted.getPixelsArray();		
		pixelPosition[][] foundPix = new pixelPosition[imageConverted.getWidth()][imageConverted.getHeight()];
		
		for(int y = imageConverted.getHeight()-1; y >= 0 ; y--)
		{
			for(int x = imageConverted.getWidth()-1 ; x >= 0 ; x--)
			{
				if(isBlack(imagePix[x][y]))
				{
					foundPix[x][y] = new pixelPosition(x,y);
					break;
				}
			}
		}
		
		BufferedImage HorRight = new BufferedImage(imageConverted.getWidth(),imageConverted.getHeight(),BufferedImage.TYPE_INT_RGB);
		
		
		for(int v = 0; v < imageConverted.getWidth(); v++)
		{
			for(int w = 0; w < imageConverted.getHeight();w++) {
				if(foundPix[v][w] != null) {
					Color staticColor = new Color(0,0,0);
					HorRight.setRGB(v,w,staticColor.getRGB());
				}
				else
				{
					Color staticColor = new Color(255,255,255);
					HorRight.setRGB(v,w,staticColor.getRGB());
				}
			}
		}
		ImageIO.write(HorRight, "bmp",new File("C:\\Users\\Salehaakter\\git\\JFont-Generator\\imageInput\\HorizontalRight.bmp"));	
		return HorRight;
		
	}
	
	/*public static void CleanCrops() throws IOException
	{ 
		getImage imageLeftConverted = new getImage(getLeft()); 	
		RGBValue[][] imageLeftPix = imageLeftConverted.getPixelsArray();	 	
		CardinalPixels cardinalColorsLeft = new CardinalPixels(imageLeftConverted);
		int RightLimit = cardinalColorsLeft.getRight().getxPos() + 50;
		
		getImage imageRightConverted = new getImage(getRight()); 	
		RGBValue[][] imageRightPix = imageRightConverted.getPixelsArray();	
		CardinalPixels cardinalColorsRight = new CardinalPixels(imageRightConverted);
		int LeftLimit = cardinalColorsRight.getLeft().getxPos() - 50;
		
		for(int x = 0; x < imageRightConverted.getWidth(); x++) //widths and height of both images should be the same
		{
			for(int y = 0; y < imageRightConverted.getHeight(); y++)
			{
				if(x > RightLimit && isBlack(imageLeftPix[x][y]))
				{
					getLeft().setRGB(255, 255, 255);
				}
				if(x < LeftLimit && isBlack(imageRightPix[x][y]))
				{
					getRight().setRGB(255, 255, 255);
				}
			}
		}
		
		
	}*/
	public static boolean isBlack(RGBValue rgb)
	{
		return rgb.getR() == 0 && rgb.getG() == 0 && rgb.getB() == 0;
	}

	/*public static BufferedImage getRight() {
		return Right;
	}

	public static void setRight(BufferedImage right) {
		Right = right;
	}

	public static BufferedImage getLeft() {
		return Left;
	}

	public static void setLeft(BufferedImage left) {
		Left = left;
	}*/
	
}
