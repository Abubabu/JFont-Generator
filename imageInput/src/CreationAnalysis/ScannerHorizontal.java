package CreationAnalysis;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import getImage.CardinalPixels;
import getImage.RGBValue;
import getImage.getImage;
import getImage.pixelPosition;

public class ScannerHorizontal {
	
	public static void main(String[] args) throws IOException
	{
		scannerHLeft("Random/MYA.png");
		scannerHRight("Random/MYA.png");
	}
	
	public static void scannerHLeft(String filepath) throws IOException
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
					int staticRed = imagePix[v][w].getR();
					int staticGreen = imagePix[v][w].getG();
					int staticBlue = imagePix[v][w].getB();
					Color staticColor = new Color(staticRed,staticGreen,staticBlue);
					HorLeft.setRGB(v,w,staticColor.getRGB());
				}
				else
				{
					Color staticColor = new Color(255,255,255);
					HorLeft.setRGB(v,w,staticColor.getRGB());
				}
			}
		}
		ImageIO.write(HorLeft, "bmp",new File("C:\\Users\\BT_1N3_23\\git\\JFont-Generator\\imageInput\\HorizontalLeft.bmp"));	
	}
	
	public static void scannerHRight(String filepath) throws IOException
	{
		getImage image = new getImage(filepath); 	
		getImage imageConverted = new getImage(image.getBW());	
		RGBValue[][] imagePix = imageConverted.getPixelsArray();		
		pixelPosition[][] foundPix = new pixelPosition[imageConverted.getWidth()][imageConverted.getHeight()];
		
		for(int y = imageConverted.getHeight()-1; y >= 0 ; y++)
		{
			for(int x = imageConverted.getWidth()-1 ; x >= 0 ; x++)
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
					int staticRed = imagePix[v][w].getR();
					int staticGreen = imagePix[v][w].getG();
					int staticBlue = imagePix[v][w].getB();
					Color staticColor = new Color(staticRed,staticGreen,staticBlue);
					HorRight.setRGB(v,w,staticColor.getRGB());
				}
				else
				{
					Color staticColor = new Color(255,255,255);
					HorRight.setRGB(v,w,staticColor.getRGB());
				}
			}
		}
		ImageIO.write(HorRight, "bmp",new File("C:\\Users\\BT_1N3_23\\git\\JFont-Generator\\imageInput\\HorizontalRight.bmp"));	
		
	}
	
	public static boolean isBlack(RGBValue rgb)
	{
		return rgb.getR() == 0 && rgb.getG() == 0 && rgb.getB() == 0;
	}
	
}
