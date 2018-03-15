package LetterRecognition;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import getImage.RGBValue;
import getImage.getImage;

public class Overlay {
	public static void main(String[] args) throws IOException
	{
		
		//https://stackoverflow.com/questions/29024456/image-overlay-comparison-and-pixel-color-change
		
		
		getImage a = new getImage("1.jpg"); //H; 116 W: 207 ImageType: 12
		getImage aplus = new getImage("2.jpg");
		
		BufferedImage negative = new BufferedImage(500,500, 12); 
		for(int i = 0; i < 116; i++)
		{
			for(int j = 0; j < 207; j++)
			{
				//System.out.println("x:"+ i + "  y:" + j + "  " + "A:" + a.getRGB(j,i).getR() + ", " + aplus.getRGB(j,i).getR());
				if(isRGBEqual(a.getRGB(j, i),aplus.getRGB(j, i)))
				{
					System.out.println(2);
					Color color = new Color(255,255,255);
					negative.setRGB(j, i,  color.getRGB());
				}
				else
				{
					System.out.println(1);
					Color color = new Color(0,0,0);
					negative.setRGB(j, i,  color.getRGB());
				}
			}
		}
		
		ImageIO.write(negative, "png", new File("yes.png"));
		
		
	
		
	}
	public static boolean isRGBEqual(RGBValue one, RGBValue two)
	{
		return (one.getR() == two.getR() && one.getG() == two.getG() && one.getB() == two.getB());
	}
}
