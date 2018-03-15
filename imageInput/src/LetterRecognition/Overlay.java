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
		
		
		getImage a = new getImage("a.png"); //H; 208 W: 208 ImageType: 12
		getImage aplus = new getImage("b.png");
		
		//System.out.println(a.getHeight() + " " + a.getWidth());
		
		RGBValue[][] apix = a.getArrayRGB();
		RGBValue[][] bpix = aplus.getArrayRGB();
		//System.out.println(apix.length);
		int overlap = 0;
		int nolap = 0;
		for(int i = 0; i < 1152; i++)
		{
			for(int j = 0; j < 648; j++)
			{
				//System.out.println(apix[j][i].getB() + "  " + bpix[j][i].getB());
				//System.out.println("x:"+ i + "  y:" + j + "  " + "A:" + a.getRGB(j,i).getR() + ", " + aplus.getRGB(j,i).getR());
				if(isRGBEqual(apix[j][i],bpix[j][i]))
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
		System.out.print(((double) overlap/ (double) (nolap+overlap)));
	}
	public static boolean isRGBEqual(RGBValue one, RGBValue two)
	{
		return (one.getR() == two.getR() && one.getG() == two.getG() && one.getB() == two.getB());
	}
}
