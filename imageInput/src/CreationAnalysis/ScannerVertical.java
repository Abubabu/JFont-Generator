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
		CardinalPixels cardinalColors = new CardinalPixels(imageConverted);
		pixelPosition[][] topBotLeft = new pixelPosition[imagePix.length][imagePix[1].length];
		pixelPosition[][] botTopLeft = new pixelPosition[imagePix.length][imagePix[1].length];
		pixelPosition[][] topBotRight = new pixelPosition[imagePix.length][imagePix[1].length];
		pixelPosition[][] botTopRight = new pixelPosition[imagePix.length][imagePix[1].length];
		int midway = cardinalColors.getLeft().getxPos() + (  (cardinalColors.getRight().getxPos() - cardinalColors.getLeft().getxPos())  /2);
		System.out.println(midway);
		for(int x = 0; x < imagePix.length ; x++) {
			boolean foundtopBotLeft = false;
			boolean foundbotTopLeft = false;
			boolean foundtopBotRight = false;
			boolean foundbotTopRight= false;
			for(int y = 0; y < imagePix[x].length; y++) {
				if(x < midway) { // seperates the left hand array code and right hand for efficiency 
					if(!foundtopBotLeft && isBlack(imageColors[x][y])) { // checks for a found in order to keep the loop going to find both pixels in one loop without accidentally replacing one
						topBotLeft[x][y] = imagePix[x][y];
						foundtopBotLeft = true;
					}
					if(!foundbotTopLeft && isBlack(imageColors[x][imageColors.length - 1 - y])) {
						botTopLeft[x][imageColors.length - 1 - y] = imagePix[x][imageColors.length - 1 - y];
						foundbotTopLeft = true;
					}
					if(foundbotTopLeft && foundtopBotLeft) {// if both are found move on
						break;
					}
				}
				else {
					if(!foundtopBotRight&& isBlack(imageColors[x][y])) { // checks for a found in order to keep the loop going to find both pixels in one loop without accidentally replacing one
						topBotRight[x][y] = imagePix[x][y];
						foundtopBotRight = true;
					}
					if(!foundbotTopRight && isBlack(imageColors[x][imageColors.length - 1 - y])) {
						botTopRight[x][imageColors.length - 1 - y] = imagePix[x][imageColors.length - 1 - y];
						foundbotTopRight = true;
					}
					if(foundbotTopRight && foundtopBotRight) {// if both are found move on
						break;
					}
					
				}
					
			}
		}
		BufferedImage VerTop = new BufferedImage(botTopLeft.length,botTopLeft[0].length,BufferedImage.TYPE_INT_RGB);
	//	System.out.println("first");
		for(int v = 0; v < VerTop.getWidth(); v++)
		{
			//System.out.println("second");
			for(int w = 0; w < VerTop.getHeight();w++) {
				if(botTopLeft[v][w] != null) { 
				//System.out.println("third");
					Color staticColor = new Color(0,0,0);
					VerTop.setRGB(v,w,staticColor.getRGB());
				//	System.out.println("fourth");
				}
				else
				{
			
					Color staticColor = new Color(255,255,255);
					VerTop.setRGB(v,w,staticColor.getRGB());
				}
			}
		}
		ImageIO.write(VerTop, "bmp",new File("C:\\\\Users\\\\Administrator\\\\git\\\\JFont-Generator\\\\imageInput\\\\JFont-GeneratorVerTop.bmp"));	
		//System.out.println("fifth");
	}
	
	public static boolean isBlack(RGBValue rgb){
		return rgb.getR() == 0 && rgb.getG() == 0 && rgb.getB() == 0;
	}

}
