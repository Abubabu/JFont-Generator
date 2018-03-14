package LetterRecognition;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;

import getImage.RGBValue;
import getImage.getImage;

public class Overlay {
	public static void main(String[] args) throws IOException
	{
		
		//https://stackoverflow.com/questions/29024456/image-overlay-comparison-and-pixel-color-change
		
		
		getImage a = new getImage("a.png"); //H; 116 W: 207 ImageType: 12
		getImage aplus = new getImage("aplus.png");
		
		BufferedImage negative = new BufferedImage(116,207, 12); 
		for(int i = 0; i < 116; i++)
		{
			for(int j = 0; j < 207; j++)
			{
				if(isRGBEqual(a.getRGB(j, i),aplus.getRGB(j, i)))
				{
					RGBValue colo = a.getRGB(j, i);
					Color color = new Color(colo.getR(),colo.getG(),colo.getB());
					negative.setRGB(j, i,  color.getRGB());
				}
			}
		}
		
		
	
		
	}
	public static boolean isRGBEqual(RGBValue one, RGBValue two)
	{
		return one.getR() == two.getR() && one.getG() == two.getG() && one.getB() == two.getB();
	}
}
