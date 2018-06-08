package CreationAnalysis;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import getImage.CardinalPixels;
import getImage.RGBValue;
import getImage.getImage;
import getImage.pixelPosition;

public class ScannerVertical {
	public static void main(String[] args) throws IOException
	{
		scanImage("Random/MYA.png");
	}
	public static void scanImage(String filepath) throws IOException
	{
		getImage image = new getImage(filepath); 	
		getImage imageConverted = new getImage(image.getBW());	
		pixelPosition[][] imagePix = imageConverted.getArrayPixels();	
		RGBValue[][] imageColors = imageConverted.getPixelsArray();	 
		pixelPosition[][] topBot = new pixelPosition[imagePix.length][imagePix[1].length];
		pixelPosition[][] botTop = new pixelPosition[imagePix.length][imagePix[1].length];
		boolean foundTopBot = false;
		boolean foundBotTop = false;
		for(int x = 0; x < imagePix.length; x++) {
			for(int y = 0; y < imagePix[x].length; y++) {
				if(!foundTopBot && isBlack(imageColors[x][y])) {
					topBot[x][y] = imagePix[x][y];
					foundTopBot = true;
				}
				if(foundBotTop && isBlack(imageColors[x][imageColors.length - 1 - y])) {
					botTop[x][imageColors.length - 1 - y] = imagePix[x][imageColors.length - 1 - x];
					foundBotTop = true;
				}
				if(foundBotTop && foundTopBot) {
					foundTopBot = false;
				    foundBotTop = false;
					break;
				}
			}
		}
BufferedImage VerTop = new BufferedImage(imageConverted.getWidth(),imageConverted.getHeight(),BufferedImage.TYPE_INT_RGB);
		
		
		for(int v = 0; v < imageConverted.getWidth(); v++)
		{
			for(int w = 0; w < imageConverted.getHeight();w++) {
				if(topBot[v][w] != null) {
					Color staticColor = new Color(0,0,0);
					VerTop.setRGB(v,w,staticColor.getRGB());
				}
				else
				{
					Color staticColor = new Color(255,255,255);
					VerTop.setRGB(v,w,staticColor.getRGB());
				}
			}
		}
		ImageIO.write(VerTop, "bmp",new File("C:\\Users\\BT_1N3_22\\git\\JFont-Generator\\imageInput\\VerTop.bmp"));	
	}
	
	public static boolean isBlack(RGBValue rgb){
		return rgb.getR() == 0 && rgb.getG() == 0 && rgb.getB() == 0;
	}

}
