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

public class Reflection {

	
	public static BufferedImage reflector(getImage img) {
		BufferedImage ReflectedImg = new BufferedImage(img.getWidth(),img.getHeight(),BufferedImage.TYPE_INT_RGB);
		RGBValue[][] colors = img.getPixelsArray();
		for(int x = 0; x < img.getWidth(); x++) {
			for(int y = 0; y < img.getHeight();y++) {
				if(isBlack(colors[x][y])) {
					ReflectedImg.setRGB(img.getWidth()-x-1, y, new Color(0,0,0).getRGB());
				}
				else
				{
					ReflectedImg.setRGB(img.getWidth()-x-1,y, new Color(255,255,255).getRGB());
				}
			}
		}
		return ReflectedImg;
	}
	
	public static boolean isBlack(RGBValue rgb){
		return rgb.getR() == 0 && rgb.getG() == 0 && rgb.getB() == 0;
	}

}
