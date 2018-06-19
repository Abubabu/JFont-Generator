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
public class LineDirections {

	
	public static double[] findDifferences(getImage img) {
		boolean foundFirst = false;
		int prevX = 0;
		int prevY = 0;
		int count = 0;
		RGBValue[][] colors = img.getPixelsArray();
		double[] differences = new double[img.getWidth()];
		for(int x = 0; x < img.getWidth(); x++) {
			for(int y = 0; y < img.getHeight();y++) {
				if(isBlack(colors[x][y]) && !foundFirst) {
					prevX = x;
					prevY = y;
					foundFirst = true;
				}
				else if(isBlack(colors[x][y]) && foundFirst) {
					differences[count] = y - prevY;
					count++;
				}
			}
		}
		return differences;
	}
	
	public static double[] findLines(double[] differences) {
		
	}
	public static boolean isBlack(RGBValue rgb)
	{
		return rgb.getR() == 0 && rgb.getG() == 0 && rgb.getB() == 0;
	}
}
