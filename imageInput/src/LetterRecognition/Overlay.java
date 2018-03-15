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
		
		
		getImage a = new getImage("a.png"); //H; 116 W: 207 ImageType: 12
		getImage aplus = new getImage("aplus.png");
		
		RGBValue[][] apix = a.getArrayRGB();
		RGBValue[][] aplix = aplus.getArrayRGB();
		int overlap = 0;
		int nolap = 0;
		for(int i = 0; i < 116; i++)
		{
			for(int j = 0; j < 207; j++)
			{
				//System.out.println("x:"+ i + "  y:" + j + "  " + "A:" + a.getRGB(j,i).getR() + ", " + aplus.getRGB(j,i).getR());
				if(isRGBEqual(apix[j][i],aplix[j][i]))
				{
					overlap++;		
				}
				else
				{
					nolap++;
				}
			}
		}
		System.out.println(overlap + " " + nolap);
	}
	public static boolean isRGBEqual(RGBValue one, RGBValue two)
	{
		return (one.getR() == two.getR() && one.getG() == two.getG() && one.getB() == two.getB());
	}
}
